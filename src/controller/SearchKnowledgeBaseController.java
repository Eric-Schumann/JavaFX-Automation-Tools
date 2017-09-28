package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xwpf.extractor.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import lib.Controller;

public class SearchKnowledgeBaseController extends Controller {
	
	//view
	@FXML Button mainMenuButton, openSelectedFileButton;
	@FXML ListView<String> searchResultsListBox;
	@FXML TextField searchField;
	
	private ObservableList<String> documentList = FXCollections.observableArrayList();;
	private File folder;
	private File[] listDir;
	private static String _path = "\\\\mmouixperfile01\\Xperience_Strike_Team\\Knowledge Base Documents"; 
	
	
	@Override
	public void setMain(Main main) {
		this.main = main;
		refreshDirectory();

	}
	
	@FXML
	public void goToMainMenu() {
		main.showMainMenu();
	}
	
	//This method is executed each time user types a letter into the search field.
	//Takes search text and parses each document in the directory looking for that text.
	//Populates list with documents that contain matching text.
	@FXML 
	public void searchDirectory() {
		
		String searchText = searchField.getText().trim().toLowerCase();
		
		if (searchText.isEmpty()) {
			
			refreshDirectory();
			
		} else {
			
			searchResultsListBox.getItems().clear();
			documentList.clear();
			
			for (File file : listDir) {
				
				try {
					
					InputStream is = new FileInputStream(file.getPath());
					XWPFDocument document = new XWPFDocument(is);
					
					@SuppressWarnings("resource")
					XWPFWordExtractor extractor = new XWPFWordExtractor(document);					
					
					String contents = extractor.getText();					
					
					if (contents.toLowerCase().contains(searchText)) {
						documentList.add(file.getName());
					}
										
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
		

		}
		
		searchResultsListBox.setItems(documentList);
		
	}
	
	//Refreshes list of documents when this controller is created and when the search field is empty.
	//A refresh will list all documents in the directory.
	private void refreshDirectory() {
		
		searchResultsListBox.getItems().clear();
		documentList.clear();
		
		try {
			
			folder = new File(_path);
			listDir = folder.listFiles();
			
			for (File file : listDir) {
				documentList.add(file.getName());
			}
			
		} catch (Exception e) {
			System.out.println("Problem");
		}	
		
		
		searchResultsListBox.setItems(documentList);
		
		searchResultsListBox.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	public void openSelectedFile() {
			
		String fileName = searchResultsListBox.getSelectionModel().getSelectedItem();
		
		try {
			
			Desktop.getDesktop().open(new File(String.format(_path + "\\%s", fileName)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
			
	}
}
