package dtalk.repository;

import dtalk.domain.CUTime;
import dtalk.domain.Friend;
import dtalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendRepository {
    private final EntityManager em;

    @Transactional
    public void addFriend(User me,User you){
        Friend meFriend = new Friend();
        Friend youFriend = new Friend();
        CUTime cuTime = new CUTime(LocalDateTime.now());
        meFriend.setCuTime(cuTime);
        meFriend.setMe(me);
        meFriend.setYou(you);

        youFriend.setCuTime(cuTime);
        meFriend.setMe(you);
        meFriend.setYou(me);
    }
    public List<User> friendList(User user) {
        return em.createQuery("select f.you FROM Friend f  " +
                        "where (f.me=:user)", User.class)
                .setParameter("user", user)
                .getResultList();
    }
    public void friendDelete(User me,User you) {
         Friend friend = em.createQuery("select f FROM Friend f  " +
                        "where (f.me=:me) and (f.you=:you)", Friend.class)
                .setParameter("me", me)
                .setParameter("you", you)
                .getSingleResult();
         em.remove(friend);
    }
}
