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
        if(record1.getLike()==0){
            record1.setLike(1);
        }else{
            record1.setLike(0);
        }
    }
    public Long userRecommendCount(Record record){
        Long likeCount = em.createQuery("select count(r) from Record r where r.quiz.user=:user and r.like= 1",
                        Long.class)
                .setParameter("user",record.getUser())
                .getSingleResult();
        return likeCount;
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
        Long like= em.createQuery("select count(r) " +
                                "from Record r where r.quiz=:quiz and r.like= 1 ",
                        Long.class)
                .setParameter("quiz",quiz)
                .getSingleResult();
        return like;
    }
    public void updateRecord(Record record){
        em.persist(record);
    }

    public List<Quiz> receiveQuizList(User user){
        return em.createQuery("select r.quiz from Record r where r.user=:user",
                Quiz.class).setParameter("user",user).getResultList();
    }
}
