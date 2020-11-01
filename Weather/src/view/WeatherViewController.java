package view;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Model;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import util.AlertUtil;

public class WeatherViewController implements Initializable {
	@FXML
	private Button b;
	@FXML
	private Label temperature;
	@FXML
	private Label ville;
	@FXML
	private Label time;
	@FXML
	private Label humidity;
	@FXML
	private Label precip;
	@FXML
	private Label weather_descriptions;
	

	private Model model;
	private static final String API_KEY = "29f37b3d8eb5e9058c529d9c54fd4e77";
	private static String city = "louvres";
	private static String endpoint = "current";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub



		runAsynchroneOkHttpMethod();
		// temperature.setText(model.getTime()+"°");
	}

	public void runAsynchroneOkHttpMethod() {

		model = new Model();
		model.setApiKey(API_KEY);
		model.setCity(city);
		model.setEndPoint(endpoint);
		model.setUrl("http://api.weatherstack.com/" + model.getEndPoint() + "?access_key=" + model.getApiKey()
				+ "&query=" + model.getCity());
		System.out.println(model.getUrl());
		Request request = new Request.Builder().url(model.getUrl()).build();

		model.getClient().newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						AlertUtil.error("Erreur");
					}
				});

				e.printStackTrace();

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				System.out.println("response.isSuccessful(): " + response.isSuccessful());
				if (response.isSuccessful()) {
					System.out.println("response.code(): " + response.code());
					if (response.code() >= 200 && response.code() < 300) {
						// System.out.println(response.body().string());
						String jsonStrng = response.body().string();
						JSONObject jsonObj;
						try {
							jsonObj = (JSONObject) JSONValue.parseWithException(jsonStrng);
							JSONObject current = (JSONObject) jsonObj.get("current");
							JSONObject location = (JSONObject) jsonObj.get("location");
							
							

							long time = Long.parseLong(location.get("localtime_epoch") + "");
							String country = (String) location.get("country"+"");
							String ville = location.get("name")+"";
							
							JSONArray weather_descriptions = (JSONArray) current.get("weather_descriptions");
							
							double temperature = Double.parseDouble(current.get("temperature") + "");
							double humidity = Double.parseDouble(current.get("humidity") + "");
							double precip = Double.parseDouble(current.get("precip") + "");
							
							// convert seconds to milliseconds
							Date date = new Date(time * 1000L);
							// the format of your date
							SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
							// give a timezone reference for formatting (see comment at the bottom)
							formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

							String formattedDate = formatter.format(date);
							
							model.setTime(formattedDate);
							model.setVille(ville);
							model.setTime(formattedDate);
							model.setPays(country);
							model.setHumidity(humidity);
							model.setTemperature((int)temperature);
							model.setPrecip(precip);
							model.setWeather_descriptions((String) weather_descriptions.get(0));
							
							
							// System.out.println("weather_descriptions: "+weather_descriptions[0]);
						} catch (ParseException e) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									AlertUtil.error("Erreur");
								}
							});
							e.printStackTrace();
						}
						updateFrame();
					} else {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								AlertUtil.error("Erreur");
							}
						});
					}

				}
			}
		});

		// temperature.setText(model.getTime()+"°");
	}
	

	protected void updateFrame() {
		Platform.runLater(new Runnable() {

			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				temperature.setText(model.getTemperature()+"°");
				ville.setText(model.getVille()+", "+model.getPays());
				time.setText("Il est "+model.getTime()+" et la température est de ");
				humidity.setText(model.getHumidity()+"%");
				precip.setText(model.getPrecip()+"%");
				weather_descriptions.setText(model.getWeather_descriptions());
			}
		});
		
		
	}

	public void runAsynchroneManuel() throws Exception {
		new Thread(new Runnable() {

			@Override
			public void run() {

				// TODO Auto-generated method stub
				Request request = new Request.Builder().url(model.getUrl()).build();
				try (Response response = model.getClient().newCall(request).execute()) {
					if (!response.isSuccessful())
						throw new IOException("Unexpected code " + response);

					Headers responseHeaders = response.headers();
					for (int i = 0; i < responseHeaders.size(); i++) {
						System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
					}

					System.out.println(response.body().string());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
}
