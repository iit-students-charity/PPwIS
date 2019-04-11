package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Application;
import javafx.stage.Stage;
import plagamedicum.ppvis.lab2s4.Controller.Controller;
import plagamedicum.ppvis.lab2s4.model.Model;


public class MainWindow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage mainStage) {
		final int		STAGE_WIDTH  = 1460,
						STAGE_HEIGHT = 720;
		final String 	STAGE_TITLE  = "Lab2";

		Model model = new Model();
		Controller controller = new Controller(model);
		MainScene scene = new MainScene(controller);
		
		mainStage.setWidth (STAGE_WIDTH);
		mainStage.setHeight(STAGE_HEIGHT);
		mainStage.setTitle (STAGE_TITLE);
		mainStage.setScene(scene.getScene());
		mainStage.show();
	}
}

/* TODO(NOT)
* В контролере где-то 6 методов:
* 	Добавить там
* 	Найти там
* 	Удалить там
* 	итп там...
*
* Окна общаются с окнами и не знают о реализации друг-друга
*/