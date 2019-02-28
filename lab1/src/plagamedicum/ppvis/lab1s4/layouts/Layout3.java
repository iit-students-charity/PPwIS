package plagamedicum.ppvis.lab1s4.layouts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Layout3 {
	
	private TextField 			textBox;
	private Button 				button;
	private RadioButton 		radButton1,
								radButton2,
								radButton3;
	private ToggleGroup 		radButtonFamily;
	private ObservableList<RadioButton> radButtonArray;
	private Pane 				aligner;
	
	public Layout3() {
		
		final String	BUTTON_NAME = "CHOOSE!",
						RADB1_NAME = "1",
						RADB2_NAME = "2",
						RADB3_NAME = "3",
						ERROR_TITLE = "!!!!!!!!!",
						ERROR_CONTENT = "Can't find specified element!";
		
		textBox = new TextField();
		button = new Button();
		button.setText(BUTTON_NAME);
		radButton1 = new RadioButton();
		radButton1.setText(RADB1_NAME);
		radButton2 = new RadioButton();
		radButton2.setText(RADB2_NAME);
		radButton3 = new RadioButton();
		radButton3.setText(RADB3_NAME);
		radButtonArray = FXCollections.observableArrayList();
		radButtonArray.addAll(radButton1,
							  radButton2,
							  radButton3);
		radButtonFamily = new ToggleGroup();
		radButtonFamily.getToggles().addAll(radButtonArray);
		
		
		
		aligner = new VBox();
		aligner.getChildren().addAll(textBox,
									 button);
		aligner.getChildren().addAll(radButtonArray);
		
		button.setOnAction(ae -> {
			
			String input;
			input = textBox.getText();
			boolean match = false;
			for(int i = 0; i < radButtonArray.size(); i++) {
				RadioButton matchButton = radButtonArray.get(i);
				if(match = matchButton.getText().equals(input)) {
					matchButton.fire();
					break;
				}
			}
			if(match == false) {
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
