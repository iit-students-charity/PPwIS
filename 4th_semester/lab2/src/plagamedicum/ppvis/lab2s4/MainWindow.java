package plagamedicum.ppvis.lab2s4;

import javafx.application.Application;
import javafx.stage.Stage;
import plagamedicum.ppvis.lab2s4.Controller.Controller;
import plagamedicum.ppvis.lab2s4.model.Model;
import plagamedicum.ppvis.lab2s4.view.View;

public class MainWindow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage mainStage) {
		Model 	   model 	  = new Model(8, 0);
		Controller controller = new Controller(model);
		View view 	  = new View(controller);
		
		mainStage = view.getStage();
		mainStage.show();
	}
}

/*TODO:
*
*?-	по среднему балу (средний бал задается 2 мя числами нижним пределом и верхним пределом) и фамилии студента;?
*
* move from view to controller
*
* Adding exceptions Alerts
*
*/