package io.github.vitkin.jive.api.data;

import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JivePersonName {

  private String familyName;

  private String formatted;

  private String givenName;
}
