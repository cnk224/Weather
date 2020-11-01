package model;

import okhttp3.OkHttpClient;

public class Model {
	private final OkHttpClient client = new OkHttpClient();

	private String url;
	private String time;
	private String endPoint;
	private String apiKey;
	private String city;
	private int temperature;
	private String ville;
	private String pays;
	private Double humidity;
	private double precip;
	private String weather_descriptions;
	
	
	
	public String getWeather_descriptions() {
		return weather_descriptions;
	}

	public void setWeather_descriptions(String weather_descriptions) {
		this.weather_descriptions = weather_descriptions;
	}

	public double getPrecip() {
		return precip;
	}

	public void setPrecip(double precip) {
		this.precip = precip;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Model() {
		//this.url = "http://api.weatherstack.com/" + endPoint + "?access_key=" + apiKey + "&query=" + city;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public OkHttpClient getClient() {
		return client;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	

	
}
