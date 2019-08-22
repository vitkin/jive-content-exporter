package io.github.vitkin.jive.api.data;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Victor Itkin
 */
public class JivePlaces extends JiveResults<JivePlace> {

  /**
   * 
   * @param itemsPerPage
   * @param links
   * @param list
   * @param startIndex 
   */
  public JivePlaces(int itemsPerPage, Map<String, String> links, List<JivePlace> list, int startIndex) {
    super(itemsPerPage, links, list, startIndex);
  }

}
