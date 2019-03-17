package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainWindow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage mainStage) {
		final int 	 STAGE_WIDTH  = 1460,
				  	 STAGE_HEIGHT = 720;
		final String STAGE_TITLE  = "Lab2";
		MainScene scene = new MainScene();
		
		mainStage.setWidth (STAGE_WIDTH);
		mainStage.setHeight(STAGE_HEIGHT);
		mainStage.setTitle (STAGE_TITLE);
		mainStage.setScene(scene.getScene());
		mainStage.show();
	}
}
