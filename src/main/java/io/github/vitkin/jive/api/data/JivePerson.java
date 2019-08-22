package io.github.vitkin.jive.api.data;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JivePerson {

  private long id;

  private Map<String, JiveResource> resources;

  private long followerCount;

  private Date published;

  private Date updated;

  private String displayName;

  private List<JiveEmail> emails;

  private long followingCount;

  @SerializedName("jive")
  private JivePersonDetails details;

  private JivePersonName name;

  private int thumbnailId;

  private String type;
}
