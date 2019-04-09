package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;


public class MainScene {
    private Scene scene;
	private VBox root;

	public MainScene() {
	    final String FILE_MENU_LABEL        = "File",
                     NEW_MENU_ITEM_LABEL    = "New",
                     SAVE_MENU_ITEM_LABEL   = "Save",
                     OPEN_MENU_ITEM_LABEL   = "Open",
                     EXIT_MENU_ITEM_LABEL   = "Exit",
                     EDIT_MENU_LABEL        = "Edit",
                     SEARCH_MENU_ITEM_LABEL = "Search",
                     DELETE_MENU_ITEM_LABEL = "Delete",
	                 TO_BEGIN_BUTTON_LABEL  = "<<",
                     TO_LEFT_BUTTON_LABEL   = "<",
                     TO_RIGHT_BUTTON_LABEL  = ">",
                     TO_END_BUTTON_LABEL    = ">>";
        MenuItem searchMenuItem = new MenuItem(SEARCH_MENU_ITEM_LABEL),
                 deleteMenuItem = new MenuItem(DELETE_MENU_ITEM_LABEL),
                 newMenuItem    = new MenuItem(NEW_MENU_ITEM_LABEL),
                 saveMenuItem   = new MenuItem(SAVE_MENU_ITEM_LABEL),
                 openMenuItem   = new MenuItem(OPEN_MENU_ITEM_LABEL),
                 exitMenuItem   = new MenuItem(EXIT_MENU_ITEM_LABEL);
        Menu     fileMenu       = new Menu(FILE_MENU_LABEL),
                 editMenu       = new Menu(EDIT_MENU_LABEL);
        MenuBar  menuBar        = new MenuBar();
        Button   toBeginButton  = new Button(TO_BEGIN_BUTTON_LABEL),
                 toLeftButton   = new Button(TO_LEFT_BUTTON_LABEL),
                 toRightButton  = new Button(TO_RIGHT_BUTTON_LABEL),
                 toEndButton    = new Button(TO_END_BUTTON_LABEL);
        //TODO: MORE BUTTONS!!!!
        //TODO: Some windows
        HBox     instruments    = new HBox(),
                 pagination     = new HBox();
        MainTable mainTable     = new MainTable();

        root = new VBox();

        exitMenuItem.setOnAction(ae -> Platform.exit());
        fileMenu.getItems().addAll(newMenuItem,
                                   saveMenuItem,
                                   openMenuItem,
                                   new SeparatorMenuItem(),
                                   exitMenuItem);
        editMenu.getItems().addAll(searchMenuItem,
                                   deleteMenuItem);
        menuBar.getMenus().addAll(fileMenu,
                                  editMenu);
        pagination.getChildren().addAll(toBeginButton,  //TODO: Pages Counter
                                        toLeftButton,
                                        toRightButton,
                                        toEndButton);

        root.getChildren().addAll(menuBar,
                                  instruments,
                                  mainTable.get(),
                                  pagination);

        scene	= new Scene(root);
	}
	
	public Scene getScene() {
		return scene;
	}
}
