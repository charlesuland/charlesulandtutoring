package com.charlieulandsamazingwebsite.charlesulandtutoring.respository;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);

}
