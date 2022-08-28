package dtalk.repository;

import dtalk.domain.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankRepository {
    private final EntityManager em;

    @Transactional
    public void rankUpdate(Rank rank){
        em.persist(rank);
    }
}
