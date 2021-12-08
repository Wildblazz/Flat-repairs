package com.lpr.repairs.common;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class Util {

  public static <T> Stream<T> getSafeStream(Collection<T> collection) {
    return Stream.ofNullable(collection).flatMap(Collection::stream);
  }

  public static boolean isCollectionsSizeEqual(Collection a, Collection b) {
    return CollectionUtils.size(a) == CollectionUtils.size(b);
  }

  public static void throwExceptionIfRequired(boolean required, String message) {
    if (required) {
      throw new RuntimeException(message);
    }
  }

  public static boolean isEmptyGenericCollection(Object param) {
    return param instanceof Iterable &&
        (CollectionUtils.isEmpty((Collection<?>) param) ||
        ((Collection<?>) param).stream().allMatch(Objects::isNull));
  }
}
