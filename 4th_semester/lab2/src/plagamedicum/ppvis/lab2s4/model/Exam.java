package plagamedicum.ppvis.lab2s4.model;


public class Exam {
    private String  examName;
    private int     examScore;

    public Exam(String examName, int examScore) {
        this.examName = examName;
        this.examScore = examScore;
    }

    public String getExamName(){
        return examName;
    }

    public void setExamName(String examName){
        this.examName = examName;
    }

    public int getExamScore(){
        return examScore;
    }

    public void setExamScore(int examScore){
        this.examScore = examScore;
    }
}
