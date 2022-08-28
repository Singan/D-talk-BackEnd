package dtalk.dto.rank;

import dtalk.domain.Quiz;
import lombok.Data;

@Data
public class RankDTO {
    private Quiz quiz;
    private Long like;
    private Long rank;
}
