package dao.mobility;
import lombok.Getter;

@Getter
public enum MobilityType {
    BICYCLE("Bicycle"),
    BICYCLESTATIONBAY("Bicyclestationbay"),
    BIKE_CHARGER("BIKE_CHARGER"),
    BIKE_CHARGER_BAY("BIKE_CHARGER_BAY"),
    BIKE_COUNTER("BikeCounter"),
    BIKE_PARKING("BikeParking"),
    BIKE_PARKING_BAY("BikeParkingBay"),
    BIKE_PARKING_LOCATION("BikeParkingLocation"),
    BIKESHARING_STATION("BikesharingStation"),
    BLUETOOTH_STATION("BluetoothStation"),
    CARPOOLING_HUB("CarpoolingHub"),
    CARPOOLING_SERVICE("CarpoolingService"),
    CARPOOLING_USER("CarpoolingUser"),
    CARSHARING_CAR("CarsharingCar"),
    CARSHARING_STATION("CarsharingStation"),
    COMPANY_GAMIFICATION_ACTION("CompanyGamificationAction"),
    CREATIVE_INDUSTRY("CreativeIndustry"),
    CULTURE("Culture"),
    E_CHARGING_PLUG("EChargingPlug"),
    E_CHARGING_STATION("EChargingStation"),
    ENVIRONMENT_STATION("EnvironmentStation"),
    FLIGHT("Flight"),
    GAMIFICATION_ACTION("GamificationAction"),
    LINK_STATION("LinkStation"),
    METEO_STATION("MeteoStation"),
    MOBILESTATION("Mobilestation"),
    NOI_PLACE("NOI-Place"),
    ON_DEMAND_ITINERARY("ON_DEMAND_ITINERARY"),
    ON_DEMAND_STOP("ON_DEMAND_STOP"),
    ON_DEMAND_VEHICLE("ON_DEMAND_VEHICLE"),
    PARKING_FACILITY("ParkingFacility"),
    PARKING_SENSOR("ParkingSensor"),
    PARKING_STATION("ParkingStation"),
    RWIS_STATION("RWISstation"),
    STREET_STATION("Streetstation"),
    TRAFFIC("traffic"),
    TRAFFIC_DIRECTION("TrafficDirection"),
    TRAFFIC_FORECAST("TrafficForecast"),
    TRAFFIC_SENSOR("TrafficSensor"),
    TRAFFIC_STATION("Trafficstation"),
    TRAFFIC_STREET_FACTOR("TrafficStreetFactor"),
    VMS("VMS"),
    WEATHER_FORECAST("WeatherForecast"),
    WEATHER_FORECAST_SERVICE("WeatherForecastService"),
    WEB_STATISTICS("WebStatistics"),
    WEB_STATSTICS("WebStatstics");

    private final String id;

    MobilityType(String id) {
        this.id = id;
    }
}
