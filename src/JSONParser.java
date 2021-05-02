import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {
    public static WeatherObject parse(String json) {
        WeatherObject weather = new WeatherObject();
        JSONObject json_object = new JSONObject(json);
        JSONArray weather_array = json_object.getJSONArray("weather");
        JSONObject weather_object = weather_array.getJSONObject(0);
        JSONObject main_object = json_object.getJSONObject("main");
        weather.temp = main_object.getFloat("temp");
        weather.feels_like = main_object.getFloat("feels_like");
        weather.temp_min = main_object.getFloat("temp_min");
        weather.temp_max = main_object.getFloat("temp_max");
        weather.pressure = main_object.getInt("pressure");
        weather.pressure = main_object.getInt("pressure");
        weather.humidity = main_object.getInt("humidity");
        weather.main = weather_object.getString("main");
        weather.description = weather_object.getString("description");
        return weather;
    }
}
