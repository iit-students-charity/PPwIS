package plagamedicum.ppvis.lab2s4.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import plagamedicum.ppvis.lab2s4.Controller.Controller;
import plagamedicum.ppvis.lab2s4.model.Student;

public class TableElement {
    private int                rowsNumber   = 15;
    private TableView<Student> table        = new TableView();
    private HBox               navigator    = new HBox(),
                               pagination   = new HBox();
    private VBox               tableElement = new VBox();

    public TableElement(Controller controller){
        final String SNP_COLUMN_LABEL_TEXT        = "Прозвішча студэнта",
                     GROUP_COLUMN_LABEL_TEXT      = "Група",
                     EXAMS_COLUMN_LABEL_TEXT      = "Экзамены",
                     EXAM_NAME_COLUMN_LABEL_TEXT  = "назва",
                     EXAM_SCORE_COLUMN_LABEL_TEXT = "адзн.",
                     TO_BEGIN_BUTTON_LABEL_TEXT   = "<<",
                     TO_LEFT_BUTTON_LABEL_TEXT    = "<",
                     TO_RIGHT_BUTTON_LABEL_TEXT   = ">",
                     TO_END_BUTTON_LABEL_TEXT     = ">>";
        StringProperty sProperty = new SimpleStringProperty();
        Label   pagesCounter  = new Label(" 1/1 ");
        Button  toBeginButton = new Button(TO_BEGIN_BUTTON_LABEL_TEXT),
                toLeftButton  = new Button(TO_LEFT_BUTTON_LABEL_TEXT),
                toRightButton = new Button(TO_RIGHT_BUTTON_LABEL_TEXT),
                toEndButton   = new Button(TO_END_BUTTON_LABEL_TEXT);
        TextField rowsOnPage  = new TextField();
        TableColumn<Student, String> snpCol   = new TableColumn<>(SNP_COLUMN_LABEL_TEXT),
                                     groupCol = new TableColumn<>(GROUP_COLUMN_LABEL_TEXT),
                                     examsCol = new TableColumn<>(EXAMS_COLUMN_LABEL_TEXT);
        List<TableColumn<Student, String>> examNumCol   = new ArrayList<>(),
                                           examNameCol  = new ArrayList<>(),
                                           examScoreCol = new ArrayList<>();

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

        table.getColumns().addAll(
                snpCol,
                groupCol,
                examsCol);
        table.setItems(FXCollections.observableArrayList(controller.getStudentList()));

        navigator.getChildren().addAll(
                toBeginButton,
                toLeftButton,
                pagesCounter,
                toRightButton,
                toEndButton);

        pagination.getChildren().addAll(
                new Label("Радкоў на старонцы "),
                rowsOnPage,
                new Separator(),
                navigator);
        pagination.setAlignment(Pos.CENTER);

        tableElement.getChildren().addAll(table,
                                          pagination);

        rowsOnPage.setOnAction(ae -> refreshRows());
        toBeginButton.setOnAction(ae -> goBegin());
        toLeftButton.setOnAction(ae -> goLeft());
        toRightButton.setOnAction(ae -> goRight());
        toEndButton.setOnAction(ae -> goEnd());
    }

    public TableView getTable(){
        return table;
    }

    public VBox get(){
        return tableElement;
    }

    private void refreshRows(){
        
    }

    private void goBegin(){

    }

    private void goLeft(){

    }

    private void goRight(){

    }

    private void goEnd(){

    }
}
