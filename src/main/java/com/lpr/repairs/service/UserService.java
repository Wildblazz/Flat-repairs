package com.lpr.repairs.service;

import com.lpr.repairs.dto.UserDto;
import com.lpr.repairs.dto.param.create.UserCreateParam;
import com.lpr.repairs.dto.param.search.UserSearchParam;
import com.lpr.repairs.model.User;
import com.lpr.repairs.repository.UserRepository;
import com.lpr.repairs.repository.UserRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserRepositoryCustomImpl repositoryCustom;

  @Transactional
  public UserDto create(@Valid UserCreateParam createParam) {
    findUserByUserName(createParam.getUsername()).ifPresent(usr -> {
      throw new RuntimeException("User with given userName exists");
    });
    return new UserDto(userRepository.save(new User(createParam)));
  }

  public Page<UserDto> findAll(Pageable pageable) {
    var users = userRepository.findAll(pageable);
    if (users.getTotalElements() == 0) {
      return new PageImpl<>(emptyList(), pageable, 0);
    }
    return users.map(UserDto::new);
  }

  @Transactional
  public void remove(Long id) {
    userRepository.deleteById(id);
  }

  public List<UserDto> search(UserSearchParam searchParam) {
    return  repositoryCustom.search(searchParam).stream().map(UserDto::new).collect(Collectors.toList());
  }

  public Optional<User> findUserByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }
}
