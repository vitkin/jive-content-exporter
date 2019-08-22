package io.github.vitkin.jive.api.data;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author Victor Itkin
 * @param <T>
 */
@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JiveResults<T> {

  protected int itemsPerPage;

  protected Map<String, String> links;

  protected List<T> list;

  protected int startIndex;
}
