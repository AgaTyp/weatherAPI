package pl.agntyp.weatherapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final String API_KEY = "*****";
    //    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private static final String RESOURCE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    public WeatherData getResponse(String city) {
//        String city = "Warszawa";

//        URI uri = new URI();

        ResponseEntity<String> response = restTemplate
                .getForEntity(RESOURCE_URL + "?q="+city+ "&units=metric&lang=pl&APPID=" + API_KEY, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
//            JsonNode wind = root.get("wind").get("speed");
            JsonNode name = root.get("weather").get(0);
            String weatherDescription = name.get("description").textValue();

            double airTemperature = root.get("main").get("temp").asDouble();
            double feelingTemperature = root.get("main").get("feels_like").asDouble();
            double minTemperature = root.get("main").get("temp_min").asDouble();
            double maxTemperature = root.get("main").get("temp_max").asDouble();
            double pressure = root.get("main").get("pressure").asDouble();
            double humidity = root.get("main").get("humidity").asDouble();
            double windSpeed = root.get("wind").get("speed").asDouble();
            return new WeatherData(city, weatherDescription, airTemperature, feelingTemperature,
                    minTemperature, maxTemperature, pressure, humidity, windSpeed);
//            System.out.println(windSpeed);
//            System.out.println(weatherDescription);
        } catch (JsonProcessingException ignored) {

        }

        return null;
    }


}
