package io.github.vitkin.jive.api.data;

import java.util.Date;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveActivityObject {

  private String id;

  private JiveActor author;

  private String displayName;

  private String objectType;

  private Date published;

  private String summary;

  private Date updated;

  private String url;
}
