package io.github.vitkin.jive.api.data;

import java.util.Date;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveActivityDetails {

  private String collection;

  private Date collectionUpdated;

  private JiveActivityParent parent;

  private JiveActor parentActor;

  private int parentReplyCount;

  private int replyCount;

  private long objectID;

  private int objectType;

  private String iconCss;

  private boolean canReply;
}
