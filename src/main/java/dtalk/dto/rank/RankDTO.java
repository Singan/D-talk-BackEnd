package dtalk.dto.rank;

import dtalk.domain.Quiz;
import dtalk.domain.Rank;
import lombok.Data;

@Data
public class RankDTO implements Comparable<RankDTO>{
    private String thumbImg;
    private Long like;
    private int rank;
    public static RankDTO createRankDTO(String thumbImg,Long like){
        RankDTO rankDTO = new RankDTO();

        rankDTO.setLike(like);
        rankDTO.setThumbImg(thumbImg);
        return rankDTO;
    }


    @Override
    public int compareTo(RankDTO o) {
        if (this.getLike() <o.getLike()){
            return -1;
        }else if(this.getLike() >o.getLike()){
            return 1;
        }
        return 0;
    }
}
