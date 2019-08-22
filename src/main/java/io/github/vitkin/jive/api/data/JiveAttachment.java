package io.github.vitkin.jive.api.data;

import java.util.Map;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveAttachment {

  private String id;

  private Map<String, JiveResource> resources;

  private String contentType;

  private String name;

  private long size;

  private String url;

  private boolean doUpload;

  private String type;
}
