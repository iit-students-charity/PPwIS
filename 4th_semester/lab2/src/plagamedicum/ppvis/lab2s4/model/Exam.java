package plagamedicum.ppvis.lab2s4.model;

import java.util.List;

public class Exam {
    private static int  examNumber = 5;
    private List        examName,
                        examScore;

    public List getExamName(){
        return examName;
    }

    public void setExamName(List examName){
        this.examName = examName;
    }

    public List getExamScore(){
        return examScore;
    }

    public void setExamScore(List examScore){
        this.examScore = examScore;
    }

    public int getExamNumber(){
        return examNumber;
    }
}
