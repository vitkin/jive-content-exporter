package io.github.vitkin.jive.api.data;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveEmail {

  @SerializedName("jive_label")
  private String label;

  private boolean primary;

  private String type;

  @SerializedName("value")
  private String email;
}
