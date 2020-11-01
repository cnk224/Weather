package util;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class AlertUtil {
	public static void error(String headerText, String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Erreur");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);

		alert.showAndWait();
	}
	
	public static void error(String headerText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Erreur");
		alert.setHeaderText(headerText);
		alert.setContentText("Une erreur est survenu veuillez vous assurer des informations renseignés");

		alert.showAndWait();
	}
}
