package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import plagamedicum.ppvis.lab2s4.Controller.Controller;
import plagamedicum.ppvis.lab2s4.model.Exam;

import java.util.ArrayList;
import java.util.List;

public class View {
    private final String REGEX_DIGITS_ONLY = "^\\d+$";
    private Scene        scene;
	private TableElement tableElement;
    private Controller   controller;
    private Stage        stage;
    private VBox         root;

	public View(Controller controller) {
        final int    STAGE_WIDTH  = 1460,
                     STAGE_HEIGHT = 781;
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
        ToolBar  instruments;

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

        instruments = new ToolBar(
                newDocButton,
                openDocButton,
                saveDocButton,
                new Separator(),
                addItemsButton,
                searchItemsButton,
                deleteItemsButton);

        tableElement = new TableElement(controller);

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
	    final String EXAM_NUM_LABEL_TEXT       = "Колькасць экзаменаў: ",
                     ENTRY_NUM_LABEL_TEXT      = "Згенераваць запісаў: ",
                     INFO_LABEL_TEXT           = "\nКалі колькасць экзаменаў ці колькасць экзаменаў для генераццыі\n" +
                                                 "не будзе ўведзена карыстальнікам, яна будзе прыраўнана\n" +
                                                 "перадвызначанаму значэнню.\n ",
                     NEW_DOC_WINDOW_TITLE_TEXT = "Стварыць новы дакумент";
	    TextField  examNumField = new TextField("8"),
                   entNumField  = new TextField("0");
        GridPane   grid         = new GridPane();
        Pane       root         = new VBox();
	    Alert      newDocWindow;

	    grid.addRow(0,
                new Label(EXAM_NUM_LABEL_TEXT),
                examNumField
        );
        grid.addRow(1,
                new Label(ENTRY_NUM_LABEL_TEXT),
                entNumField
        );
        root.getChildren().addAll(
                grid,
                new Label(INFO_LABEL_TEXT)
        );

	    newDocWindow = createEmptyCloseableDialog();
	    newDocWindow.setTitle(NEW_DOC_WINDOW_TITLE_TEXT);
        newDocWindow.getDialogPane().setContent(root);
	    newDocWindow.show();

        ((Button)newDocWindow.getDialogPane().lookupButton(newDocWindow.getButtonTypes().get(0))).setOnAction(ae->{
            int examNumber     = 8,
                entitiesNumber = 0;

	        if(!examNumField.getText().isEmpty() & examNumField.getText().matches(REGEX_DIGITS_ONLY)){
                examNumber = Integer.valueOf(examNumField.getText());
            }
            if(!entNumField.getText().isEmpty() & entNumField.getText().matches(REGEX_DIGITS_ONLY)){
                entitiesNumber = Integer.valueOf(entNumField.getText());
            }
            controller.newDoc(examNumber, entitiesNumber);
            tableElement.refresh();
	        newDocWindow.close();
        });
    }

    private void openDoc(){
        FileChooser openDocChooser = new FileChooser();

        openDocChooser.setTitle("Адкрыць дакумент");
        openDocChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Усе файлы", "*.*"),
                new FileChooser.ExtensionFilter("XML-дакумент", "*.xml")
        );

        try {
            controller.openDoc(openDocChooser.showOpenDialog(stage));
        } catch (Exception exception){
            exception.printStackTrace();
        }

        tableElement.refresh();
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
        final String WINDOW_TITLE_TEXT      = "Дадаць радкі: ",
                     SURNAME_LABEL_TEXT     = "Прозвішча: ",
                     NAME_LABEL_TEXT        = "Імя: ",
                     PATRONYM_LABEL_TEXT    = "Імя па бацьку: ",
                     GROUP_LABEL_TEXT       = "Назва групы: ",
                     EXAM_NUMBER_LABEL_TEXT = "Нумар экзамена: ",
                     EXAM_NAME_LABEL_TEXT   = "Назва экзамена: ",
                     EXAM_SCORE_LABEL_TEXT  = "Адзнака: ";
        List<Exam>   examList       = new ArrayList<>();
        TextField    surnameField   = new TextField(),
                     nameField      = new TextField(),
                     patronymField  = new TextField(),
                     groupField     = new TextField();
        class ComboElement{
            private int       selectedItem;
            private TextField examNameField,
                              examScoreField;
            private ComboBox  comboBox;

            public ComboElement(int selectedItem){
                this.selectedItem = selectedItem;
                this.comboBox     = new ComboBox<Integer>();
                this.examNameField  = new TextField("");
                this.examScoreField = new TextField("0");

                for(int i = 0; i < controller.getExamNumber(); i++){
                    comboBox.getItems().addAll(((Integer)(i+1)).toString());
                    examList.add(new Exam("", 0));
                }

                comboBox.setValue(((Integer)(selectedItem)).toString());
                refreshSelected();
            }

            public TextField getExamNameField(){
                return examNameField;
            }

            public TextField getExamScoreField(){
                return examScoreField;
            }

            public ComboBox get(){
                return comboBox;
            }

            public void refreshSelected(){
                examList.get(selectedItem).setName(examNameField.getText());
                examList.get(selectedItem).setScore(Integer.valueOf(examScoreField.getText()));
                selectedItem = comboBox.getSelectionModel().getSelectedIndex();

                examNameField.setText(examList.get(selectedItem).getName());
                examScoreField.setText(((Integer)examList.get(selectedItem).getScore()).toString());
            }
        }
        ComboElement examComElement = new ComboElement(1);
	    GridPane     root           = new GridPane();
        Alert        addItemWindow;

        root.addRow(0,
                new Label(SURNAME_LABEL_TEXT),
                surnameField
        );
        root.addRow(1,
                new Label(NAME_LABEL_TEXT),
                nameField
        );
        root.addRow(2,
                new Label(PATRONYM_LABEL_TEXT),
                patronymField
        );
        root.addRow(3,
                new Label(GROUP_LABEL_TEXT),
                groupField
        );
        root.addRow(4,
                new Label(EXAM_NUMBER_LABEL_TEXT),
                examComElement.get()
        );
        root.addRow(5,
                new Label(EXAM_NAME_LABEL_TEXT),
                examComElement.getExamNameField()
        );
        root.addRow(6,
                new Label(EXAM_SCORE_LABEL_TEXT),
                examComElement.getExamScoreField()
        );

        addItemWindow = createEmptyCloseableDialog();
        addItemWindow.setTitle(WINDOW_TITLE_TEXT);
        addItemWindow.getDialogPane().setContent(root);
        addItemWindow.show();

        examComElement.get().setOnAction(ae -> {
            examComElement.refreshSelected();
        });
        ((Button)addItemWindow.getDialogPane().lookupButton(addItemWindow.getButtonTypes().get(0))).setOnAction(ae->{
            controller.addStudent(
                    surnameField.getText(),
                    nameField.getText(),
                    patronymField.getText(),
                    groupField.getText(),
                    examList
            );
            examComElement.refreshSelected();
            tableElement.refresh();
            addItemWindow.close();
        });
    }

    private void searchItems(){
        final String WINDOW_TITLE_TEXT = "Шукаць радкі";
        Alert        searchItemsWindow;

        searchItemsWindow = createEmptyCloseableDialog();
        searchItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        searchItemsWindow.show();
    }

    private void deleteItems(){
        final String WINDOW_TITLE_TEXT = "Выдаліць радкі";
        Alert        deleteItemsWindow;

        deleteItemsWindow = createEmptyCloseableDialog();
        deleteItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        deleteItemsWindow.show();
    }

    private Alert createEmptyCloseableDialog(){
        final String CLOSE_BUTTON_LABEL_TEXT = "Далей";
        ButtonType   closeButton       = new ButtonType(CLOSE_BUTTON_LABEL_TEXT);
        Alert        window            = new Alert(Alert.AlertType.NONE);

        window.getButtonTypes().addAll(closeButton);
        return window;
    }
}
