package dao.station;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TimeMeasurement {
    Integer period;
    String value;

    Provenance provenance;

    LocalDateTime transactionTime;
    LocalDateTime validTime;
}
