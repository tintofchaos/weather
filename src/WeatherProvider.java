import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherProvider {
    private static float getCelsius(float kelvin) {
        return kelvin-(float)273.15;
    }
    private static final String USER_AGENT = "Mozilla/5.0";
    public static WeatherObject getWeather(String city) throws IOException {
        String GET_URL = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=0c9c7f0257d8de19e11f6cf009a9c7ff";
        URL obj = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = inputReader.readLine()) != null) {
                response.append(inputLine);
            }
            inputReader.close();
            WeatherObject weather_object = JSONParser.parse(response.toString());
            weather_object.temp_max = getCelsius(weather_object.temp_max);
            weather_object.temp_min = getCelsius(weather_object.temp_min);
            weather_object.temp = getCelsius(weather_object.temp);
            weather_object.feels_like = getCelsius(weather_object.feels_like);
            return weather_object;
        } else {
            return null;
        }
    }
}
