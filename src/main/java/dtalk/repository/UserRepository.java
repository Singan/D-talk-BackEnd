package dtalk.repository;

import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public Long save(User user) {


        em.persist(user);
        return user.getIdx();
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
    public Long findByUserCount(String userId){
        return em.createQuery("select count(u) from User u where u.id = :id", Long.class)
                .setParameter("id",userId)
                .getSingleResult();
    }
    public User findByUserId(String userId){
        return em.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id",userId)
                .getSingleResult();

    }
}
