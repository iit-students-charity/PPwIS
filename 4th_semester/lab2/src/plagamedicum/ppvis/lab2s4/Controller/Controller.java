package plagamedicum.ppvis.lab2s4.Controller;

import javafx.collections.ObservableList;
import plagamedicum.ppvis.lab2s4.model.Model;
import plagamedicum.ppvis.lab2s4.model.Student;

public class Controller {
    private ObservableList<Student> studentList;

    public Controller(Model model){
        this.studentList = model.getStudentList();
    }

    public ObservableList<Student> getStudentList(){
        return studentList;
    }
}
