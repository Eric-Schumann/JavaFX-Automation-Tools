package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import lib.AccountType;
import lib.Controller;
import model.AccountModel;

public class GenerateAccountsController extends Controller {	
	//view
	@FXML private RadioButton silverLakeRadio, twentyTwentyRadio;
	@FXML private Button generateCSVButton;
	@FXML private TextField loanField, customerField, checkingField, savingsField, timeDepositField;
	
	
	@Override
	public void setMain(Main main) {
		this.main = main;
		ToggleGroup group = new ToggleGroup();
		silverLakeRadio.setToggleGroup(group);
		twentyTwentyRadio.setToggleGroup(group);
		silverLakeRadio.setSelected(true);
	}
	
	public void goToMainMenu() {
		main.showMainMenu();
	}
	
	public void generateCSV() {
		
		List<AccountModel> accountList = new ArrayList<AccountModel>() {
			
			private static final long serialVersionUID = 1L;

		{
			
			add(new AccountModel(AccountType.LOAN, loanField.getText()));
			add(new AccountModel(AccountType.CUSTOMER, customerField.getText()));
			add(new AccountModel(AccountType.CHECKING, checkingField.getText()));
			add(new AccountModel(AccountType.SAVINGS, savingsField.getText()));
			add(new AccountModel(AccountType.TIME_DEPOSIT, timeDepositField.getText()));
			
		}};
		
		if (twentyTwentyRadio.isSelected()) {
			accountList.get(1).setAccountSymbol("$$");
		}
		
		StringBuilder accountsCSV = new StringBuilder();
		
		for (AccountModel account : accountList) {
			for (int i = 0; i < 10; i++) {
				accountsCSV.append(String.format("%s, %s \n", account.getAccountNumber(), account.getAccountSymbol()));
			}
		}
		
		System.out.println(accountsCSV);
		
		
		//saveFile method throws IOException handled here
		try {
			
			saveFile(accountsCSV.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block			
			generateAlert(AlertType.ERROR, e.getCause().toString(), e.getMessage());

		}
		
	}
	
	//Saves Accounts.csv containing generated accounts to the users desktop.
	private void saveFile(String file) throws IOException {
		
		//Grabs users home location.
		Properties p = System.getProperties();
		String userHome = p.getProperty("user.home");
		
		
		//Write file and automatically closer BufferedWriter when finished.
		try ( BufferedWriter out = new BufferedWriter( new FileWriter(String.format("%s\\Desktop\\Accounts.csv", userHome)))) { 
			out.write(file); 
			
			//Alert user if file written successfully and provides them with location where it is saved.
			generateAlert(AlertType.INFORMATION, "Success", String.format("Accounts.csv saved to the following location: \n%s\\Desktop", userHome));

		}
		
	}
}
