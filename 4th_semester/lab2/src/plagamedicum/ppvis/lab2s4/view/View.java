package plagamedicum.ppvis.lab2s4.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import plagamedicum.ppvis.lab2s4.Controller.Controller;
import plagamedicum.ppvis.lab2s4.model.Exam;
import plagamedicum.ppvis.lab2s4.model.Student;

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

            this.root.getChildren().remove(tableElement.get());
            tableElement = new TableElement(controller);
            this.root.getChildren().addAll(
                    tableElement.get()
            );

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

                comboBox.setOnAction(ae -> refreshSelected());
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

    private class RequestElement{
        private int          selectedItem;
        private ComboBox     criteriaComBox;
        private Button       searchButton;
        private TableElement tableElement;
        private GridPane     grid;
        private Pane         criteriaChooser,
                             root;
        private List<Label>     criteria1LabelList,
                                criteria2LabelList,
                                criteria3LabelList;
        private List<TextField> criteria1FieldList,
                                criteria2FieldList,
                                criteria3FieldList;

        public RequestElement(){
            final String CRITERIA_1 = "СЯРЭДНЯЯ АДЗН. І ПРОЗВІШЧА",
                         CRITERIA_2 = "НАЗВА ГРУПЫ І ПРОЗВІШЧА",
                         CRITERIA_3 = "ПРОЗВІШЧА І АДЗН. ПА ДЫСЦЫПЛІНЕ";

            criteriaComBox = new ComboBox();
            criteriaComBox.getItems().addAll(
                    CRITERIA_1,
                    CRITERIA_2,
                    CRITERIA_3
            );
            criteriaComBox.setValue(CRITERIA_1);
            searchButton    = new Button("Шукаць");
            criteriaChooser = new HBox();
            criteriaChooser.getChildren().addAll(
                    new Label("Крытэрый пошуку: "),
                    criteriaComBox,
                    searchButton
            );

            criteria1LabelList = new ArrayList<>();
            criteria1FieldList = new ArrayList<>();
            criteria2LabelList = new ArrayList<>();
            criteria2FieldList = new ArrayList<>();
            criteria3LabelList = new ArrayList<>();
            criteria3FieldList = new ArrayList<>();
            initCriteriaLists();
            grid              = new GridPane();
            switchPreset();

            tableElement = new TableElement(controller);

            this.root = new VBox();
            this.root.getChildren().addAll(
                    new Separator(),
                    new Separator(),
                    criteriaChooser,
                    grid,
                    new Separator(),
                    new Separator(),
                    tableElement.get(),
                    new Separator(),
                    new Separator(),
                    new Separator()
            );

            criteriaComBox.setOnAction(ae -> switchPreset());
        }

        private void switchPreset(){
            grid.getChildren().clear();
            selectedItem = criteriaComBox.getSelectionModel().getSelectedIndex();
            switch (selectedItem){
                case 0:
                    for(int i = 0; i < 2; i++){
                        grid.addRow(i,
                                criteria1LabelList.get(i),
                                criteria1FieldList.get(i)
                        );
                    }
                    break;
                case 1:
                    for(int i = 0; i < 2; i++){
                        grid.addRow(i,
                                criteria2LabelList.get(i),
                                criteria2FieldList.get(i)
                        );
                    }
                    break;
                case 2:
                    for(int i = 0; i < 4; i++){
                        grid.addRow(i,
                                criteria3LabelList.get(i),
                                criteria3FieldList.get(i)
                        );
                    }
                    break;
            }
        }

        private void initCriteriaLists(){
            final String SNP_LABEL_TEXT           = "Прозвішча: ",
                         GROUP_LABEL_TEXT         = "Нумар групы: ",
                         DISCIPLINE_LABEL_TEXT    = "Дысцыпліна: ",
                         MINIMAL_SCORE_LABEL_TEXT = "Мінімальная адзн.: ",
                         MAXIMAL_SCORE_LABEL_TEXT = "Максімальная адзн.: ",
                         AVERAGE_SCORE_LABEL_TEXT = "Сярэдняя адзн.: ";
            TextField    snpField                 = new TextField();

            criteria1LabelList.add(new Label(AVERAGE_SCORE_LABEL_TEXT));
            criteria1LabelList.add(new Label(SNP_LABEL_TEXT));
            criteria1FieldList.add(new TextField());
            criteria1FieldList.add(snpField);
            criteria2LabelList.add(new Label(GROUP_LABEL_TEXT));
            criteria2LabelList.add(new Label(SNP_LABEL_TEXT));
            criteria2FieldList.add(new TextField());
            criteria2FieldList.add(snpField);
            criteria3LabelList.add(new Label(SNP_LABEL_TEXT));
            criteria3LabelList.add(new Label(DISCIPLINE_LABEL_TEXT));
            criteria3LabelList.add(new Label(MINIMAL_SCORE_LABEL_TEXT));
            criteria3LabelList.add(new Label(MAXIMAL_SCORE_LABEL_TEXT));
            criteria3FieldList.add(snpField);
            criteria3FieldList.add(new TextField());
            criteria3FieldList.add(new TextField());
            criteria3FieldList.add(new TextField());
        }

        public Pane get(){
            return this.root;
        }

        public Button getSearchButton(){
            return searchButton;
        }

        public TableElement getTableElement(){
            return tableElement;
        }

        public List search(boolean delete){
            final String S_N_P        = criteria1FieldList.get(1).getText();
            int          studentIndex = 0;
            List<Student> studentList = controller.getStudentList();
            List          resultList;

            if(delete){
                resultList = new ArrayList<Integer>();
            } else {
                resultList = new ArrayList<Student>();
            }

            switch (selectedItem){
                case 0:
                    final String AVERAGE_SCORE = criteria1FieldList.get(0).getText();
                    Integer          studentsMinimalScore = 10,
                                     studentsMaximalScore = 1,
                                     studentsAverageScore;

                    for(Student student:studentList){
                        for(Exam exam:student.getExamList()){
                            if(exam.getScore() < studentsMinimalScore){
                                studentsMinimalScore = exam.getScore();
                            }
                            if (exam.getScore() > studentsMaximalScore){
                                studentsMaximalScore = exam.getScore();
                            }
                        }
                        studentsAverageScore = (studentsMaximalScore + studentsMinimalScore) / 2;
                        if(student.getAlignSnp().equals(S_N_P) && studentsAverageScore == Integer.valueOf(AVERAGE_SCORE)){
                            if(delete){
                                resultList.add(studentIndex);
                            }else{
                                resultList.add(student);
                            }
                        }
                        studentIndex++;
                    }
                    break;
                case 1:
                    final String GROUP = criteria2FieldList.get(0).getText();

                    for(Student student:studentList) {
                        if (student.getAlignSnp().equals(S_N_P) & student.getGroup().equals(GROUP)) {
                            if(delete){
                                resultList.add(studentIndex);
                            }else{
                                resultList.add(student);
                            }
                        }
                        studentIndex++;
                    }
                    break;
                case 2:
                    final String  DISCIPLINE    = criteria3FieldList.get(1).getText(),
                                  MINIMAL_SCORE = criteria3FieldList.get(2).getText(),
                                  MAXIMAL_SCORE = criteria3FieldList.get(3).getText();
                    final Integer SCORE         = (Integer.valueOf(MINIMAL_SCORE) + Integer.valueOf(MAXIMAL_SCORE)) / 2;
                    boolean       examExists    = false;

                    for(Student student:studentList) {
                        for(Exam exam:student.getExamList()){
                            if(exam.getName().equals(DISCIPLINE) && exam.getScore() == SCORE){
                                examExists = true;
                            }
                        }
                        if (student.getAlignSnp().equals(S_N_P) && examExists) {
                            if(delete){
                                resultList.add(studentIndex);
                            }else{
                                resultList.add(student);
                            }
                        }
                        studentIndex++;
                    }
                    break;
            }

            return resultList;
        }
    }

    private void searchItems(){
        final String WINDOW_TITLE_TEXT = "Шукаць радкі";
        Alert        searchItemsWindow;
        RequestElement requestElement = new RequestElement();

        searchItemsWindow = createEmptyCloseableDialog();
        searchItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        searchItemsWindow.getDialogPane().setContent(requestElement.get());
        searchItemsWindow.show();

        requestElement.getSearchButton().setOnAction(ae->{
            List<Student> studentList = requestElement.search(false);

            requestElement.getTableElement().setObservableList(studentList);
        });

        ((Button)searchItemsWindow.getDialogPane().lookupButton(searchItemsWindow.getButtonTypes().get(0))).setOnAction(ae->{
            tableElement.refresh();
            searchItemsWindow.close();
        });
    }

    private void deleteItems(){
        final String WINDOW_TITLE_TEXT = "Выдаліць радкі";
        Alert        deleteItemsWindow;
        RequestElement requestElement = new RequestElement();

        deleteItemsWindow = createEmptyCloseableDialog();
        deleteItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        deleteItemsWindow.getDialogPane().setContent(requestElement.get());
        deleteItemsWindow.show();

        ((Button)deleteItemsWindow.getDialogPane().lookupButton(deleteItemsWindow.getButtonTypes().get(0))).setOnAction(ae->{
            List<Integer> indexList = requestElement.search(true);

            for(int i:indexList){
                controller.getStudentList().remove(i);
            }
            tableElement.refresh();
            deleteItemsWindow.close();
        });
    }

    private Alert createEmptyCloseableDialog(){
        final String CLOSE_BUTTON_LABEL_TEXT = "Далей";
        ButtonType   closeButton       = new ButtonType(CLOSE_BUTTON_LABEL_TEXT);
        Alert        window            = new Alert(Alert.AlertType.NONE);

        window.getButtonTypes().addAll(closeButton);
        return window;
    }
}
