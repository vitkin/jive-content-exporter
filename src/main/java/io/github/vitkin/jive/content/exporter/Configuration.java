package io.github.vitkin.jive.content.exporter;

import lombok.Data;

/**
 *
 * @author Victor Itkin
 */
@Data
public class Configuration {

  private String exportPath;
  private String host;
  private String samlAuthenticationUrl;
  private String userId;
  private String password;
  private String proxyHost;
  private int proxyPort;
  private String group;
}
