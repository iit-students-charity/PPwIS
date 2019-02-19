import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Lab1 extends Application{

	Group layout = new Group();

	VBox vertice = new VBox();

	TextField textBox = new TextField();

	HBox buttons = new HBox();
	Button 	button1 = new Button(),
			button2 = new Button();
	
	public static void main(String args[]){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage)throws Exception{
		final String 	BUTTON_1_NAME = "BUTTON 1",
						BUTTON_2_NAME = "BUTTON 2",
						STAGE_TITLE = "Lab1";

		final int 	SCENE_WIDTH = 300,
					SCENE_HEIGHT = 250;

		layout.getChildren().add(vertice);

		vertice.getChildren().add(textBox);
		vertice.getChildren().add(buttons);

		button1.setText(BUTTON_1_NAME);
		button2.setText(BUTTON_2_NAME);
		buttons.getChildren().add(button1);
		buttons.getChildren().add(button2);

		button1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent ae){
				String text;
				text = textBox.getText();
				button2.setText(text);
			}
		});

		button2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent ae){
				String 	button1Text,
						button2Text;

				button1Text = button1.getText();
				button2Text = button2.getText();
				button1.setText(button2Text);
				button2.setText(button1Text);
			}
		});

		Scene scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle(STAGE_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}