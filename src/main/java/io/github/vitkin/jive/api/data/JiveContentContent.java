package io.github.vitkin.jive.api.data;

import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveContentContent {

  private String text;
  private boolean editable;
  private String type;
}

