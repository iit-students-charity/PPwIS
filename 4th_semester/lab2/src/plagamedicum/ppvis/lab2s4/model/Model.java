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
        List<Exam>    examList = new ArrayList<>();

        for(int i = 0; i < entitiesNumber; i++){
            examList.clear();
            examList.add(new Exam("Беларуская мова", 99));
            examList.add(new Exam("Мовазнаўства", 10));
            examList.add(new Exam("Беларуская літаратура", 9));
            examList.add(new Exam("Матэматыка", 5));
            examList.add(new Exam("Філязофія", 7));
            examList.add(new Exam("Сусветная гісорыя", 8));
            examList.add(new Exam("ППуІС", 0));
            examList.add(new Exam("Эканоміка", 6));
            students.add(
                    new Student(new SNP("Тарашкевіч Браніслаў Адамавіч"), "ЛІ19",
                            examList
                    )
            );
        }
        return students;
    }

    public List<Student> getStudentList(){
        return studentList;
    }

    public void setStudentList(List<Student> studentList){
        this.studentList = studentList;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public int getExamNumber(){
        return examNumber;
    }
}
