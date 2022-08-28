package dtalk.repository;

import dtalk.domain.Batch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BatchRepository {
    private final EntityManager em;

    public Long batchIdx(Batch batch){
        em.persist(batch);
        return batch.getIdx();
    }
}
