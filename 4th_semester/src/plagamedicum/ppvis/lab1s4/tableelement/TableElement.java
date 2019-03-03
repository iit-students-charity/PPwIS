package plagamedicum.ppvis.lab1s4.tableelement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TableElement {
	
	private String			EMPTY = "";
	private StringProperty 	pos1,
							pos2;
	
	public TableElement (String text) {
		
		pos1 = new SimpleStringProperty(text);
	}
	
	public StringProperty pos1Property() {
		
		if(pos1 == null) {
			pos1 = new SimpleStringProperty(EMPTY);
		}
		return pos1;
	}
	
	public StringProperty pos2Property() {
		
		if(pos2 == null) {
			pos2 = new SimpleStringProperty(EMPTY);
		}
		return pos2;
	}
	
	public void goLeft() {
		
		if(pos2.get() != EMPTY) {
			pos1.set(pos2.get());;
			pos2.set(EMPTY);
		}
	}
	
	public void goRight() {
		
		if(pos1.get() != EMPTY) {
			pos2.set(pos1.get());;
			pos1.set(EMPTY);
		}
	}
}
