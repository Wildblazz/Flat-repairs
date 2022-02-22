package com.lpr.repairs.common;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Util {

  public static <T> Stream<T> getNullSafeStream(Collection<T> collection) {
    return Stream.ofNullable(collection).flatMap(Collection::stream);
  }

  public static <T> Collection<T> getNullSafeCollection(Collection<T> collection) {
    return Objects.isNull(collection) ? CollectionUtils.emptyCollection() : collection;
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

  public static boolean isAnyNonNull(Object ...param) {
    return Objects.nonNull(param) && Arrays.stream(param).anyMatch(Objects::nonNull);
  }

  public static <T> Optional<Collection<T>> getCollectionAsOptional(Collection<T> collection) {
    return CollectionUtils.isEmpty(collection) ? Optional.empty() : Optional.of(collection);
  }
}
