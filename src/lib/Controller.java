package lib;
import controller.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public abstract class Controller {
	
	protected Main main;
	protected Stage stage;
	protected Alert alert;
	
	public void setMain(Main main) {
		this.main = main;
		this.stage = null;
	};
	
	public void setMainWithStage(Main main, Stage stage) {
		this.main = main;
		this.stage = stage;
	}
	
	public void generateAlert(AlertType type, String header, String content) {
		alert = new Alert(type);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
}
