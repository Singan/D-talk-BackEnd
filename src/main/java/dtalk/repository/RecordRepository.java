package dtalk.repository;

import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordRepository {
    private final EntityManager em;
    @Transactional
    public void quizRecommend(Record record){
        Record record1 = em.createQuery("select r from Record r where r.user=:user and r.quiz=:quiz",
                        Record.class)
                .setParameter("user",record.getUser())
                .setParameter("quiz",record.getQuiz())
                .getSingleResult();
        if(record1.getRecommend()==0){
            record1.setRecommend(1);
        }else{
            record1.setRecommend(0);
        }
    }
    public Long userRecommendCount(Record record){
        Long recommendCount = em.createQuery("select count(r) from Record r where r.quiz.user=:user and r.recommend= 1",
                        Long.class)
                .setParameter("user",record.getUser())
                .getSingleResult();
        return recommendCount;
    }
    public Record findRecord(Record record){
        Record record1 = em.createQuery("select r from Record r where r.user=:user and r.quiz=:quiz",
                        Record.class)
                .setParameter("user",record.getUser())
                .setParameter("quiz",record.getQuiz())
                .getSingleResult();
        return record1;
    }
    public Long findRecordLike(Quiz quiz){
        Long recommend= em.createQuery("select count(r) " +
                                "from Record r where r.quiz=:quiz and r.recommend= 1 ",
                        Long.class)
                .setParameter("quiz",quiz)
                .getSingleResult();
        return recommend;
    }
    public void updateRecord(Record record){
        em.persist(record);
    }

    public List<Quiz> receiveQuizList(User user){
        return em.createQuery("select r.quiz from Record r join fetch Quiz where r.user=:user",
                Quiz.class).setParameter("user",user).getResultList();
    }
}
