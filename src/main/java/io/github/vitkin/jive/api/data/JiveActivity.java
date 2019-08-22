package io.github.vitkin.jive.api.data;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class JiveActivity {

  private JiveActor actor;

  private String content;

  @SerializedName("jive")
  private JiveActivityDetails details;

  private JiveActivityObject object;

  //TODO: NEED TO CONFIRM IF WE NEED TO CREATE YET ANOTHER TYPED OBJECT OR IF THIS IS SAFE TO RE-USE
  private JiveActor provider;

  private Date published;

  //TODO: NEED TO CONFIRM IF WE NEED TO CREATE YET ANOTHER TYPED OBJECT OR IF THIS IS SAFE TO RE-USE
  private JiveActor target;

  private String title;

  private Date updated;

  private String url;

  private String verb;
}
