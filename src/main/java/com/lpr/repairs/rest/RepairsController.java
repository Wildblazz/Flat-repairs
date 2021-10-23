package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.common.DesiredRepairsParam;
import com.lpr.repairs.service.RepairsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/repairs")
@RequiredArgsConstructor
public class RepairsController {

  private final RepairsService repairsService;

  @PostMapping(path = "/calculate")
  @ResponseStatus(HttpStatus.OK)
  public double calculate(@Valid @RequestBody DesiredRepairsParam param) {
    return repairsService.calculate(param);
  }
}
