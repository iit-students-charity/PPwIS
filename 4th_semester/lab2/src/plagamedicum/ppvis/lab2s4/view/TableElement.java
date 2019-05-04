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
    private int                rowsOnPage  = 17,
                               currentPage = 1,
                               numberOfPages;

    private Label paginationLabel;
    private TextField          rowsOnPageField;
    private TableView<Student> table;
    private ToolBar            navigator,
                               pagination;
    private Pane               tableElement;
    private Controller         controller;
    private ObservableList<Student> studentObsList,
                                    curStudentObsList;

    public TableElement(Controller controller){
        final int    TABLE_HEIGHT                 = 600;
        final String SNP_COLUMN_LABEL_TEXT        = "Прозвішча студэнта",
                     GROUP_COLUMN_LABEL_TEXT      = "Група",
                     EXAMS_COLUMN_LABEL_TEXT      = "Экзамены",
                     EXAM_NAME_COLUMN_LABEL_TEXT  = "назва",
                     EXAM_SCORE_COLUMN_LABEL_TEXT = "адзн.",
                     ROWS_ON_PAGE_LABEL_TEXT      = "Радкоў на старонцы ",
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
                                     examsCol = new TableColumn<>(EXAMS_COLUMN_LABEL_TEXT);
        List<TableColumn<Student, String>> examNumCol   = new ArrayList<>(),
                                           examNameCol  = new ArrayList<>(),
                                           examScoreCol = new ArrayList<>();

        this.controller = controller;

        snpCol.setCellValueFactory(new PropertyValueFactory<>("alignSnp"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        for(int i=0; i < controller.getExamNumber(); i++){
            final int k = i;
            examNameCol.add(new TableColumn(EXAM_NAME_COLUMN_LABEL_TEXT));
            examNameCol.get(i).setCellValueFactory(p -> {
                    sProperty.setValue(String.valueOf(p.getValue().getExamName(k)));
                    return sProperty;
                }
            );
            examScoreCol.add(new TableColumn(EXAM_SCORE_COLUMN_LABEL_TEXT));
            examScoreCol.get(i).setCellValueFactory(p -> {
                    sProperty.setValue(String.valueOf(p.getValue().getExamScore(k)));
                    return sProperty;
                }
            );
            examNumCol.add(new TableColumn(Integer.toString(i+1)));
            examNumCol.get(i).getColumns().addAll(
                    examNameCol.get(i),
                    examScoreCol.get(i));
            examsCol.getColumns().add(examNumCol.get(i));
        }

        curStudentObsList = studentObsList = FXCollections.observableArrayList(controller.getStudentList());

        table = new TableView<>();

        table.setMinHeight(TABLE_HEIGHT);
        table.getColumns().addAll(
                snpCol,
                groupCol,
                examsCol);
        table.setItems(curStudentObsList);

        paginationLabel = new Label();
        refreshPaginationLabel();
        navigator = new ToolBar(
                toBeginButton,
                toLeftButton,
                paginationLabel,
                toRightButton,
                toEndButton);

        pagination = new ToolBar(
                new Label(ROWS_ON_PAGE_LABEL_TEXT),
                rowsOnPageField = new TextField(),
                new Separator(),
                navigator);

        tableElement = new VBox();
        tableElement.getChildren().addAll(table,
                                          pagination);

        rowsOnPageField.setOnAction(ae -> setRowsOnPage());
        toBeginButton.setOnAction(ae -> goBegin());
        toLeftButton.setOnAction(ae -> goLeft());
        toRightButton.setOnAction(ae -> goRight());
        toEndButton.setOnAction(ae -> goEnd());
    }

    public TableView getTable(){
        return table;
    }

    public Pane get(){
        return tableElement;
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
