package io.github.vitkin.jive.content.exporter;

/**
 *
 * @author Victor Itkin
 */
enum Property {

  CONFIG_PATH("config.path", "etc/jive-content-exporter/config.properties"),
  CREDENTIALS_PATH("credentials.path", "etc/jive-content-exporter/credentials.properties"),
  EXPORT_PATH("export.path", "var/jive-content-exporter"),
  HOST("host", "sandbox.jiveon.com"),
  SAML_AUTHENTICATION_URL("saml.authentication.url", "https://idp.example.org/saml2/sso"),
  USER_ID("userId"),
  PASSWORD("password"),
  GROUP("group"),
  PROXY_HOST("proxy.host"),
  PROXY_PORT("proxy.port");

  private static final String SYSTEM_PROPERTY_PREFIX = "jive-content-exporter.";

  final String name;
  final String systemPropertyName;
  final String defaultValue;

  private Property(String name, String defaultValue) {
    this.name = name;
    this.systemPropertyName = SYSTEM_PROPERTY_PREFIX + name;
    this.defaultValue = defaultValue;
  }

  private Property(String name) {
    this.name = name;
    this.systemPropertyName = SYSTEM_PROPERTY_PREFIX + name;
    this.defaultValue = null;
  }

  @Override
  public String toString() {
    return name;
  }
}
