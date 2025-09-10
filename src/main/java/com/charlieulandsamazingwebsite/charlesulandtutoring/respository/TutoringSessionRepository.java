package com.charlieulandsamazingwebsite.charlesulandtutoring.respository;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TutoringSessionRepository extends CrudRepository<TutoringSession, Integer> {

    List<TutoringSession> findAllByTutoringClass(TutoringClass tutoringClass);

    TutoringSession findById(int id);

    List<TutoringSession> findTutoringSessionsByMonthAndDay(int month, int day);
}
