package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import plagamedicum.ppvis.lab2s4.Controller.Controller;
import plagamedicum.ppvis.lab2s4.model.Student;


public class MainScene {
    private Scene     scene;
	private VBox      root;
	private MainTable mainTable;
    private ObservableList<Student> studentList;

	public MainScene(Controller controller) {
	    final String FILE_MENU_LABEL        = "Файл",
                     NEW_MENU_ITEM_LABEL    = "Новы",
                     SAVE_MENU_ITEM_LABEL   = "Захаваць",
                     OPEN_MENU_ITEM_LABEL   = "Адкрыць",
                     EXIT_MENU_ITEM_LABEL   = "Выйсці",
                     EDIT_MENU_LABEL        = "Рэдагаваць",
                     SEARCH_MENU_ITEM_LABEL = "Шукаць",
                     DELETE_MENU_ITEM_LABEL = "Выдаліць",
                     ADD_BUTTON_LABEL       = "Дадаць",
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
        Label    pagesCounter   = new Label();
        Button   addButton      = new Button(ADD_BUTTON_LABEL),
                 toBeginButton  = new Button(TO_BEGIN_BUTTON_LABEL),
                 toLeftButton   = new Button(TO_LEFT_BUTTON_LABEL),
                 toRightButton  = new Button(TO_RIGHT_BUTTON_LABEL),
                 toEndButton    = new Button(TO_END_BUTTON_LABEL);
        //TODO: Some windows
        HBox     instruments    = new HBox(),
                 pagination     = new HBox();

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

        this.studentList = controller.getStudentList();
        mainTable = new MainTable(this.studentList);

        pagination.getChildren().addAll(toBeginButton,
                                        toLeftButton,
                                        pagesCounter,
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
