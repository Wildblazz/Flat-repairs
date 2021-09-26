package com.lpr.repairs.repository;

import com.lpr.repairs.model.TradeMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradeMarkRepository extends JpaRepository<TradeMark, Long> {
  Optional<TradeMark> findByName(String name);
}
