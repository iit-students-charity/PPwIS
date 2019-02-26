package plagamedicum.ppvis.lab1s4.layouts;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Layout1 extends LayoutBase {
	
	private TextField 			textBox = new TextField();
	private Button 				button = new Button();
	private ComboBox<String> 	comBox = new ComboBox<String>();
	private HBox 				horizontal = new HBox();
	
	public Layout1() {
		
		final String	BUTTON_NAME = "CLICK ON ME";
		
		button.setText(BUTTON_NAME);
		
		comBox.setMaxWidth(75);
		
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
			} else {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle(ERROR_TITLE);
				error.setContentText(ERROR_CONTENT);
				error.show();
			}
		});
	}
}
