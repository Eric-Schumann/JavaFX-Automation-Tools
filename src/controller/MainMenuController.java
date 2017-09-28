package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lib.Controller;

public class MainMenuController extends Controller {
		
	//view
	@FXML Button goToAccountsControllerButton, goToMergeControllerButton, goToSearchKnowledgeBaseController;
	
	@FXML
	public void launchAccountsGenerator() {
		main.showGenerateAccounts();
	}
	
	@FXML
	public void launchMergeResults() {
		main.showMergeResults();
	}
	
	@FXML
	public void launchSearchKnowledgeBase() {
		main.showSearchKnowledgeBase();
	}
}
