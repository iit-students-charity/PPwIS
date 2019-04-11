package plagamedicum.ppvis.lab2s4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Model {
    private ObservableList<Student> studentList;

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
                        ))
        );
    }

    public ObservableList<Student> getStudentList(){
        return studentList;
    }
}
