package plagamedicum.ppvis.lab2s4.view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import plagamedicum.ppvis.lab2s4.model.Student;

import java.util.List;


public class MainTable {
    private TableView<Student> table = new TableView();

    public MainTable(){
        final String SNP_COLUMN_LABEL        = "ФИО студента",
                     GROUP_COLUMN_LABEL      = "группа",
                     EXAMS_COLUMN_LABEL      = "Экзамены",
                     EXAM_NAME_COLUMN_ABEL   = "наим",
                     EXAM_SCORE_COLUMN_NAME  = "балл";
        final int EXAM_NUMBER = 8,
                  TABLE_HEIGHT   = 600;
        TableColumn<Student, String> snpCol = new TableColumn<>(SNP_COLUMN_LABEL),
                    groupCol    = new TableColumn<>(GROUP_COLUMN_LABEL),
                    examsTopCol    = new TableColumn<>(EXAMS_COLUMN_LABEL);
        TableColumn<Student, String>[] examCol = new TableColumn[EXAM_NUMBER],
                      examNameCol  = new TableColumn[EXAM_NUMBER],
                      examScoreCol = new TableColumn[EXAM_NUMBER];
        /*
        List exa = new List{"bb", "dsad", "ddddd", "jjjj"};
        List exaS = new List{5,6,7,8};
        ObservableList<Student> studentList = FXCollections.observableArrayList(
                new Student("Кармак Джон", 725555666, exa, exaS),
                new Student("Тарашкевич Бронислав Адамович", 1918888, exa, exaS),
                new Student("Платонов Андрей Платонович", 33333333, exa,exaS)
        );
        */

        snpCol.setCellValueFactory(
                new PropertyValueFactory<>("snp")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<>("group")
        );

        for(int i=0; i < EXAM_NUMBER; i++){
            examCol[i] = new TableColumn(Integer.toString(i+1));

            examNameCol[i] = new TableColumn(EXAM_NAME_COLUMN_ABEL);
            /*examNameCol[i].setCellValueFactory(
                    new PropertyValueFactory<>("examName")
            );*/
            examScoreCol[i] = new TableColumn(EXAM_SCORE_COLUMN_NAME);
           /*examScoreCol[i].setCellValueFactory(
                    (TableColumn.CellDataFeatures<Student, String> p) ->{
                        (p.getValue()[i]);
                    }
            );*/
            /*examScoreCol[i].setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Student, Integer[]>,
                            ObservableValue<String>>()
                    {
                        @Override
                        public ObservableValue<String> call( TableColumn.CellDataFeatures<Student,
                                Integer[]> p )
                        {
                            return new ReadOnlyStringWrapper(
                                    Arrays.toString( p.getValue().getExamScore() )
                            );
                        }
                    } );*/
            examScoreCol[i].setCellValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory());

            examCol[i].getColumns().addAll(examNameCol[i],
                                           examScoreCol[i]);
            examsTopCol.getColumns().add(examCol[i]);
        }

        table.getColumns().addAll(snpCol,
                                  groupCol,
                                  examsTopCol);
        //table.setItems(studentList);
        table.setMinHeight(TABLE_HEIGHT);
    }

    public TableView get(){
        return table;
    }
}
