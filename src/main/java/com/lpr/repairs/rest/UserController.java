package com.lpr.repairs.rest;

import com.lpr.repairs.dto.UserDto;
import com.lpr.repairs.dto.param.create.UserCreateParam;
import com.lpr.repairs.dto.param.search.UserSearchParam;
import com.lpr.repairs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<UserDto> getAllUsers(
      @RequestParam(value = "user_id", required = false) @Pattern(regexp = ALPHANUMERIC) String userId,
      @RequestParam(value = "user_name", required = false) @Pattern(regexp = ALPHANUMERIC) String userName,
      @RequestParam(value = "name", required = false) @Pattern(regexp = ALPHANUMERIC) String name,
      @RequestParam(value = "surname", required = false) @Pattern(regexp = ALPHANUMERIC) String surname,
      @RequestParam(value = "estates", required = false) Set<Long> estateIds
  ) {
    var searchParam = UserSearchParam.builder()
        .id(userId)
        .userName(userName)
        .name(name)
        .surname(surname)
        .estates(estateIds).build();

    return userService.search(searchParam);
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
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
