package dtalk.service;

import dtalk.domain.Rank;
import dtalk.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;

    public void rankUpdate(Rank rank){
        rankRepository.rankUpdate(rank);
    }
    public List<Rank> dayRank(){
        return rankRepository.dayRank();
    }
}
