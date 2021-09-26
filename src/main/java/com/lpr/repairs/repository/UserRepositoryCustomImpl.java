package com.lpr.repairs.repository;

import com.lpr.repairs.dto.param.search.UserSearchParam;
import com.lpr.repairs.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.lpr.repairs.common.Constants.UserFields.ESTATES;
import static com.lpr.repairs.common.Constants.UserFields.ID;
import static com.lpr.repairs.common.Constants.UserFields.NAME;
import static com.lpr.repairs.common.Constants.UserFields.SURNAME;
import static com.lpr.repairs.common.Constants.UserFields.USER_NAME;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
//  todo just for training, other repos use specification
  private final EntityManager entityManager;

  @Override
  public List<User> search(UserSearchParam searchParam) {
    var builder = entityManager.getCriteriaBuilder();
    var query = builder.createQuery(User.class);

    var userRoot = query.from(User.class);
    var predicates = getUserPredicates(searchParam, userRoot, builder);

    query.where(builder.and(predicates.toArray(new Predicate[0]))).distinct(true);

    return predicates.isEmpty() ? List.of() : entityManager.createQuery(query).getResultList();
  }

  private List<Predicate> getUserPredicates(
      UserSearchParam searchParam,
      Root<User> userRoot,
      CriteriaBuilder builder) {
    var predicates = new ArrayList<Predicate>();
    addEqualCriteria(ID, searchParam.getId(), builder, userRoot, predicates);
    addEqualCriteria(USER_NAME, searchParam.getUserName(), builder, userRoot, predicates);
    addEqualCriteria(NAME, searchParam.getName(), builder, userRoot, predicates);
    addEqualCriteria(SURNAME, searchParam.getSurname(), builder, userRoot, predicates);

    if (Objects.nonNull(searchParam.getEstates()) && !searchParam.getEstates().isEmpty()) {
      predicates.add(userRoot.join(ESTATES).get(ID).in(searchParam.getEstates()));
    }
    return predicates;
  }

  private void addEqualCriteria(String fieldName, String value, CriteriaBuilder builder, Root root, List<Predicate> result) {
    if (isNotBlank(value)) {
      result.add(builder.equal(root.get(fieldName), value));
    }
  }
}
