package com.charlieulandsamazingwebsite.charlesulandtutoring.respository;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<AppUser, Integer> {

    AppUser findById(int id);
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
