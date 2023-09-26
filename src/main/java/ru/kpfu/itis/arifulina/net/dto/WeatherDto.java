package ru.kpfu.itis.arifulina.net.dto;

public class WeatherDto {
    private String temperature;
    private String humidity;
    private String precipitation;

    public WeatherDto(String temperature, String humidity, String precipitation) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPrecipitation() {
        return precipitation;
    }
}
