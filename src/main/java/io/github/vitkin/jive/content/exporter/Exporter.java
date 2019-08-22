package io.github.vitkin.jive.content.exporter;

import io.github.vitkin.jive.api.data.JiveContent;
import io.github.vitkin.jive.api.data.JiveContents;
import io.github.vitkin.jive.api.data.JivePlaces;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import kong.unirest.Config;
import kong.unirest.GsonObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 *
 * @author Victor Itkin
 */
@Log4j2
public class Exporter {

  /**
   */
  private Configuration configuration;

  /**
   *
   * @param configuration
   */
  public void setConfiguration(final Configuration configuration) {
    this.configuration = configuration;

    final Config config = Unirest.config();

    config
        .followRedirects(false)
        .setObjectMapper(new GsonObjectMapper());

    final String proxyHost = configuration.getProxyHost();
    final int proxyPort = configuration.getProxyPort();

    if (proxyHost != null && proxyPort != 0) {
      config.proxy(configuration.getProxyHost(), configuration.getProxyPort());
    }
  }

  /**
   *
   */
  public void logIn() {

    final String host = configuration.getHost();
    final String samlAuthenticationUrl = configuration.getSamlAuthenticationUrl();

    Unirest.get("https://" + host + "/login.jspa")
        .queryString("ssologin", "true")
        .queryString("fragment", "")
        .queryString("referer", "/")
        .queryString("hint", "")
        .asString()
        .ifSuccess(response -> {
          final String samlRequest;

          samlRequest = Jsoup.parse(response.getBody())
              .body()
              .getElementsByTag("form")
              .first()
              .getElementsByTag("div")
              .first()
              .getElementsByAttributeValue("name", "SAMLRequest")
              .first()
              .attributes()
              .get("value");

          log.debug(() -> "SAML Request: " + samlRequest);

          Unirest.post(samlAuthenticationUrl)
              .field("RelayState", "Lw==")
              .field("SAMLRequest", samlRequest)
              .asString()
              .ifFailure(response2 -> {
                handleResponseError(response2);
              });
        })
        .ifFailure(response -> {
          handleResponseError(response);
        });

    Unirest.post(samlAuthenticationUrl)
        .queryString("sid", "0")
        .field("option", "credential")
        .field("Ecom_User_ID", configuration.getUserId())
        .field("Ecom_Password", configuration.getPassword())
        .field("submit", "Login")
        .asString()
        .ifFailure(response -> {
          handleResponseError(response);
        });

    Unirest.get(samlAuthenticationUrl)
        .queryString("sid", "0")
        .asString()
        .ifSuccess(response -> {

          Elements elements = Jsoup.parse(response.getBody())
              .body()
              .getElementsByTag("form")
              .first()
              .getElementsByAttributeValue("name", "SAMLResponse");

          if (elements.isEmpty()) {
            log.error("Failed to log in!");

            System.exit(-3);
          } else {
            final String samlResponse;

            samlResponse = elements.first()
                .attributes()
                .get("value");

            log.debug(() -> "SAML Response: " + samlResponse);

            Unirest.post("https://" + host + "/saml/sso")
                .field("SAMLResponse", samlResponse)
                .field("RelayState", "Lw==")
                .asString()
                .ifFailure(response2 -> {
                  handleResponseError(response2);
                });
          }
        })
        .ifFailure(response -> {
          handleResponseError(response);
        });
  }

  /**
   *
   * @throws java.io.IOException
   */
  public void exportGroup() throws IOException {

    final String group = configuration.getGroup();
    final Path groupExportPath = Paths.get(configuration.getExportPath(), "groups", group).toAbsolutePath();

    log.info(() -> "Group export path: " + groupExportPath);
    Files.createDirectories(groupExportPath);
//    savePlacesJson(group);
    getPlaces(group, groupExportPath);
  }

  /**
   *
   * @param group
   */
  private void savePlacesJson(final String group) {

    final Path groupExportPath = Paths.get(configuration.getExportPath(), "groups", group).toAbsolutePath();
    final String host = configuration.getHost();

    Unirest.get("https://" + host + "/api/core/v3/search/places")
        .queryString("filter", "search(\"" + group + "\")")
        .queryString("filter", "type(group)")
        //        .queryString("count", "3")
        .asString()
        .ifSuccess((HttpResponse<String> response) -> {
          try {
            Files.writeString(groupExportPath.resolve("places.json"), response.getBody());
          } catch (IOException ex) {
            log.error(ex, ex);
          }
        })
        .ifFailure(response -> {
          handleResponseError(response);
        });
  }

  /**
   * Place service is defined
   * <a href=https://developers.jivesoftware.com/api/v3/cloud/rest/PlaceService.html>here</a>.
   *
   * @param group
   * @param groupExportPath
   */
  private void getPlaces(final String group, final Path groupExportPath) {

    final String host = configuration.getHost();

    Unirest.get("https://" + host + "/api/core/v3/search/places")
        .queryString("filter", "search(\"" + group + "\")")
        .queryString("filter", "type(group)")
        //        .queryString("count", "3")
        .asObject(JivePlaces.class)
        .ifSuccess((HttpResponse<JivePlaces> response) -> {
          log.debug(response::getBody);

          String uri = response.getBody().getList().get(0).getResources().get("contents").getRef();
//          saveContentsJson(uri);
          getContents(uri, groupExportPath);

          //TODO: Handle pages:
          // https://developers.jivesoftware.com/api/v3/cloud/rest/PlaceService.html#getPages(String,%20List<String>,%20String)
        })
        .ifFailure(response -> {
          handleResponseError(response);
        });
  }

  /**
   *
   * @param uri
   * @param groupExportPath
   */
  private void saveContentsJson(final String uri, final Path groupExportPath) {

    Unirest.get(uri)
        .queryString("count", "3")
        .asString()
        .ifSuccess((HttpResponse<String> response) -> {
          try {
            Files.writeString(groupExportPath.resolve("contents.json"), response.getBody());
          } catch (IOException ex) {
            log.error(ex, ex);
          }
        })
        .ifFailure(response -> {
          handleResponseError(response);
        });
  }

  /**
   *
   * @param uri
   * @param groupExportPath
   */
  private void getContents(final String uri, final Path groupExportPath) {

    final ExporterConsumer consumer = new ExporterConsumer();
    consumer.next = uri;
    consumer.groupExportPath = groupExportPath;

    while (consumer.next != null) {
      Unirest.get(consumer.next)
          .queryString("count", "3")
          .asObject(JiveContents.class)
          .ifSuccess(consumer)
          .ifFailure(response -> {
            handleResponseError(response);
          });
    }
  }

  /**
   * Content types are defined
   * <a href=https://developers.jivesoftware.com/api/v3/cloud/rest/ContentEntity.html>here</a>.
   *
   * @param list
   * @param groupExportPath
   */
  private void processList(final List<JiveContent> list, final Path groupExportPath) {

    list.stream().forEach(content -> {
      try {
        //TODO: Handle more content types.
        switch (content.getType()) {
          case "file":
            final String fileName = "DOC-" + content.getId() + "_" + content.getName();
            Files.deleteIfExists(groupExportPath.resolve(fileName));

            Unirest.get(content.getBinaryURL()).asFile(groupExportPath.resolve(fileName).toString())
                .ifFailure((fileResponse) -> {
                  handleResponseError(fileResponse);
                });

            break;
          case "document":
            Files.writeString(
                groupExportPath.resolve("DOC-" + content.getId() + ".html"),
                content.getContent().getText().replaceAll("&#39;", "'"));

            break;
        }
      } catch (IOException ex) {
        log.error(ex, ex);
      }
    });
  }

  /**
   *
   * @param response
   */
  private void handleResponseError(HttpResponse<?> response) {

    if (response.getStatus() != 302) {
      log.error(() -> "Status: " + response.getStatus());

      response.getParsingError().ifPresent(e -> {
        log.error("Parsing Exception: ", e);
        log.error(() -> "Original body: " + e.getOriginalBody());
      });

      System.exit(-2);
    }
  }

  /**
   *
   */
  class ExporterConsumer implements Consumer<HttpResponse<JiveContents>> {

    Path groupExportPath;
    String next;

    @Override
    public void accept(HttpResponse<JiveContents> response) {
      log.debug(response::getBody);
      processList(response.getBody().getList(), groupExportPath);
      final Map<String, String> links = response.getBody().getLinks();
      next = links != null ? links.get("next") : null;
    }
  }
}
