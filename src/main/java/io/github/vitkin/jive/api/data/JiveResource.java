package io.github.vitkin.jive.api.data;

import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveResource {

  private String[] allowed;
  private String ref;
}
