package plagamedicum.ppvis.lab2s4.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Student> studentList;
    private int examNumber;

    public Model(int examNumber, int entitiesNumber){
        this.examNumber = examNumber;
        if(entitiesNumber > 0){
            studentList = generateEntities(entitiesNumber);
        } else {
            studentList = new ArrayList<>();
        }
    }

    private List<Student> generateEntities(int entitiesNumber){
        List<Student> students = new ArrayList<>();
        List<Exam>    exams    = new ArrayList<>();

        for(int i = 0; i < entitiesNumber; i++){
            exams.clear();
            exams.add(new Exam("Беларуская мова", 99));
            exams.add(new Exam("Мовазнаўства", 10));
            exams.add(new Exam("Беларуская літаратура", 9));
            exams.add(new Exam("Матэматыка", 5));
            exams.add(new Exam("Філязофія", 7));
            exams.add(new Exam("Сусветная гісорыя", 8));
            exams.add(new Exam("ППуІС", 0));
            exams.add(new Exam("Эканоміка", 6));
            students.add(
                    new Student(new SNP("Тарашкевіч Браніслаў Адамавіч"), "ЛІ19",
                            exams
                    )
            );
        }
        return students;
    }

    public List<Student> getStudentList(){
        return studentList;
    }

    public int getExamNumber(){
        return examNumber;
    }
}
