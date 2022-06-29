package dtalk.repository;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

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
    public List<Quiz> list(User user){
        return em.createQuery("select q from Quiz q where q.user=:user",Quiz.class)
                .getResultList();
    }

}
