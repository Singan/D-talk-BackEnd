package dtalk.repository;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizRepository {
    private final EntityManager em;
    @Transactional
    public Long save(Quiz quiz) {
        em.persist(quiz);
        return quiz.getIdx();
    }

}
