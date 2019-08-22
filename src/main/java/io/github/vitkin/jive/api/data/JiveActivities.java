package io.github.vitkin.jive.api.data;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Victor Itkin
 */
public class JiveActivities extends JiveResults<JiveActivity> {

  /**
   * 
   * @param itemsPerPage
   * @param links
   * @param list
   * @param startIndex 
   */
  public JiveActivities(int itemsPerPage, Map<String, String> links, List<JiveActivity> list, int startIndex) {
    super(itemsPerPage, links, list, startIndex);
  }

}
