package io.github.vitkin.jive.api.data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JivePlace {

  private long id;

  private Map<String, JiveResource> resources;

  private long followerCount;

  private List<String> tags;

  private Date updated;

  private String iconCss;

  private long placeID;

  private List<String> contentTypes;

  private String description;

  private String displayName;

  private String name;

  private String parent;

  private String status;

  private long viewCount;

  private boolean visibleToExternalContributors;

  private JivePerson creator;

  //TODO: HOW BEST TO TYPE THESE VARIABLES FOR ONLY GROUPS
  private String groupType;

  private long memberCount;

  //TODO: HOW BEST TO TYPE THESE VARIABLES FOR ONLY PROJECTS
  private Date dueDate;

  private String projectStatus;

  private Date startDate;

  private String type;
}
