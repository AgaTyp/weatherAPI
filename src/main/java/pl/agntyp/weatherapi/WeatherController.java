package pl.agntyp.weatherapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home(Model model) {
        WeatherData weatherData = new WeatherData();

        model.addAttribute("weatherCity", weatherData.getCity());

        return "home";
    }

    @PostMapping("/getWeather")
    public String getWeather(String weatherCity) {
        WeatherData weatherData = weatherService.getResponse(weatherCity);

        return "redirect:/";
    }
}
