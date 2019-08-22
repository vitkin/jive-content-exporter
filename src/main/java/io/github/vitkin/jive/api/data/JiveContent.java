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
public class JiveContent {

  private long id;

  private Map<String, JiveResource> resources;

  private long followerCount;

  private long likeCount;

  private Date published;

  private List<String> tags;

  private Date updated;

  private String iconCss;

  private Map<String, String> parentPlace;

  private String contentID;

  private JivePerson author;

  private JiveContentContent content;

  private String parent;

  private int replyCount;

  private String status;

  private String subject;

  private int viewCount;

  private boolean visibleToExternalContributors;

  private List<JiveAttachment> attachments;

  private String type;
  
  private String name;
  
  private String binaryURL;
}
