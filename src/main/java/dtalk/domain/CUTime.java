package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
@Getter
@Embeddable public class CUTime {
    private LocalDateTime createdAt;

    public CUTime() {
    }

    public CUTime(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
