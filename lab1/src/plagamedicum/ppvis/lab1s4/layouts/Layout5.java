package plagamedicum.ppvis.lab1s4.layouts;

import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import plagamedicum.ppvis.lab1s4.tableelement.*;


public class Layout5 {
	
	private TextField 	textBox;
	private Button		addButton,
						leftButton,
						rightButton;
	private HBox 		buttons;
	private TableView<TableElement> 			table;
	private TableColumn<TableElement, String> 	column1,
												column2;
	ObservableList<TableElement> tableElementArray = FXCollections.observableArrayList();
	private Pane 		aligner;
	
	public Layout5() {
		
		final String	ADD_BUTTON_NAME = "v",
						LEFT_BUTTON_NAME = "<",
						RIGHT_BUTTON_NAME = ">",
						COLUMN1_NAME = "1",
						COLUMN2_NAME = "2";
		
		textBox = new TextField();	
		addButton = new Button();
		addButton.setText(ADD_BUTTON_NAME);
		leftButton = new Button();
		leftButton.setText(LEFT_BUTTON_NAME);
		rightButton = new Button();
		rightButton.setText(RIGHT_BUTTON_NAME);
		buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(addButton,
									 leftButton,
									 rightButton);
		
		column1 = new TableColumn<>(COLUMN1_NAME);
		column1.setCellValueFactory(new PropertyValueFactory<>("pos1"));
		column1.setSortable(false);
		column1.setMinWidth(123);
		column2 = new TableColumn<>(COLUMN2_NAME);
		column2.setCellValueFactory(new PropertyValueFactory<>("pos2"));
		column2.setSortable(false);
		column2.setMinWidth(123);
		
		table = new TableView<>(tableElementArray);
		table.getColumns().add(column1);
		table.getColumns().add(column2);
		table.setMaxHeight(150);
		
		aligner = new VBox();
		aligner.getChildren().addAll(textBox,
									 buttons,
									 table);
		
		addButton.setOnAction(ae -> {
			
			String input;
			
			input = textBox.getText();
			if(input.length() > 0) {
				TableElement newElement = new TableElement(input);
				tableElementArray.add(newElement);
			}
		});
		
		leftButton.setOnAction(ae -> {
			
			TableElement selectedElement = table.getSelectionModel().getSelectedItem();
			
			if(selectedElement != null) {
				selectedElement.goLeft();
			}
		});

		rightButton.setOnAction(ae -> {
			
			TableElement selectedElement = table.getSelectionModel().getSelectedItem();
			
			if(selectedElement != null) {
				selectedElement.goRight();
			}
		});
	}
	
	public Pane getAligner() {
		
		return aligner;
	}
}
