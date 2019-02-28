package plagamedicum.ppvis.lab1s4.layouts;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Layout1 {
	
	private TextField 			textBox;
	private Button 				button;
	private ComboBox<String> 	comBox;
	private HBox 				horizontal;
	private Pane 				aligner;
	
	public Layout1() {
		
		final String	BUTTON_NAME = "CLICK ON ME";
		
		textBox = new TextField();
		button = new Button();
		button.setText(BUTTON_NAME);
		comBox = new ComboBox<String>();
		comBox.setMaxWidth(75);
		horizontal = new HBox();
		horizontal.getChildren().addAll(comBox,
										button);
		
		aligner = new VBox();
		aligner.getChildren().addAll(textBox, 
									 horizontal);
		
		button.setOnAction(ae -> {
			
			boolean			elementExists;
			String			textFromTextBox;
			final String 	ERROR_TITLE = "^&*#@%&#",
							ERROR_CONTENT = "This element already exists!";
			
			textFromTextBox = textBox.getText();
			
			elementExists = comBox.getItems().contains(textFromTextBox) ? 
							true : false;
			
			if(elementExists == false) {
				comBox.getItems().add(textFromTextBox);
				comBox.setValue(textFromTextBox);
			} else {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle(ERROR_TITLE);
				error.setContentText(ERROR_CONTENT);
				error.show();
			}
		});
	}
	
	public Pane getAligner() {
		
		return aligner;
	}
}
