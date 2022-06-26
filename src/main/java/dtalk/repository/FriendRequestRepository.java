package dtalk.repository;

import dtalk.domain.FriendRequest;
import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendRequestRepository {
    private final EntityManager em;

    @Transactional
    public Long friendSend(FriendRequest friendRequest) {
        em.persist(friendRequest);
        return friendRequest.getIdx();
    }

    public Long findRequest(User user1, User user2) {
        return em.createQuery("select count(f) from FriendRequest f where" +
                                "(f.sendUser = :user1 and f.receiveUser = :user2) or " +
                                "( f.receiveUser = :user2 and f.sendUser = :user1)"
                        , Long.class).
                setParameter("user1", user1).
                setParameter("user2", user2).
                getSingleResult();
    }
    public Long findFriend(User user,User user2){
        return em.createQuery("select count(f) "+
                                "FROM FriendRequest f " +
                                "where (f.sendUser = :user2) and (f.receiveUser = :user) " +
                                "or (f.sendUser = :user) and (f.receiveUser = :user2)",
                Long.class)
                .setParameter("user",user)
                .setParameter("user2",user2)
                .getSingleResult();
    }

    public List<User> friendReceive(User user) {
        return em.createQuery("select f.sendUser FROM FriendRequest f  " +
                        "where (f.receiveUser = :user)", User.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Transactional
    public void friendRequestDelete(User user,User me) {
        System.out.println(user.getIdx());
        FriendRequest friendRequest = em.createQuery(
                "select f from FriendRequest f where " +
                        "(f.sendUser = :user) and (f.receiveUser = :me)"
                , FriendRequest.class)
                .setParameter("user",user)
                .setParameter("me",me)
                .getSingleResult(); // USER 로 받아서 USER에 있는 requestList 를 for문으로 돌려야하나?

        em.remove(friendRequest);
    }
}
