package plagamedicum.ppvis.lab1s4.layouts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;


public class Layout4 extends LayoutBase {
	
	private TextField 	textBox = new TextField();
	private Button 		button = new Button();
	private CheckBox	chBox1 = new CheckBox(),
						chBox2 = new CheckBox(),
						chBox3 = new CheckBox();
	private ObservableList<CheckBox> chButtonArray = FXCollections.observableArrayList();
	
	public Layout4() {
		
		final String	BUTTON_NAME = "CHECK",
						CHB1_NAME = "1",
						CHB2_NAME = "2",
						CHB3_NAME = "3",
						ERROR_TITLE = "?!?!?!!?!?!",
						ERROR_CONTENT = "There is no specified element here!";
		
		button.setText(BUTTON_NAME);
		chBox1.setText(CHB1_NAME);
		chBox2.setText(CHB2_NAME);
		chBox3.setText(CHB3_NAME);		
		chButtonArray.addAll(chBox1,
							 chBox2,
							 chBox3);
		
		aligner = new VBox();
		aligner.getChildren().addAll(textBox,
									 button);
		aligner.getChildren().addAll(chButtonArray);
		
		button.setOnAction(ae -> {
			
			String input;
			input = textBox.getText();
			boolean match = false;
			for(int i = 0; i < chButtonArray.size(); i++) {
				CheckBox matchButton = chButtonArray.get(i);
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
}
