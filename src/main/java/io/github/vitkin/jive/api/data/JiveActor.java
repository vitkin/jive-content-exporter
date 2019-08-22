package io.github.vitkin.jive.api.data;

import java.util.Date;
import java.util.Map;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveActor {

  private String id;

  private String displayName;

  private Map<String, String> image;

  private String objectType;

  private Date published;

  private Date updated;

  private String url;
}
