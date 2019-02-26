package plagamedicum.ppvis.lab1s4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import plagamedicum.ppvis.lab1s4.layouts.*;


public class Lab1 extends Application {
	
	public FlowPane root;
	
	public static void main(String args[]) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		final String 	STAGE_TITLE = "Lab1";
		final int 		SCENE_WIDTH = 1050, 
						SCENE_HEIGHT = 500;
		final double 	HORIZ_GAP 	= 10,
						VERT_GAP 	= 15;
		
		Layout1 l1 = new Layout1();
		Layout2	l2 = new Layout2();
		Layout3	l3 = new Layout3();
		Layout4	l4 = new Layout4();
		Layout5	l5 = new Layout5();
		
		root = new FlowPane(HORIZ_GAP, VERT_GAP);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(l1.getAligner(),
								  l2.getAligner(),
								  l3.getAligner(),
								  l4.getAligner(),
								  l5.getAligner());		

		Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle(STAGE_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
