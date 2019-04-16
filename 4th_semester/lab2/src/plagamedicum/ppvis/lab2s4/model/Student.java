package plagamedicum.ppvis.lab2s4.model;

import java.util.ArrayList;

public class Student {
	private SNP 	snp;
	private String 	group;
	private ArrayList<Exam> exam;

	public Student(SNP snp, String group,
				   ArrayList<Exam> exam){
		this.snp   = snp;
		this.group = group;
		this.exam  = exam;
	}

	public SNP getSnp(){
		return snp;
	}

	public void setSnp(SNP snp){
		this.snp = snp;
	}

	public String getAlignSnp(){
		return snp.getSurname()+" "+snp.getName()+" "+snp.getPatronym();
	}

	public void setAlignSnp(String alignSnp){
		this.snp = new SNP(alignSnp);
	}

	public String getGroup(){
		return group;
	}

	public void setGroup(String group){
		this.group = group;
	}

	public ArrayList<Exam> getExam(){
		return exam;
	}

	public void setExam(ArrayList<Exam> exam){
		this.exam = exam;
	}

	public String getExamName(int i){
		return exam.get(i).getName();
	}

	public int getExamScore(int i){
		return exam.get(i).getScore();
	}
}
