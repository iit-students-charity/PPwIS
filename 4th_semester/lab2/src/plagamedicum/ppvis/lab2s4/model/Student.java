package plagamedicum.ppvis.lab2s4.model;

import java.util.List;

public class Student {
	private String 	snp,
					group;
	Exam 			exam;
	public Student(){

	}

	public Student(String snp, String group,
				   List examName, List examScore){
		this.snp = snp;
		this.group = group;
		this.exam.setExamName(examName);
		this.exam.setExamScore(examScore);
	}
	
	public String getSnp(){
		return snp;
	}

	public void setSnp(String snp){
		this.snp = snp;
	}

	public String getGroup(){
		return group;
	}

	public void setGroup(String group){
		this.group = group;
	}

	public Exam getExam(){
		return exam;
	}

	/*
	public int getExamNumber(){
		return examNumber;
	}

	public void setExamNumber(){
		this.examNumber = examNumber;
	}
	*/
}
