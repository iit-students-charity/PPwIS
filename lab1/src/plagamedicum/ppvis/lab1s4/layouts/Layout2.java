package plagamedicum.ppvis.lab1s4.layouts;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Layout2 extends LayoutBase {
	
	private Button 		button1, 
						button2;
	private TextField 	textBox;
	private HBox 		buttons;

	public Layout2() {
		
		final String 	BUTTON_1_NAME = "BUTTON 1", 
						BUTTON_2_NAME = "BUTTON 2";
		
		textBox = new TextField();
		button1 = new Button();
		button1.setText(BUTTON_1_NAME);
		button1.setMaxWidth(94);
		button2 = new Button();
		button2.setText(BUTTON_2_NAME);
		button2.setMaxWidth(94);
		buttons = new HBox();
		buttons.getChildren().addAll(button1,
									 button2);
		
		aligner = new VBox();
		aligner.getChildren().addAll(textBox,
									 buttons);
		
		button1.setOnAction(ae -> {
			
			String	text;
			text = textBox.getText();
			button2.setText(text);
		});
	
		button2.setOnAction(ae -> {
			
			String 	button1Text, 
					button2Text;
	
			button1Text = button1.getText();
			button2Text = button2.getText();
			button1.setText(button2Text);
			button2.setText(button1Text);
		});
	}
}
