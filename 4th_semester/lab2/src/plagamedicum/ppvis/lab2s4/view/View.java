package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import plagamedicum.ppvis.lab2s4.Controller.Controller;

import java.io.File;

public class View {
    private Scene        scene;
	private VBox         root;
	private TableElement tableElement;
    private Controller   controller;
    private Stage        stage;

	public View(Controller controller) {
        final int    STAGE_WIDTH  = 1460,
                     STAGE_HEIGHT = 750;
        final String STAGE_TITLE  = "Lab2";

        this.controller = controller;
        init();
        stage = new Stage();
        stage.setWidth (STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setTitle (STAGE_TITLE);
        stage.setScene(scene);
	}

	private void init(){
        final int    TABLE_HEIGHT                 = 600;
        final String FILE_MENU_LABEL              = "Файл",
                     EDIT_MENU_LABEL              = "Рэдагаваць",
                     NEW_DOC_MENU_ITEM_LABEL      = "Новы дакумент",
                     OPEN_DOC_MENU_ITEM_LABEL     = "Адкрыць дакумент",
                     SAVE_DOC_MENU_ITEM_LABEL     = "Захаваць дакумент",
                     ADD_ITEM_MENU_ITEM_LABEL     = "Дадаць запіс",
                     SEARCH_ITEMS_MENU_ITEM_LABEL = "Шукаць запісы",
                     DELETE_ITEMS_MENU_ITEM_LABEL = "Выдаліць запісы",
                     CLOSE_APP_MENU_ITEM_LABEL    = "Выйсці",
                     NEW_DOC_BUTTON_LABEL         = "Новы дакумент",
                     OPEN_DOC_BUTTON_LABEL        = "Адкрыць дакумент",
                     SAVE_DOC_BUTTON_LABEL        = "Захаваць дакумент",
                     ADD_BUTTON_LABEL             = "Дадаць запіс",
                     SEARCH_ITEMS_BUTTON_LABEL    = "Шукаць запісы",
                     DELETE_ITEMS_BUTTON_LABEL    = "Выдаліць запісы";
        MenuItem newDocMenuItem      = new MenuItem(NEW_DOC_MENU_ITEM_LABEL),
                 openDocMenuItem     = new MenuItem(OPEN_DOC_MENU_ITEM_LABEL),
                 saveMenuItem        = new MenuItem(SAVE_DOC_MENU_ITEM_LABEL),
                 addItemMenuItem     = new MenuItem(ADD_ITEM_MENU_ITEM_LABEL),
                 searchItemsMenuItem = new MenuItem(SEARCH_ITEMS_MENU_ITEM_LABEL),
                 deleteItemsMenuItem = new MenuItem(DELETE_ITEMS_MENU_ITEM_LABEL),
                 closeAppMenuItem    = new MenuItem(CLOSE_APP_MENU_ITEM_LABEL);
        Menu     fileMenu            = new Menu(FILE_MENU_LABEL),
                 editMenu            = new Menu(EDIT_MENU_LABEL);
        MenuBar  menuBar             = new MenuBar();
        Button   newDocButton        = new Button(NEW_DOC_BUTTON_LABEL),
                 openDocButton       = new Button(OPEN_DOC_BUTTON_LABEL),
                 saveDocButton       = new Button(SAVE_DOC_BUTTON_LABEL),
                 addItemButton       = new Button(ADD_BUTTON_LABEL),
                 searchItemsButton   = new Button(SEARCH_ITEMS_BUTTON_LABEL),
                 deleteItemsButton   = new Button(DELETE_ITEMS_BUTTON_LABEL);
        HBox     instruments         = new HBox();

        fileMenu.getItems().addAll(
                newDocMenuItem,
                openDocMenuItem,
                saveMenuItem,
                new SeparatorMenuItem(),
                closeAppMenuItem);
        editMenu.getItems().addAll(
                addItemMenuItem,
                new SeparatorMenuItem(),
                searchItemsMenuItem,
                deleteItemsMenuItem);
        menuBar.getMenus().addAll(
                fileMenu,
                editMenu);

        instruments.getChildren().addAll(
                newDocButton,
                openDocButton,
                saveDocButton,
                new Separator(),
                addItemButton,
                searchItemsButton,
                deleteItemsButton);

        tableElement = new TableElement(controller);
        tableElement.getTable().setMinHeight(TABLE_HEIGHT);

        root = new VBox();
        root.getChildren().addAll(
                menuBar,
                instruments,
                tableElement.get());
        scene = new Scene(root);

        newDocButton.setOnAction(ae -> newItem());
            newDocMenuItem.setOnAction(ae -> newItem());
        openDocButton.setOnAction(ae -> openDoc());
            openDocMenuItem.setOnAction(ae -> openDoc());
        saveDocButton.setOnAction(ae -> saveDoc());
            saveMenuItem.setOnAction(ae -> saveDoc());
        addItemButton.setOnAction(ae -> addItem());
            addItemMenuItem.setOnAction(ae -> addItem());
        searchItemsButton.setOnAction(ae -> searchItems());
            searchItemsMenuItem.setOnAction(ae -> searchItems());
        deleteItemsButton.setOnAction(ae -> deleteItems());
            deleteItemsMenuItem.setOnAction(ae -> deleteItems());
        closeAppMenuItem.setOnAction(ae -> Platform.exit());
    }
	
	public Scene getScene() {
		return scene;
	}

	public Stage getStage(){
	    return stage;
    }

	private void newItem(){
	    Alert newDocWindow = new Alert(Alert.AlertType.NONE);
	    newDocWindow.setTitle("Стварыць новы дакумент");
        //newDocWindow.getDialogPane().setContent();
	    newDocWindow.show();
    }

    private File openDoc(){
        FileChooser openDocChooser = new FileChooser();
        openDocChooser.setTitle("Адкрыць дакумент");
        openDocChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Усе файлы", "*.*"),
                new FileChooser.ExtensionFilter("XML-дакумент", "*.xml")
        );

        return openDocChooser.showOpenDialog(stage);
    }

    private File saveDoc(){
        FileChooser saveDocChooser = new FileChooser();
        saveDocChooser.setTitle("Захаваць дакумент");
        saveDocChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Усе файлы", "*.*"),
                new FileChooser.ExtensionFilter("XML-дакумент", "*.xml")
        );

        return saveDocChooser.showSaveDialog(stage);
    }

	private void addItem(){

    }

    private void searchItems(){

    }

    private void deleteItems(){

    }
}
