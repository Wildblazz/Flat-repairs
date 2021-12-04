package com.lpr.repairs.service;

import com.lpr.repairs.dto.EstateDto;
import com.lpr.repairs.dto.param.create.EstateCreateParam;
import com.lpr.repairs.model.Estate;
import com.lpr.repairs.repository.EstateRepository;
import com.lpr.repairs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstateService {

  private final EstateRepository estateRepository;
  private final UserRepository userRepository;

  public List<EstateDto> findAll() {
    return estateRepository.findAll().stream().map(EstateDto::new).collect(Collectors.toList());
  }

  public EstateDto findEstateById(Long estateId) {
    return findById(estateId).map(EstateDto::new).orElse(null);
  }

  public Optional<Estate> findById(Long estateId) {
    return estateRepository.findById(estateId);
  }

  public EstateDto findByUser(Long userId, String name) {
    if (isNull(userId) && isBlank(name)) {
//    todo create custom exceptions
      throw new RuntimeException("Request params are empty");
    }
    return isBlank(name) ? estateRepository.findDistinctByUserId(userId).map(EstateDto::new).orElse(null) :
        estateRepository.findDistinctByUserName(name).map(EstateDto::new).orElse(null);
  }

  public EstateDto create(EstateCreateParam createParam) throws RuntimeException {
//    todo create custom exceptions
    validateArea(createParam);
    var user = userRepository.findById(createParam.getUserId()).orElseThrow(RuntimeException::new);
    return new EstateDto(estateRepository.save(new Estate(createParam, user)));
  }

  public void remove(Long id) {
    estateRepository.deleteById(id);
  }

  public EstateDto update(EstateDto dto) {
    if (dto.getId() == 0) {
      throw new RuntimeException();
    }
    var estate =  estateRepository.findById(dto.getId()).orElseThrow(RuntimeException::new);
    estate.setArea(dto.getArea());
    estate.setRooms(dto.getRooms());

    return new EstateDto(estateRepository.save(estate));
  }

  private void validateArea(EstateCreateParam createParam) {
    var totalArea = createParam.getArea() - createParam.getBathroomArea() - createParam.getKitchenArea() - createParam.getToiletArea();
    if (totalArea <= 0) {
      throw new RuntimeException("Wrong total area!");
    }
  }
}
