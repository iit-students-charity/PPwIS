package plagamedicum.ppvis.lab2s4.view;

import javafx.beans.property.SimpleStringProperty;
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
    private TableView<Student> table        = new TableView();
    private HBox               navigator    = new HBox(),
                               pagination   = new HBox();
    private VBox               tableElement = new VBox();

    public TableElement(Controller controller){
        final String SNP_COLUMN_LABEL        = "Прозвішча студэнта",
                     GROUP_COLUMN_LABEL      = "Група",
                     EXAMS_COLUMN_LABEL      = "Экзамены",
                     EXAM_NAME_COLUMN_LABEL  = "назва",
                     EXAM_SCORE_COLUMN_LABEL = "адзн.",
                     TO_BEGIN_BUTTON_LABEL   = "<<",
                     TO_LEFT_BUTTON_LABEL    = "<",
                     TO_RIGHT_BUTTON_LABEL   = ">",
                     TO_END_BUTTON_LABEL     = ">>";
        Label   pagesCounter  = new Label(" 1/1 ");
        Button  toBeginButton = new Button(TO_BEGIN_BUTTON_LABEL),
                toLeftButton  = new Button(TO_LEFT_BUTTON_LABEL),
                toRightButton = new Button(TO_RIGHT_BUTTON_LABEL),
                toEndButton   = new Button(TO_END_BUTTON_LABEL);
        TextField rowsOnPage  = new TextField();
        TableColumn<Student, String> snpCol   = new TableColumn<>(SNP_COLUMN_LABEL),
                                     groupCol = new TableColumn<>(GROUP_COLUMN_LABEL),
                                     examsCol = new TableColumn<>(EXAMS_COLUMN_LABEL);
        List<TableColumn<Student, String>> examNumCol   = new ArrayList<>(),
                                           examNameCol  = new ArrayList<>(),
                                           examScoreCol = new ArrayList<>();

        snpCol.setCellValueFactory(new PropertyValueFactory<>("snp"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        for(int i=0; i < controller.getExamNumber(); i++){
            final int k = i;
            examNameCol.add(new TableColumn(EXAM_NAME_COLUMN_LABEL));
            examNameCol.get(i).setCellValueFactory(p -> {
                    SimpleStringProperty s = new SimpleStringProperty();

                    s.setValue(String.valueOf(p.getValue().getExamName(k)));
                    return s;
                }
            );
            examScoreCol.add(new TableColumn(EXAM_SCORE_COLUMN_LABEL));
            examScoreCol.get(i).setCellValueFactory(p -> {
                    SimpleStringProperty s = new SimpleStringProperty();

                    s.setValue(String.valueOf(p.getValue().getExamScore(k)));
                    return s;
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
        table.setItems(controller.getStudentList());

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

    private void goBegin(){

    }

    private void goLeft(){

    }

    private void goRight(){

    }

    private void goEnd(){

    }
}
