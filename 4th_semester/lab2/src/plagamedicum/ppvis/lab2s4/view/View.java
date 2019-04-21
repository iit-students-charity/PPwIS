package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import plagamedicum.ppvis.lab2s4.Controller.Controller;

import java.io.File;

public class View {
    private final int    TABLE_HEIGHT = 600;
    private final String REGEX_DIGITS_ONLY = "^\\d+$";
    private Scene        scene;
	private TableElement tableElement;
    private Controller   controller;
    private Stage        stage;
    private VBox         root;

	public View(Controller controller) {
        final int    STAGE_WIDTH  = 1460,
                     STAGE_HEIGHT = 750;
        final String STAGE_TITLE_TEXT = "Lab2";

        this.controller = controller;
        initWindow();
        stage = new Stage();
        stage.setWidth (STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setTitle (STAGE_TITLE_TEXT);
        stage.setScene(scene);
	}

	private void initWindow(){
        final String FILE_MENU_LABEL_TEXT              = "Файл",
                     EDIT_MENU_LABEL_TEXT              = "Рэдагаваць",
                     NEW_DOC_MENU_ITEM_LABEL_TEXT      = "Новы дакумент",
                     OPEN_DOC_MENU_ITEM_LABEL_TEXT     = "Адкрыць дакумент",
                     SAVE_DOC_MENU_ITEM_LABEL_TEXT     = "Захаваць дакумент",
                     ADD_ITEM_MENU_ITEM_LABEL_TEXT     = "Дадаць радкі",
                     SEARCH_ITEMS_MENU_ITEM_LABEL_TEXT = "Шукаць радкі",
                     DELETE_ITEMS_MENU_ITEM_LABEL_TEXT = "Выдаліць радкі",
                     CLOSE_APP_MENU_ITEM_LABEL_TEXT    = "Выйсці",
                     NEW_DOC_BUTTON_LABEL_TEXT         = "Новы дакумент",
                     OPEN_DOC_BUTTON_LABEL_TEXT        = "Адкрыць дакумент",
                     SAVE_DOC_BUTTON_LABEL_TEXT        = "Захаваць дакумент",
                     ADD_ITEMS_BUTTON_LABEL_TEXT       = "Дадаць радкі",
                     SEARCH_ITEMS_BUTTON_LABEL_TEXT    = "Шукаць радкі",
                     DELETE_ITEMS_BUTTON_LABEL_TEXT    = "Выдаліць радкі";
        MenuItem newDocMenuItem      = new MenuItem(NEW_DOC_MENU_ITEM_LABEL_TEXT),
                 openDocMenuItem     = new MenuItem(OPEN_DOC_MENU_ITEM_LABEL_TEXT),
                 saveMenuItem        = new MenuItem(SAVE_DOC_MENU_ITEM_LABEL_TEXT),
                 addItemsMenuItem    = new MenuItem(ADD_ITEM_MENU_ITEM_LABEL_TEXT),
                 searchItemsMenuItem = new MenuItem(SEARCH_ITEMS_MENU_ITEM_LABEL_TEXT),
                 deleteItemsMenuItem = new MenuItem(DELETE_ITEMS_MENU_ITEM_LABEL_TEXT),
                 closeAppMenuItem    = new MenuItem(CLOSE_APP_MENU_ITEM_LABEL_TEXT);
        Menu     fileMenu            = new Menu(FILE_MENU_LABEL_TEXT),
                 editMenu            = new Menu(EDIT_MENU_LABEL_TEXT);
        MenuBar  menuBar             = new MenuBar();
        Button   newDocButton        = new Button(NEW_DOC_BUTTON_LABEL_TEXT),
                 openDocButton       = new Button(OPEN_DOC_BUTTON_LABEL_TEXT),
                 saveDocButton       = new Button(SAVE_DOC_BUTTON_LABEL_TEXT),
                 addItemsButton      = new Button(ADD_ITEMS_BUTTON_LABEL_TEXT),
                 searchItemsButton   = new Button(SEARCH_ITEMS_BUTTON_LABEL_TEXT),
                 deleteItemsButton   = new Button(DELETE_ITEMS_BUTTON_LABEL_TEXT);
        HBox     instruments         = new HBox();

        fileMenu.getItems().addAll(
                newDocMenuItem,
                openDocMenuItem,
                saveMenuItem,
                new SeparatorMenuItem(),
                closeAppMenuItem);
        editMenu.getItems().addAll(
                addItemsMenuItem,
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
                addItemsButton,
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

        newDocButton.setOnAction(ae -> newDoc());
            newDocMenuItem.setOnAction(ae -> newDoc());
        openDocButton.setOnAction(ae -> openDoc());
            openDocMenuItem.setOnAction(ae -> openDoc());
        saveDocButton.setOnAction(ae -> saveDoc());
            saveMenuItem.setOnAction(ae -> saveDoc());
        addItemsButton.setOnAction(ae -> addItems());
            addItemsMenuItem.setOnAction(ae -> addItems());
        searchItemsButton.setOnAction(ae -> searchItems());
            searchItemsMenuItem.setOnAction(ae -> searchItems());
        deleteItemsButton.setOnAction(ae -> deleteItems());
            deleteItemsMenuItem.setOnAction(ae -> deleteItems());
        closeAppMenuItem.setOnAction(ae -> Platform.exit());
    }

	public Stage getStage(){
	    return stage;
    }

	private void newDoc(){
	    final String OK_BUTTON_LABEL_TEXT      = "Далей",
                     EXAM_NUM_LABEL_TEXT       = "Колькасць экзаменаў: ",
                     ENTRY_NUM_LABEL_TEXT      = "Згенераваць запісаў: ",
                     INFO_LABEL_TEXT           = "\nКалі колькасць экзаменаў ці колькасць экзаменаў для генераццыі\n" +
                                                 "не будзе ўведзена карыстальнікам, яна будзе прыраўнана\n" +
                                                 "перадвызначанаму значэнню.\n ",
                     NEW_DOC_WINDOW_TITLE_TEXT = "Стварыць новы дакумент";
	    Label      examNumLabel = new Label(EXAM_NUM_LABEL_TEXT),
                   entNumLabel  = new Label(ENTRY_NUM_LABEL_TEXT),
                   infoLabel    = new Label(INFO_LABEL_TEXT);
	    TextField  examNumField = new TextField("8"),
                   entNumField  = new TextField("0");
	    ButtonType okButton     = new ButtonType(OK_BUTTON_LABEL_TEXT);
        VBox       root         = new VBox();
        GridPane   grid         = new GridPane();
	    Alert      newDocWindow = new Alert(Alert.AlertType.NONE);

	    grid.addColumn(1,
                examNumLabel,
                entNumLabel);
        grid.addColumn(2,
                examNumField,
                entNumField);
	    infoLabel.setAlignment(Pos.CENTER_LEFT);

	    root.setAlignment(Pos.CENTER);
	    root.getChildren().addAll(
	            grid,
	            infoLabel
        );

        newDocWindow.getButtonTypes().addAll(okButton);
	    newDocWindow.setTitle(NEW_DOC_WINDOW_TITLE_TEXT);
        newDocWindow.getDialogPane().setContent(root);
	    newDocWindow.show();

        ((Button)newDocWindow.getDialogPane().lookupButton(okButton)).setOnAction(ae->{
            int examNumber     = 8,
                entitiesNumber = 0;

	        if(!examNumField.getText().isEmpty() & examNumField.getText().matches(REGEX_DIGITS_ONLY)){
                examNumber = Integer.valueOf(examNumField.getText());
            }
            if(!entNumField.getText().isEmpty() & entNumField.getText().matches(REGEX_DIGITS_ONLY)){
                entitiesNumber = Integer.valueOf(entNumField.getText());
            }
            controller.newDoc(examNumber, entitiesNumber);
            refreshMainTableElement();
	        newDocWindow.close();
        });
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

    private void saveDoc(){
        FileChooser saveDocChooser = new FileChooser();

        saveDocChooser.setTitle("Захаваць дакумент");
        saveDocChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Усе файлы", "*.*"),
                new FileChooser.ExtensionFilter("XML-дакумент", "*.xml")
        );

        controller.saveDoc(saveDocChooser.showSaveDialog(stage));
    }

	private void addItems(){
	    final String WINDOW_TITLE_TEXT = "Дадаць радкі";
        Alert        addItemWindow;

        addItemWindow = createDialogWithTable();
        addItemWindow.setTitle(WINDOW_TITLE_TEXT);
        addItemWindow.show();
    }

    private void searchItems(){
        final String WINDOW_TITLE_TEXT = "Шукаць радкі";
        Alert        searchItemsWindow;

        searchItemsWindow = createDialogWithTable();
        searchItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        searchItemsWindow.show();
    }

    private void deleteItems(){
        final String WINDOW_TITLE_TEXT = "Выдаліць радкі";
        Alert        deleteItemsWindow;

        deleteItemsWindow = createDialogWithTable();
        deleteItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        deleteItemsWindow.show();
    }

    private Alert createDialogWithTable(){
        final String CLOSE_BUTTON_LABEL_TEXT = "Закрыць";
        ButtonType   closeButton       = new ButtonType(CLOSE_BUTTON_LABEL_TEXT);
        TableElement tableElement      = new TableElement(controller);
        VBox         root              = new VBox();
        Alert        window            = new Alert(Alert.AlertType.NONE);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                tableElement.get()
        );
        window.getButtonTypes().addAll(closeButton);
        window.getDialogPane().setContent(root);

        return window;
    }

    private void refreshMainTableElement(){
        this.root.getChildren().remove(tableElement.get());
        tableElement = new TableElement(controller);
        tableElement.getTable().setMinHeight(TABLE_HEIGHT);
        this.root.getChildren().addAll(
                tableElement.get()
        );
    }
}
