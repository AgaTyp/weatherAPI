package pl.agntyp.weatherapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/getWeather")
    public String getWeather(@RequestParam String city, Model model) {
        WeatherData weatherData = weatherService.getResponse(city);
        model.addAttribute("weather", weatherData);

        return "weather";
    }

    @GetMapping("/error")
    public String error() {
        return "redirect:/error";
    }
}
