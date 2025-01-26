package model.station;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Station {
    String name;
    String origin;
    String type;
    String code;

    Boolean active;
    Boolean available;

    Coordinate coordinate;
    Datatypes datatype;

    Station parent;
}
