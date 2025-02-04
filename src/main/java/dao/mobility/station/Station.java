package dao.mobility.station;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Station {
    @JsonProperty("sactive")
    Boolean sActive;
    @JsonProperty("savailable")
    Boolean sAvailable;
    @JsonProperty("scode")
    String sCode;
    @JsonProperty("sname")
    String sName;
    @JsonProperty("sorigin")
    String sOrigin;
    @JsonProperty("stype")
    String sType;

    @JsonProperty("scoordinate")
    Map<String, Object> sCoordinate;
    @JsonProperty("sdatatypes")
    Map<String, Object> sDataTypes;
    @JsonProperty("smetadata")
    Map<String, Object> sMetadata;
}
