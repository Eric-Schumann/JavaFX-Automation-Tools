package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import lib.Controller;
import model.PerformanceEvent;

public class MergeResultsController extends Controller {

	@FXML Button rawButton, templateButton, showMainMenuButton, mergeButton;
	@FXML TextField rawTextField, templateTextField;
	
	private FileChooser chooser; 
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	@FXML
	public void getTemplate() {
		
		chooser = new FileChooser();
		File workingFile = chooser.showOpenDialog(this.stage);
		
		if (!workingFile.getName().contains(".xlsx")) {
					
			generateAlert(AlertType.WARNING, "PC LOAD LETTER", "This file must be a .xlsx file.");		
			
		} else {
			
			templateTextField.setText(workingFile.getAbsolutePath());
			try {
				
				workbook = new XSSFWorkbook(workingFile);
				sheet = workbook.getSheetAt(2);
				
			} catch (InvalidFormatException e) {
				
				generateAlert(AlertType.ERROR, e.getMessage(), e.getLocalizedMessage());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				generateAlert(AlertType.ERROR, e.getMessage(), e.getLocalizedMessage());
			}
			
		}
		

		
		System.out.println("Getting Template!");
	}
	
	@FXML
	public void getRaw() {
		
		chooser = new FileChooser();
		File workingFile = chooser.showOpenDialog(this.stage);
		
		if (!workingFile.getName().contains("_raw.txt")) {
			generateAlert(AlertType.WARNING, "PC LOAD LETTER", "This must be the file that ends with _raw.txt");
		} else {
			rawTextField.setText(workingFile.getAbsolutePath());
			
			parseRaw(workingFile);
		}
		System.out.println("Getting Raw!");
	}
	
	@FXML
	public void goToMainMenu () {
		main.showMainMenu();
	}
	
	@FXML 
	public void mergeResults() {
		System.out.println("Results Merged!");
	}	
	
	private void parseRaw(File file) {
		
		FileReader fr;
		List<PerformanceEvent> eventFile;
		List<PerformanceEvent> allEvents;
		
		try {
			
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);			
			
			eventFile = new ArrayList<PerformanceEvent>();
			allEvents = new ArrayList<PerformanceEvent>();
			
			int multiplier = 1;
			
			String fileName = file.getName();
			String line;
			String acct = "";
			String accType = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			sdf.setTimeZone(TimeZone.getTimeZone("CST"));
			String[] fileNameSegments = fileName.split("-");
			String dateString = fileNameSegments[1] + "-" + fileNameSegments[2] + "-" + fileNameSegments[3].substring(0, 2);	
			
			while ((line = br.readLine()) != null) {
				
				String[] segment = line.split(",");				
				Date tempDate = sdf.parse(dateString + " " + segment[0]);
				System.out.println(dateString + " " + segment[0]);
							
				eventFile.add(new PerformanceEvent(){{
					setFileName(fileName);
					setAccount(acct);
					setStartTime(tempDate);
					setDescription(segment[4]);
				}});
			}
			
			br.close();
			
			boolean active = false;
			
			for (int i = 0; i < eventFile.size() ; i++) {
								
				if (eventFile.get(i).getDescription().trim().contains("") && active) {
					
					int z = i;
								
					while (eventFile.get(i).getStartTime().getTime() - eventFile.get(z).getStartTime().getTime() < .1 ) {
						z--;
					}
					

					
				}
					
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			generateAlert(AlertType.ERROR, e.getCause().toString(), e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			generateAlert(AlertType.ERROR, e.getCause().toString(), e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			generateAlert(AlertType.ERROR, e.getCause().toString(), e.getMessage());
		}

		

	}
}
