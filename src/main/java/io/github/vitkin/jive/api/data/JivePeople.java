package io.github.vitkin.jive.api.data;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Victor Itkin
 */
public class JivePeople extends JiveResults<JivePerson> {

  /**
   *
   * @param itemsPerPage
   * @param links
   * @param list
   * @param startIndex
   */
  public JivePeople(int itemsPerPage, Map<String, String> links, List<JivePerson> list, int startIndex) {
    super(itemsPerPage, links, list, startIndex);
  }

}
