package controller;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import lib.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;	

public class Main extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Strike Team Automation Tool");
		showMainMenu();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void showMainMenu() {
		setUpView("MainMenuView.fxml");
	}
	
	public void showGenerateAccounts() {
		setUpView("GenerateAccountsView.fxml");
	}
	
	public void showSearchKnowledgeBase() {		
		setUpView("SearchKnowledgeBaseView.fxml");		
	}
	
	public void showMergeResults() {
		setUpViewWithStage("MergeResultsView.fxml");
	}
	
	private void setUpView(String viewName) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(String.format("/view/%s", viewName)));
			AnchorPane pane = loader.load();
			
			Controller controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(pane);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			
			e.getMessage();
			
		}
	}
	
	private void setUpViewWithStage(String viewName) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(String.format("/view/%s", viewName)));
			AnchorPane pane = loader.load();
			
			Controller controller = loader.getController();
			controller.setMainWithStage(this, primaryStage);
			
			Scene scene = new Scene(pane);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			
			e.getMessage();
			
		}
	}
}
