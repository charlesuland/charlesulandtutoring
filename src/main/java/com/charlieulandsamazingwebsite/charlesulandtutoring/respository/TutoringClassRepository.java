package com.charlieulandsamazingwebsite.charlesulandtutoring.respository;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TutoringClassRepository extends CrudRepository<TutoringClass, Integer> {


    TutoringClass findTutoringClassById(int id);

    List<TutoringClass> getTutoringClassesByMonth(int value);
}
