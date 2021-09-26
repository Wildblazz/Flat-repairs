package com.lpr.repairs.rest;

import com.lpr.repairs.dto.UserDto;
import com.lpr.repairs.dto.param.create.UserCreateParam;
import com.lpr.repairs.dto.param.search.UserSearchParam;
import com.lpr.repairs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Page<UserDto> getAllUsers(@PageableDefault(sort = "name", size = 25) Pageable pageable) {
    return userService.findAll(pageable);
  }

  @PostMapping(path = "/search")
  @ResponseStatus(HttpStatus.OK)
  public List<UserDto> search(@Valid @RequestBody UserSearchParam searchParam) {
    return userService.search(searchParam);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateParam createParam) {
    return new ResponseEntity<>(userService.create(createParam), HttpStatus.OK);
  }

  @DeleteMapping()
  @ResponseStatus(HttpStatus.OK)
  public void removeUser(@NotBlank @RequestParam Long id) {
    userService.remove(id);
  }
}
