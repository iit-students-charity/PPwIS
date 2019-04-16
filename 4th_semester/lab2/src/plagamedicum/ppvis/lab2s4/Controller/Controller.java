package plagamedicum.ppvis.lab2s4.Controller;

import javafx.collections.ObservableList;
import plagamedicum.ppvis.lab2s4.model.Model;
import plagamedicum.ppvis.lab2s4.model.Student;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public ObservableList<Student> getStudentList(){
        return model.getStudentList();
    }

    public int getExamNumber(){
        return model.getExamNumber();
    }

    public void setExamNumber(int examNumber){
        model.setExamNumber(examNumber);
    }

    public void openFile(){

    }

    public void saveFile(){

    }
}
