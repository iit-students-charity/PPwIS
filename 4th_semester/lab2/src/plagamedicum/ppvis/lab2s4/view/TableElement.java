package plagamedicum.ppvis.lab2s4.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import plagamedicum.ppvis.lab2s4.Controller.Controller;
import plagamedicum.ppvis.lab2s4.model.Student;

public class TableElement {
    private int                rowsOnPage,
                               currentPage = 1,
                               numberOfPages;

    private Label              paginationLabel;
    private Button             resetSearchButton;
    private TextField          rowsOnPageField;
    private TableView<Student> table;
    private ToolBar            navigator,
                               pagination;
    private Pane               tableElement;
    private Controller         controller;
    private ObservableList<Student> studentObsList,
                                    curStudentObsList;

    public TableElement(Controller controller){
        final int    TABLE_HEIGHT                 = 600,
                     TABLE_WIDTH                  = 1460,
                     DEFAULT_ROWS_ON_PAGE_NUMBER  = 17;
        final String SNP_COLUMN_LABEL_TEXT        = "Прозвішча студэнта",
                     GROUP_COLUMN_LABEL_TEXT      = "Група",
                     EXAMS_COLUMN_LABEL_TEXT      = "Экзамены",
                     EXAM_NAME_COLUMN_LABEL_TEXT  = "назва",
                     EXAM_SCORE_COLUMN_LABEL_TEXT = "адзн.",
                     ROWS_ON_PAGE_LABEL_TEXT      = "Радкоў на старонцы: ",
                     TO_BEGIN_BUTTON_LABEL_TEXT   = "<<",
                     TO_LEFT_BUTTON_LABEL_TEXT    = "<",
                     TO_RIGHT_BUTTON_LABEL_TEXT   = ">",
                     TO_END_BUTTON_LABEL_TEXT     = ">>";
        Property  sProperty       = new SimpleStringProperty();
        Button    toBeginButton   = new Button(TO_BEGIN_BUTTON_LABEL_TEXT),
                  toLeftButton    = new Button(TO_LEFT_BUTTON_LABEL_TEXT),
                  toRightButton   = new Button(TO_RIGHT_BUTTON_LABEL_TEXT),
                  toEndButton     = new Button(TO_END_BUTTON_LABEL_TEXT);
        TableColumn<Student, String> snpCol   = new TableColumn<>(SNP_COLUMN_LABEL_TEXT),
                                     groupCol = new TableColumn<>(GROUP_COLUMN_LABEL_TEXT),
                                     examsCol = new TableColumn<>(EXAMS_COLUMN_LABEL_TEXT),
                                     examNameCol;
        List<TableColumn<Student, String>> examNumColList   = new ArrayList<>(),
                                           examNameColList  = new ArrayList<>(),
                                           examScoreColList = new ArrayList<>();

        this.controller = controller;
        studentObsList = FXCollections.observableArrayList(controller.getStudentList());
        curStudentObsList = FXCollections.observableArrayList();

        snpCol.setMinWidth(300);
        snpCol.setCellValueFactory(new PropertyValueFactory<>("alignSnp"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        for(int i=0; i < controller.getExamNumber(); i++){
            final int k = i;
            examNameCol = new TableColumn(EXAM_NAME_COLUMN_LABEL_TEXT);
            examNameCol.setMinWidth(250);
            examNameColList.add(examNameCol);
            examNameColList.get(i).setCellValueFactory(p -> {
                    sProperty.setValue(String.valueOf(p.getValue().getExamName(k)));
                    return sProperty;
                }
            );
            examScoreColList.add(new TableColumn(EXAM_SCORE_COLUMN_LABEL_TEXT));
            examScoreColList.get(i).setCellValueFactory(p -> {
                    sProperty.setValue(String.valueOf(p.getValue().getExamScore(k)));
                    return sProperty;
                }
            );
            examNumColList.add(new TableColumn(Integer.toString(i+1)));
            examNumColList.get(i).getColumns().addAll(
                    examNameColList.get(i),
                    examScoreColList.get(i));
            examsCol.getColumns().add(examNumColList.get(i));
        }

        paginationLabel = new Label();
        navigator = new ToolBar(
                toBeginButton,
                toLeftButton,
                paginationLabel,
                toRightButton,
                toEndButton
        );

        rowsOnPageField = new TextField();
        rowsOnPageField.setText(String.valueOf(DEFAULT_ROWS_ON_PAGE_NUMBER));
        resetSearchButton = new Button("Скінуць пошук");
        resetSearchButton.setVisible(false);
        pagination = new ToolBar(
                new Label("/" + studentObsList.size() + "/"),
                new Separator(),
                new Label(ROWS_ON_PAGE_LABEL_TEXT),
                rowsOnPageField,
                new Separator(),
                navigator,
                resetSearchButton
        );

        table = new TableView<>();
        table.setMinHeight(TABLE_HEIGHT);
        table.setMaxWidth(TABLE_WIDTH);
        table.getColumns().addAll(
                snpCol,
                groupCol,
                examsCol
        );
        table.setItems(curStudentObsList);
        setRowsOnPage();

        tableElement = new VBox();
        tableElement.getChildren().addAll(table,
                                          pagination);

        rowsOnPageField.setOnAction(ae -> setRowsOnPage());
        toBeginButton.setOnAction(ae -> goBegin());
        toLeftButton.setOnAction(ae -> goLeft());
        toRightButton.setOnAction(ae -> goRight());
        toEndButton.setOnAction(ae -> goEnd());
        resetSearchButton.setOnAction(ae->{
            table.setItems(FXCollections.observableArrayList(controller.getStudentList()));
            resetSearchButton.setVisible(false);
        });
    }

    public Pane get(){
        return tableElement;
    }

    public void setObservableList(List<Student> list){
        table.setItems(FXCollections.observableArrayList(list));
        resetSearchButton.setVisible(true);
    }

    private void setRowsOnPage(){
        rowsOnPage = Integer.valueOf(rowsOnPageField.getText());
        refreshPage();
    }

    private void refreshPaginationLabel(){
        numberOfPages = (studentObsList.size() - 1) / rowsOnPage + 1;
        paginationLabel.setText(currentPage + "/" + numberOfPages);
    }

    private void refreshPage(){
        int fromIndex = (currentPage - 1) * rowsOnPage,
            toIndex   =  currentPage      * rowsOnPage;

        if(toIndex > studentObsList.size()){
            toIndex = studentObsList.size();
        }

        curStudentObsList.clear();
        curStudentObsList.addAll(
                studentObsList.subList(
                        fromIndex,
                        toIndex
                )
        );

        refreshPaginationLabel();
    }

    public void refresh(){
        studentObsList = FXCollections.observableArrayList(controller.getStudentList());
        refreshPage();
    }

    private void goBegin(){
        currentPage = 1;
        refreshPage();
    }

    private void goLeft(){
        if(currentPage > 1){
            currentPage--;
        }
        refreshPage();
    }

    private void goRight(){
        if(currentPage < numberOfPages){
            currentPage++;
        }
        refreshPage();
    }

    private void goEnd(){
        currentPage = numberOfPages;
        refreshPage();
    }
}
