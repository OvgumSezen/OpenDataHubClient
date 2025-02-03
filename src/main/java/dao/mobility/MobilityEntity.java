package dao.mobility;

import com.fasterxml.jackson.annotation.JsonProperty;
import dao.ODHEntity;
import dao.mobility.station.Station;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MobilityEntity extends ODHEntity {
    private String name;
    @JsonProperty("stations")
    private Map<String, Station> stations;
}
