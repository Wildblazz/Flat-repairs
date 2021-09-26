package com.lpr.repairs.repository;

import com.lpr.repairs.dto.param.search.UserSearchParam;
import com.lpr.repairs.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryCustom {
  List<User> search(UserSearchParam searchParam);
}
