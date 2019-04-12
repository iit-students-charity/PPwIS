package plagamedicum.ppvis.lab2s4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Model {
    private ObservableList<Student> studentList;
    private int examNumber = 8;

    public Model(){
        studentList = FXCollections.observableArrayList(
                new Student("Тарашкевіч Браніслаў Адамавіч", "ЛІ19",
                        new ArrayList<>(
                                FXCollections.observableArrayList(
                                        new Exam("Беларуская мова", 99),
                                        new Exam("Мовазнаўства", 10),
                                        new Exam("Беларуская літаратура", 9),
                                        new Exam("Матэматыка", 5),
                                        new Exam("Філязофія", 7),
                                        new Exam("Сусветная гісорыя", 8),
                                        new Exam("ППуІС", 0),
                                        new Exam("Эканоміка", 6)
                                )
                        )
                ),
                new Student("Платонаў Андрэй Платонавіч", "Ч3В3Н7УР",
                        new ArrayList<>(
                                FXCollections.observableArrayList(
                                        new Exam("Руская мова", 1918),
                                        new Exam("Мовазнаўства", 7),
                                        new Exam("Фізіка", 9),
                                        new Exam("Матэматыка", 8),
                                        new Exam("Філасофія", 8),
                                        new Exam("Фалькларыстыка", 10),
                                        new Exam("МВЗуІС", 0),
                                        new Exam("Палітэканомія", 8)
                                )
                        ))
        );
    }

    public ObservableList<Student> getStudentList(){
        return studentList;
    }

    public int getExamNumber(){
        return examNumber;
    }

    public void setExamNumber(int examNumber){
        this.examNumber = examNumber;
    }
}
