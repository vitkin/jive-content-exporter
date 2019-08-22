package io.github.vitkin.jive.api.data;

import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JivePersonDetails {

  private boolean enabled;

  private boolean external;

  private boolean federated;

  private String lastProfileUpdate;

  private JivePersonLevel level;

  private String locale;

  private boolean externalContributor;

  private boolean sendable;

  private String timeZone;

  private String username;

  private boolean visible;
}
