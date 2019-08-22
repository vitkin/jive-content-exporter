package io.github.vitkin.jive.api.data;

import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JivePersonLevel {

  private String description;

  private String imageURI;

  private String name;

  private long points;
}
