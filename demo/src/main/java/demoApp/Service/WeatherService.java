package demoApp.Service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String API_KEY = "71d4f47b8f69d0997feac22f4850ccc8"; // Substitua aqui
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public double getTemperatureByCity(String city) {
        String url = BASE_URL + "?q=" + city + "&units=metric&appid=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("main")) {
            throw new RuntimeException("Não foi possível obter a temperatura.");
        }

        Map main = (Map) response.get("main");
        return (double) main.get("temp");
    }

}
