package io.github.vitkin.jive.content.exporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Victor Itkin
 */
@Log4j2
public class Main {

  /**
   *
   * @param args
   */
  public static void main(String... args) {

    Date startDate = Calendar.getInstance().getTime();

    try {
      final Configuration configuration = readConfiguration(args);
      final Exporter exporter = new Exporter();
      exporter.setConfiguration(configuration);
      exporter.logIn();
      exporter.exportGroup();
    } catch (IOException ex) {
      log.error(ex, ex);

      System.exit(-1);
    } finally {
      log.info(() -> "Started at " + startDate + ", ended at " + Calendar.getInstance().getTime());
    }
  }

  /**
   *
   * @throws java.io.IOException
   */
  private static Configuration readConfiguration(String... args) throws IOException {

    Configuration configuration = new Configuration();
    Properties properties = new Properties();

    // Load config.properties
    final String configPathArg
        = System.getProperty(Property.CONFIG_PATH.systemPropertyName,
            Property.CONFIG_PATH.defaultValue);

    final Path configPath = Paths.get(configPathArg).toAbsolutePath();

    if (Files.exists(configPath)) {
      log.info(() -> "Loading configuration from " + configPath);
      properties.load(Files.newInputStream(configPath));
    }

    // Get group name
    final String group = args.length != 0 ? args[0] : properties.getProperty(Property.GROUP.name);

    if (group == null) {
      handleMissingProperty(Property.GROUP);
    }

    configuration.setGroup(group);

    // Load credentials.properties
    final String credentialsPathArg
        = System.getProperty(Property.CREDENTIALS_PATH.systemPropertyName,
            Property.CREDENTIALS_PATH.defaultValue);

    final Path credentialsPath = Paths.get(credentialsPathArg).toAbsolutePath();

    if (Files.exists(credentialsPath)) {
      log.info(() -> "Loading credentials from " + credentialsPath);
      properties.load(Files.newInputStream(credentialsPath));
    }

    // Get userId
    final String userId = System.getProperty(Property.USER_ID.systemPropertyName,
        properties.getProperty(Property.USER_ID.name));

    if (userId == null) {
      handleMissingProperty(Property.USER_ID);
    }

    configuration.setUserId(userId);

    // Get password
    final String password = System.getProperty(Property.PASSWORD.systemPropertyName,
        properties.getProperty(Property.PASSWORD.name));

    if (password == null) {
      handleMissingProperty(Property.PASSWORD);
    }

    configuration.setPassword(password);

    // Get proxy host
    configuration.setProxyHost(properties.getProperty(Property.PROXY_HOST.name));

    // Get proxy port
    try {
      configuration.setProxyPort(Integer.parseInt(properties.getProperty(Property.PROXY_PORT.name)));
    } catch (NumberFormatException ex) {
    }

    // Get export path
    configuration.setExportPath(properties.getProperty(Property.EXPORT_PATH.name, Property.EXPORT_PATH.defaultValue));

    // Get host
    configuration.setHost(properties.getProperty(Property.HOST.name, Property.HOST.defaultValue));

    // Get samlAuthenticationUrl
    configuration.setSamlAuthenticationUrl(properties.getProperty(Property.SAML_AUTHENTICATION_URL.name,
        Property.SAML_AUTHENTICATION_URL.defaultValue));

    return configuration;
  }

  /**
   *
   */
  public static void usage() {
    System.err.println("\n"
        + "# Argument (overriding the one in properties file):\n"
        + "The name of the group to export.\n"
        + "\n"
        + "## Argument examples:\n"
        + "my-group\n"
        + "\n"
        + "# Optional system properties (overriding the ones in the properties files):\n"
        + "-D" + Property.CONFIG_PATH.systemPropertyName + " (Default: "
        + Paths.get(Property.CONFIG_PATH.defaultValue).toAbsolutePath() + ")\n"
        + "-D" + Property.CREDENTIALS_PATH.systemPropertyName + " (Default: "
        + Paths.get(Property.CREDENTIALS_PATH.defaultValue).toAbsolutePath() + ")\n"
        + "-D" + Property.EXPORT_PATH.systemPropertyName + " (Default: "
        + Paths.get(Property.EXPORT_PATH.defaultValue).toAbsolutePath() + ")\n"
        + "-D" + Property.USER_ID.systemPropertyName + "\n"
        + "-D" + Property.PASSWORD.systemPropertyName + "\n"
        + "-D" + Property.PROXY_HOST.systemPropertyName + "\n"
        + "-D" + Property.PROXY_PORT.systemPropertyName + "\n");
  }

  /**
   *
   * @param property
   */
  private static void handleMissingProperty(Property property) {
    System.err.println("Missing " + property + "!");
    usage();
    System.exit(-1);
  }
}
