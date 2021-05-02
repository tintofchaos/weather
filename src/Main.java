import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: weather [city]");
            System.exit(-1);
        }
        String city = args[0];
        WeatherObject weather_object = WeatherProvider.getWeather(city);
        if (weather_object == null) {
            System.out.println("can't get weather");
            System.exit(-1);
        }
        System.out.println("the weather condition in "+city+" is "+weather_object.description);
        System.out.println("the weather in "+city+" is "+weather_object.main);
        System.out.println("the temperature in "+city+" is "+weather_object.temp+" celsius");
        System.out.println("the temperature in "+city+" feels like "+weather_object.feels_like+" celsius");
        System.out.println("the minimum temperature in "+city+" is "+weather_object.temp_min+" celsius");
        System.out.println("the maximum temperature in "+city+" is "+weather_object.temp_max+" celsius");
        System.out.println("the pressure in "+city+" is "+weather_object.pressure);
        System.out.println("the humidity in "+city+" is "+weather_object.humidity);
    }
}
