package plagamedicum.ppvis.lab2s4.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import plagamedicum.ppvis.lab2s4.model.Student;


public class MainTable {
    private TableView table = new TableView();


    public MainTable(){
        final String SNP_COLUMN_LABEL = "ФИО студента",
                     GROUP_COLUMN_LABEL      = "группа",
                     EXAMS_COLUMN_LABEL      = "Экзамены",
                     EXAM_NAME_COLUMN_ABEL   = "наим",
                     EXAM_SCORE_COLUMN_NAME  = "балл";
        final int SESSION_NUMBER = 8,
                  TABLE_HEIGHT   = 600;
        TableColumn snpCol      = new TableColumn(SNP_COLUMN_LABEL),
                    groupCol    = new TableColumn(GROUP_COLUMN_LABEL),
                    examsCol    = new TableColumn(EXAMS_COLUMN_LABEL);
        TableColumn[] sessionCol   = new TableColumn[SESSION_NUMBER],
                      examNameCol  = new TableColumn[SESSION_NUMBER],
                      examScoreCol = new TableColumn[SESSION_NUMBER];
        ObservableList<Student> students = FXCollections.observableArrayList(
                new Student("AAAA", 5, new String[2], new int[2])
        );

        snpCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("surname")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<Student,String>("group")
        );

        for(int i=0; i < SESSION_NUMBER; i++){
            sessionCol[i] = new TableColumn(Integer.toString(i+1));
            examNameCol[i] = new TableColumn(EXAM_NAME_COLUMN_ABEL);
            examScoreCol[i] = new TableColumn(EXAM_SCORE_COLUMN_NAME);
            sessionCol[i].getColumns().addAll(examNameCol[i],
                                              examScoreCol[i]);
            examsCol.getColumns().add(sessionCol[i]);
        }

        table.getColumns().addAll(snpCol,
                                  groupCol,
                                  examsCol);
        table.setMinHeight(TABLE_HEIGHT);
    }

    public TableView get(){
        return table;
    }
}
