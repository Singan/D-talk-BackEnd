package dtalk.repository;

import dtalk.domain.Friend;
import dtalk.domain.Quiz;
import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendRepository {
    private final EntityManager em;
    @Transactional
    public Long friendSend(Friend friend) {
        em.persist(friend);
        return friend.getIdx();
    }
    public Long friendCount(User user1 ,User user2){
        return em.createQuery("select count(f) from Friend f where" +
                                "(f.sendUser = :user1 and f.receiveUser = :user2) or " +
                                "( f.receiveUser = :user2 and f.sendUser = :user1)"
                        ,Long.class).
                setParameter("user1",user1).
                setParameter("user2",user2).
                getSingleResult();
    };
}
