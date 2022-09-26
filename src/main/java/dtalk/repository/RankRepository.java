package dtalk.repository;



import dtalk.domain.Batch;
import dtalk.domain.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankRepository {
    private final EntityManager em;

    @Transactional
    public void rankUpdate(Rank rank){
        em.persist(rank);
    }

    public List<Rank> dayRank(){
        Batch batch = em.createQuery("select b from Batch b where b.type='일간'" +"order by b.idx desc ", Batch.class)
                .setFirstResult(0).setMaxResults(10).getSingleResult();

        return em.createQuery("select r FROM  Rank r where r.batch = :batch",Rank.class)
                .setParameter("batch",batch)
                .getResultList();
    }
}
