package plagamedicum.ppvis.lab2s4.model;

//TODO: no more javafx
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Model {
    private ObservableList<Student> studentList;
    private int examNumber = 8;

    public Model(int examNumber, int entitiesNumber){
        this.examNumber = examNumber;
        if(entitiesNumber > 0){
            studentList = generateEntities(entitiesNumber);
        }
    }

    private ObservableList<Student> generateEntities(int entitiesNumber){
        ObservableList<Student> list;
        ArrayList<Student>      students = new ArrayList<>();

        for(int i = 0; i < entitiesNumber; i++){
            students.add(
                    new Student(new SNP("Тарашкевіч Браніслаў Адамавіч"), "ЛІ19",
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

        list = FXCollections.observableArrayList(students);
        return list;
    }

    public ObservableList<Student> getStudentList(){
        return studentList;
    }

    public int getExamNumber(){
        return examNumber;
    }
}
