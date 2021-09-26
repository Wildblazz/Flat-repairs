package com.lpr.repairs.repository;

import com.lpr.repairs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Page<User> findAll(Pageable pageable);

  Optional<User> findByUserName(String username);

  List<User> findByNameAndSurname(String name, String surname);


//  todo just for training. There is an analogue with in UserRepositoryCustom
//  @Query("select distinct u from User u  " +
//      "where u.userName = :value OR u.name = :value OR u.surname = :value")
//  List<User> findByValue(String value);
//
//  @Query("select distinct u from User u  " +
//         "join u.estates us " +
//          "where (us.id in :estateIds and (u.userName = :value OR u.name = :value OR u.surname = :value))")
//  List<User> findByValueAndEstateIdIn(String value, List<Long> estateIds)  ;
//
//  @Query("select u from User u join u.estates us where us.id in :estateId")
//  List<User> findByEstateId(List<Long> estateId);
}
