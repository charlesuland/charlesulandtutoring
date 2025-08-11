package com.charlieulandsamazingwebsite.charlesulandtutoring.respository;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.AppUser;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Enrollment;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {

    public List<Enrollment> getEnrollmentsByTutoringClass(TutoringClass tc);
}
