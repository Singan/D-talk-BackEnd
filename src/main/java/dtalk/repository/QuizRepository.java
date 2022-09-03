package dtalk.repository;

import dtalk.domain.CUTime;
import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.domain.status.RecordStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
        return em.createQuery("select q from Quiz q where q.user=:user order by q.cuTime.createdAt asc",Quiz.class)
                .setFirstResult(0).setMaxResults(10)
                .setParameter("user",user)
                .getResultList();
    }
    public Long countList(User user){
        return em.createQuery("select count(q) from Quiz q where q.user=:user",Long.class)
                .setParameter("user",user)
                .getSingleResult();
    }
    @Transactional
    public void quizSend(Record record){
        em.persist(record);
    }
    @Transactional
    public Quiz findQuiz(Long idx){
        return em.find(Quiz.class,idx);
    }

    public List<Quiz> rankQuizList(LocalDateTime prev , LocalDateTime next){
        System.out.println("퀴즈랭킹리스트 실행");
        return em.createQuery("select r.quiz from Record r  " +
                        " order by r.like desc",Quiz.class)
/*                .setParameter("prev",prev)
                .setParameter("next",next)*/
                .setFirstResult(0)
                .setMaxResults(20)
                .getResultList();
    }
}
