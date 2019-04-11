package plagamedicum.ppvis.lab2s4.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Callback;

import plagamedicum.ppvis.lab2s4.model.Student;


public class MainTable {
    private TableView<Student> table = new TableView();

    public MainTable(ObservableList<Student> studentList){
        final String SNP_COLUMN_LABEL        = "Прозвішча студэнта",
                     GROUP_COLUMN_LABEL      = "Група",
                     EXAMS_COLUMN_LABEL      = "Экзамены",
                     EXAM_NAME_COLUMN_LABEL  = "назва",
                     EXAM_SCORE_COLUMN_LABEL = "адзн.";
        final int    TABLE_HEIGHT            = 600;
        TableColumn<Student, String> snpCol   = new TableColumn<>(SNP_COLUMN_LABEL),
                                     groupCol = new TableColumn<>(GROUP_COLUMN_LABEL),
                                     examsCol = new TableColumn<>(EXAMS_COLUMN_LABEL);
        List<TableColumn<Student, String>> examNumCol   = new ArrayList<>(),
                                           examNameCol  = new ArrayList<>(),
                                           examScoreCol = new ArrayList<>();

        snpCol.setCellValueFactory(
                new PropertyValueFactory<>("snp")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<>("group")
        );

        for(int i=0; i < Student.getExamNumber(); i++){
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
            examNumCol.get(i).getColumns().addAll(examNameCol.get(i),
                                               examScoreCol.get(i));
            examsCol.getColumns().add(examNumCol.get(i));
        }

        table.getColumns().addAll(snpCol,
                                  groupCol,
                                  examsCol);
        table.setItems(studentList);
        table.setMinHeight(TABLE_HEIGHT);
    }

    public TableView get(){
        return table;
    }
}
