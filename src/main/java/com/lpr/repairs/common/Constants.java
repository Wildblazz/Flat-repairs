package com.lpr.repairs.common;

import lombok.experimental.UtilityClass;

public class Constants {

  @UtilityClass
  public static class Validation {
    public static final String ALPHANUMERIC = "^[a-zA-Z0-9]*$";
    public static final String ALPHABETIC = "^[a-zA-Z]*$";
    public static final String NUMERIC = "^[0-9]*$";
  }

  @UtilityClass
  public static class Regex {
    public static final String ANY = "%";
  }

  @UtilityClass
  public static class UserFields {
    public static final String ID = "id";
    public static final String USER_NAME = "userName";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String ESTATES = "estates";
  }
}
