import java.io.BufferedReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class weather {
    private static float getCelsius(float kelvin) {
        return kelvin-(float)273.15;
    }
    private static final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: weather [city]");
            System.exit(-1);
        }
        String GET_URL = "http://api.openweathermap.org/data/2.5/weather?q="+args[0]+"&appid=0c9c7f0257d8de19e11f6cf009a9c7ff";
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
            String[] weather_attr = {"description","main"};
            String[] main_attr = {"temp","feels_like","temp_min","temp_max","pressure","humidity"};
            JSONObject json_object = new JSONObject(response.toString());
            JSONArray weather_array = json_object.getJSONArray("weather");
            JSONObject weather_object = weather_array.getJSONObject(0);
            JSONObject main_object = json_object.getJSONObject("main");
            for (int i = 0;i < main_attr.length;i++) {
                if (i == 0) {
                    System.out.println("the temperature in "+args[0]+" is "+String.valueOf(getCelsius(Float.valueOf(main_object.getFloat(main_attr[i]))))+" celsius");
                } else if (i == 1) {
                    System.out.println("the temperature in "+args[0]+" feels like "+String.valueOf(getCelsius(Float.valueOf(main_object.getFloat(main_attr[i]))))+" celsius");
                } else if (i == 2) {
                    System.out.println("the minimum temperature in "+args[0]+" is "+String.valueOf(getCelsius(Float.valueOf(main_object.getFloat(main_attr[i]))))+" celsius");
                } else if (i == 3) {
                    System.out.println("the maximum temperature in "+args[0]+" is "+String.valueOf(getCelsius(Float.valueOf(main_object.getFloat(main_attr[i]))))+" celsius");
                } else if (i == 4) {
                    System.out.println("the pressure in "+args[0]+" is "+main_object.getInt(main_attr[i]));
                } else if (i == 5) {
                    System.out.println("the humidity in "+args[0]+" is "+main_object.getInt(main_attr[i]));
                }
            }
            for (int i = 0;i < weather_attr.length;i++) {
                if (i == 0) {
                    System.out.println("the weather condition in "+args[0]+" is "+weather_object.getString(weather_attr[i]));
                } else {
                    System.out.println("the weather in "+args[0]+" is "+weather_object.getString(weather_attr[i]));
                }
            }
        } else {
            System.out.println("response code is "+String.valueOf(connection.getResponseCode()));
            System.exit(-1);
        }
    }
}
