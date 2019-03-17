package plagamedicum.ppvis.lab2s4.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
	private SimpleStringProperty 	 snp;
	private SimpleIntegerProperty 	 group;
	private SimpleStringProperty[]	 exam;
	private SimpleIntegerProperty[]	 examScore;

	public Student() {

	}

	public Student(String snp, int group,
				   String[] exam, int[] examScore){
		this.snp = new SimpleStringProperty(snp);
		this.group = new SimpleIntegerProperty(group);
		this.exam = new SimpleStringProperty[exam.length];
		this.examScore = new SimpleIntegerProperty[examScore.length];
		for(int i = 0; i< exam.length; i++){
			this.exam[i] = new SimpleStringProperty(exam[i]);
			this.examScore[i] = new SimpleIntegerProperty(examScore[i]);
		}
	}
	
	public String getSnp(){
		return snp.get();
	}

	public void setSnp(String snp){
		this.snp.set(snp);
	}

	public int getGroup(){
		return group.get();
	}

	public void setGroup(int group){
		this.group.set(group);
	}
	
	public String[] getExam(){
		String[] exam = new String[this.exam.length];
		for(int i = 0; i< exam.length; i++){
			exam[i] = this.exam[i].get();
		}
		return exam;
	}

	public void setExam(String[] exam){
		for(int i = 0; i < exam.length; i++) {
			this.exam[i].set(exam[i]);
		}
	}

	public int[] getExamScore(){
		int[] examScore = new int[this.examScore.length];
		for(int i = 0; i< examScore.length; i++){
			examScore[i] = this.examScore[i].get();
		}
		return examScore;
	}

	public void setExamScore(int[] examScore){
		for(int i = 0; i < examScore.length; i++) {
			this.examScore[i].set(examScore[i]);
		}
	}
}
