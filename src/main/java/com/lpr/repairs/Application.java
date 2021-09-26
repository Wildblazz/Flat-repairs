package com.lpr.repairs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lpr.repairs")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }
}
