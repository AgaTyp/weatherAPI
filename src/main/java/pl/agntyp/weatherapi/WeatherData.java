package pl.agntyp.weatherapi;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private String city;
    private String weatherDescription;
    private double airTemperature;
    private double feelingTemperature;
    private double minTemperature;
    private double maxTemperature;
    private double pressure;
    private double humidity;
    private double windSpeed;

}
