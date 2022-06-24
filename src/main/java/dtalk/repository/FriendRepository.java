package dtalk.repository;

import dtalk.domain.FriendRequest;
import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendRepository {
    private final EntityManager em;

    @Transactional
    public Long friendSend(FriendRequest friendRequest) {
        em.persist(friendRequest);
        return friendRequest.getIdx();
    }

    public Long friendCount(User user1, User user2) {
        return em.createQuery("select count(f) from FriendRequest f where" +
                                "(f.sendUser = :user1 and f.receiveUser = :user2) or " +
                                "( f.receiveUser = :user2 and f.sendUser = :user1)"
                        , Long.class).
                setParameter("user1", user1).
                setParameter("user2", user2).
                getSingleResult();
    }

    ;
/*    public List<User> findFriend(User user){
        return em.createQuery("select f.sendUser "+
                                "FROM FriendRequest f where (f.sendUser = :user) or (f.receiveUser = :user)",
                User.class)
                .setParameter("user",user)
                .getResultList();
    }*/

    public List<User> friendReceive(User user) {
        return em.createQuery("select f.sendUser FROM FriendRequest f  " +
                        "where (f.receiveUser = :user)", User.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Transactional
    public void friendRefuse(User user,User me) {
        List<FriendRequest> friendRequest = em.createQuery(
                "select f from FriendRequest f where " +
                        "(f.sendUser = :user) and (f.receiveUser = :me)"
                , FriendRequest.class)
                .setParameter("user",user)
                .setParameter("me",me).getResultList();
        em.remove(friendRequest.get(0));
    }
}
