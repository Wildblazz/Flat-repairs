package com.lpr.repairs.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.lpr.repairs.common.Constants.Regex.ANY;
import static com.lpr.repairs.common.Util.isEmptyGenericCollection;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class SpecBuilder {

  public enum SearchOperation {
    EQUALITY,
    LIKE,
    IN
  }

  public enum SearchType {
    OR,
    AND
  }

  public static <T> Specification<T> getNonNullSpecs(List<Specification<T>> specList, SearchType type) {
//    todo add specific exception
    var result = Optional.ofNullable(specList.get(0)).orElseThrow(RuntimeException::new);

    for (var i = 1; i < specList.size(); i++) {
      result = SearchType.AND.equals(type) ?
          Specification.where(result).and(specList.get(i)) :
          Specification.where(result).or(specList.get(i));
    }
    return result;
  }

  public static <T> void addSpec(Specification<T> criteria, List<Specification<T>> specList) {
    if (Objects.nonNull(criteria)) {
      specList.add(criteria);
    }
  }

  public static <T> Specification<T> likeCriteria(String field, String param) {
    if (isNotBlank(param)) {
      return (root, query, cb) -> getLike(cb, root.get(field), param);
    }
    return null;
  }

  public static <T, R> Specification<R> equalCriteria(String field, T param) {
    if (Objects.nonNull(param)) {
      return (root, query, cb) -> getEqual(cb, root.get(field), param);
    }
    return null;
  }

  public static <T, R, Z> Specification<T> getJoinedTable(
      Class<T> owner, Class<R> inner, String innerName, String field, Z param, SearchOperation operation) {
    if (Objects.nonNull(param) && isNotBlank(field) && !isEmptyGenericCollection(param)) {
      return (root, query, cb) -> {
        Join<T, R> join = root.join(innerName);
        return getPredicate(cb, join.get(field), param, operation);
      };
    }
    return null;
  }

  public static <T> Predicate getPredicate(
      CriteriaBuilder cb,
      Expression<String> expression,
      T param,
      SearchOperation operation) {
    switch (operation) {
      case EQUALITY:
        return getEqual(cb, expression, param);
      case LIKE:
        return getLike(cb, expression, (String) param);
      case IN:
        return getIn(cb, expression, (Iterable) param);
      default:
        return null;
    }
  }

  public static <T> Predicate getEqual(CriteriaBuilder cb, Expression<String> expression, T param) {
    return cb.equal(expression, param);
  }

  public static Predicate getLike(CriteriaBuilder cb, Expression<String> expression, String param) {
    return cb.like(expression, ANY + param + ANY);
  }

  public static Predicate getIn(CriteriaBuilder cb, Expression<String> expression, Iterable<Object> inSearchParam) {
    CriteriaBuilder.In<Object> inClause = cb.in(expression);
    inSearchParam.forEach(inClause::value);
    return inClause;
  }
}
