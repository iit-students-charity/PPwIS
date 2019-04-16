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
		Model 	   model 	  = new Model();
		Controller controller = new Controller(model);
		View 	   view 	  = new View(controller);
		
		mainStage = view.getStage();
		mainStage.show();
	}
}

/*TODO:
*
* XML parsing
* 	Save(DOM)
* 	Open(SAX)
* Adding
* pagination
* Randomization in newDoc
* Search
* Delete
* Adding exceptions Alerts
*
* */