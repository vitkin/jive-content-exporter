package io.github.vitkin.jive.api.data;

import java.util.List;
import java.util.Map;
import lombok.Value;

/**
 * 
 * @author Victor Itkin
 */
@Value
public class ApiVersion {

  private String jiveVersion;

  private List<Map<String, String>> jiveCoreVersions;

  private String instanceURL;

  private boolean ssoForOAuthGrantEnabled;

  private Map<String, String> jiveEdition;
}
