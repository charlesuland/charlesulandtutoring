package com.charlieulandsamazingwebsite.charlesulandtutoring.respository;


import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    public Optional<Student> findStudentByEmail(String s);
}
