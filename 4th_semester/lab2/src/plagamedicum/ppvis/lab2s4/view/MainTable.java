package plagamedicum.ppvis.lab2s4.view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Callback;

import plagamedicum.ppvis.lab2s4.model.Student;
import plagamedicum.ppvis.lab2s4.model.Exam;

import javax.swing.text.Style;


public class MainTable {
    private TableView<Student> table = new TableView();

    public MainTable(){
        final String SNP_COLUMN_LABEL        = "ФИО студента",
                     GROUP_COLUMN_LABEL      = "группа",
                     EXAMS_COLUMN_LABEL      = "Экзамены",
                     EXAM_NAME_COLUMN_LABEL  = "наим",
                     EXAM_SCORE_COLUMN_LABEL = "балл";
        final int    TABLE_HEIGHT = 600;
        TableColumn<Student, String> snpCol      = new TableColumn<>(SNP_COLUMN_LABEL),
                                     groupCol    = new TableColumn<>(GROUP_COLUMN_LABEL),
                                     examsTopCol = new TableColumn<>(EXAMS_COLUMN_LABEL);
        List<TableColumn<Student, String>> examCol      = new ArrayList<>(),
                                           examNameCol  = new ArrayList<>(),
                                           examScoreCol = new ArrayList<>();
        //TODO: Перанесці ўсю мадэль у Main
        ObservableList<Exam> exa = FXCollections.observableArrayList(
                new Exam("sada", 161)
        );
        ObservableList<Student> studentList = FXCollections.observableArrayList(
                new Student("bb", "vbv", new ArrayList<>(exa))
        );

        snpCol.setCellValueFactory(
                new PropertyValueFactory<>("snp")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<>("group")
        );

        for(int i=0; i < Student.getExamNumber(); i++){
            examNameCol.add(new TableColumn(EXAM_NAME_COLUMN_LABEL));

            examScoreCol.add(new TableColumn(EXAM_SCORE_COLUMN_LABEL));
            examScoreCol.get(i).setCellValueFactory(
                new Callback<
                        TableColumn.CellDataFeatures<Student, String>,
                        ObservableValue<String>>(){
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<Student, String> p){
                        SimpleStringProperty s = new SimpleStringProperty();
                        s.setValue(p.getValue().getIExamName(i));
                        return s;
                    }
                }
            );

            examCol.add(new TableColumn(Integer.toString(i+1)));
            examCol.get(i).getColumns().addAll(examNameCol.get(i),
                                               examScoreCol.get(i));
            examsTopCol.getColumns().add(examCol.get(i));
        }

        table.getColumns().addAll(snpCol,
                                  groupCol,
                                  examsTopCol);
        table.setItems(studentList);
        table.setMinHeight(TABLE_HEIGHT);
    }

    public TableView get(){
        return table;
    }
}
