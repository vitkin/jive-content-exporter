package io.github.vitkin.jive.api.data;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Victor Itkin
 */
public class JiveContents extends JiveResults<JiveContent> {

  /**
   * 
   * @param itemsPerPage
   * @param links
   * @param list
   * @param startIndex 
   */
  public JiveContents(int itemsPerPage, Map<String, String> links, List<JiveContent> list, int startIndex) {
    super(itemsPerPage, links, list, startIndex);
  }

}
