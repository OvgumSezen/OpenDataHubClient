package dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import dao.station.Station;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MobilityEntity {
    String id;
    String description;

    Station station;
}
