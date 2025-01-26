package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import model.station.Station;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public abstract class MobilityEntity {
    String id;
    String description;

    Station station;
}
