package plagamedicum.ppvis.lab1s4.layouts;

import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import plagamedicum.ppvis.lab1s4.tableelement.*;


public class Layout5 extends LayoutBase {
	
	private TextField 	textBox = new TextField();
	private Button		addButton = new Button(),
						leftButton = new Button(),
						rightButton = new Button();
	private HBox 		buttons = new HBox();
	private TableView<TableElement> table;
	private TableColumn<TableElement, String> 	column1,
												column2;
	ObservableList<TableElement> tableElementArray = FXCollections.observableArrayList();
	
	public Layout5() {
		
		final String	addButton_NAME = "v",
						LEFT_BUTTON_NAME = "<",
						RIGHT_BUTTON_NAME = ">",
						COLUMN1_NAME = "1",
						COLUMN2_NAME = "2";
		
		addButton.setText(addButton_NAME);
		leftButton.setText(LEFT_BUTTON_NAME);
		rightButton.setText(RIGHT_BUTTON_NAME);		
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
		table.getColumns().addAll(column1,
								  column2);
		table.setMaxHeight(150);
		
		aligner = new VBox();
		aligner.getChildren().addAll(textBox,
									 buttons,
									 table);
		
		table.setItems(tableElementArray);
		
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
				selectedElement.left();
			}
		});

		rightButton.setOnAction(ae -> {
			
			TableElement selectedElement = table.getSelectionModel().getSelectedItem();
			
			if(selectedElement != null) {
				selectedElement.right();
			}
		});
	}
}
