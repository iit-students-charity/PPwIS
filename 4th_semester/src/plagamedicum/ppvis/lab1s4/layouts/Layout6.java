package plagamedicum.ppvis.lab1s4.layouts;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Layout6 {
	
	private TextField 			textBox;
	private Button 				addButton,
								startButton,
								stopButton;
	private ComboBox<String> 	comBox;
	private HBox 				startStopHorizon;
	private Pane 				aligner;
	private SwitcherThread 		opSwThread;
	private volatile boolean	iterateSwitcher = false;
	
	private final int			SLEEP_TIME = 500;

	
	public class SwitcherThread extends Thread{
		
		@Override
		public void run() {
			
			String 	current;
			int		i = 0;

			iterateSwitcher = true;
			while(iterateSwitcher) {
				current = comBox.getItems().get(i);
				comBox.setValue(current);
				i++;
				if(i == comBox.getItems().size()) {
					i = 0;
				}
				try{
					Thread.sleep(SLEEP_TIME);
				}catch(InterruptedException e){}
			}
		}
	}
	
	public Layout6() {
		
		final String	ADD_BUTTON_NAME = "ADD OPTION",
						START_BUTTON_NAME = "START",
						STOP_BUTTON_NAME = "STOP";
		
		textBox = new TextField();
		addButton = new Button();
		addButton.setText(ADD_BUTTON_NAME);
		startButton = new Button();
		startButton.setText(START_BUTTON_NAME);
		stopButton = new Button();
		stopButton.setText(STOP_BUTTON_NAME);
		comBox = new ComboBox<String>();
		comBox.setMaxWidth(120);
		comBox.setEditable(true);
		startStopHorizon = new HBox();
		startStopHorizon.getChildren().addAll(startButton,
											  stopButton);
		
		aligner = new VBox();
		aligner.getChildren().addAll(textBox, 
									 comBox,
									 addButton,
									 startStopHorizon);
		
		addButton.setOnAction(ae -> {
			
			boolean			elementExists;
			String			textFromTextBox;
			final String 	ERROR_TITLE = "^&*#@%&#",
							ERROR_CONTENT = "This element already exists!";
			
			textFromTextBox = textBox.getText();
			
			elementExists = comBox.getItems().contains(textFromTextBox) ? 
							true : false;
			
			if(elementExists == false) {
				comBox.getItems().add(textFromTextBox);	
				comBox.setValue(textFromTextBox);
			} else {
				Alert error = new Alert(AlertType.WARNING);
				error.setTitle(ERROR_TITLE);
				error.setContentText(ERROR_CONTENT);
				error.show();
			}
		});
		
		startButton.setOnAction(ae -> {
			
			opSwThread = new SwitcherThread();
			iterateSwitcher = false;
			opSwThread.start();
		});
		
		stopButton.setOnAction(ae -> {
			
			iterateSwitcher = false;
			opSwThread.interrupt();
		});
	}
	
	public Pane getAligner() {
		
		return aligner;
	}
}
