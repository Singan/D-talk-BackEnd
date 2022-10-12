package dtalk.dto.rank;

import dtalk.domain.Quiz;
import dtalk.domain.Rank;
import lombok.Data;

@Data
public class RankDTO implements Comparable<RankDTO>{
    private String thumbImg;
    private Long recommend;
    private int rank;
    public static RankDTO createRankDTO(String thumbImg,Long recommend){
        RankDTO rankDTO = new RankDTO();

        rankDTO.setRecommend(recommend);
        rankDTO.setThumbImg(thumbImg);
        return rankDTO;
    }


    @Override
    public int compareTo(RankDTO o) {
        if (this.getRecommend() <o.getRecommend()){
            return -1;
        }else if(this.getRecommend() >o.getRecommend()){
            return 1;
        }
        return 0;
    }
}
