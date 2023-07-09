package pl.agntyp.weatherapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final String apikey;
    private final RestTemplate restTemplate;
    private static final String RESOURCE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherService(@Value("${app.openweatherkey}") String apikey) {
        this.apikey = apikey;
        this.restTemplate = new RestTemplate();
    }

    public WeatherData getResponse(String city) {

        ResponseEntity<String> response = restTemplate
                .getForEntity(RESOURCE_URL + "?q=" + city + "&units=metric&lang=pl&APPID=" + apikey, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode weather = root.get("weather").get(0);
            String weatherDescription = weather.get("description").textValue();

            JsonNode main = root.get("main");
            double airTemperature = main.get("temp").asDouble();
            double feelingTemperature = main.get("feels_like").asDouble();
            double minTemperature = main.get("temp_min").asDouble();
            double maxTemperature = main.get("temp_max").asDouble();
            double pressure = main.get("pressure").asDouble();
            double humidity = main.get("humidity").asDouble();
            double windSpeed = root.get("wind").get("speed").asDouble();
            return new WeatherData(city, weatherDescription, airTemperature, feelingTemperature,
                    minTemperature, maxTemperature, pressure, humidity, windSpeed);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
