package plagamedicum.ppvis.lab2s4.Controller;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import plagamedicum.ppvis.lab2s4.model.Exam;
import plagamedicum.ppvis.lab2s4.model.Model;
import plagamedicum.ppvis.lab2s4.model.SNP;
import plagamedicum.ppvis.lab2s4.model.Student;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public List<Student> getStudentList(){
        return model.getStudentList();
    }

    public int getExamNumber(){
        return model.getExamNumber();
    }

    public void newDoc(int examNumber, int entitiesNumber){
        this.model = new Model(examNumber, entitiesNumber);
    }

    public void addStudent(String surname, String name, String patronym, String group, List<Exam> examList){
        model.addStudent(
                new Student(new SNP(surname, name, patronym), group, examList)
        );
    }

    public void openDoc(File file) {
        try {
            model.setStudentList(DocOpener.openDoc(file));
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public void saveDoc(File file) {
        List<Student>          studentList = model.getStudentList();
        Element                students,
                               student,
                               snp,
                               group,
                               exams,
                               exam;
        Attr                   surname,
                               name,
                               patronym,
                               groupName,
                               examName,
                               examScore;
        Document               doc;
        DocumentBuilderFactory docBuilderFactory;
        DocumentBuilder        docBuilder;
        TransformerFactory     transformerFactory;
        Transformer            transformer;
        DOMSource              source;
        StreamResult           streamResult;

        try{
            docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            students = doc.createElement("students");
            doc.appendChild(students);

            for (Student studenti : studentList){
                surname = doc.createAttribute("surname");
                surname.setValue(studenti.getSnp().getSurname());
                name = doc.createAttribute("name");
                name.setValue(studenti.getSnp().getName());
                patronym = doc.createAttribute("patronym");
                patronym.setValue(studenti.getSnp().getPatronym());
                snp = doc.createElement("snp");
                snp.setAttributeNode(surname);
                snp.setAttributeNode(name);
                snp.setAttributeNode(patronym);

                group = doc.createElement("group");
                groupName = doc.createAttribute("name");
                groupName.setValue(studenti.getGroup());
                group.setAttributeNode(groupName);

                exams = doc.createElement("exams");
                for(int j = 0; j < model.getExamNumber(); j++){
                    examName = doc.createAttribute("name");
                    examName.setValue(studenti.getExamName(j));
                    examScore = doc.createAttribute("score");
                    examScore.setValue(((Integer) studenti.getExamScore(j)).toString());

                    exam = doc.createElement("exam");
                    exam.setAttributeNode(examName);
                    exam.setAttributeNode(examScore);
                    exams.appendChild(exam);
                }

                student = doc.createElement("student");
                student.appendChild(snp);
                student.appendChild(group);
                student.appendChild(exams);
                students.appendChild(student);
            }

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);
            streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);
        } catch (Exception  exception){
            exception.printStackTrace();
            return;
        }
    }

    public List search(boolean delete, int selectedItem, List<String> criteriaList){
        final String SURNAME        = criteriaList.get(0);
        int          studentIndex = 0;
        List<Student> studentList = getStudentList();
        List          resultList;

        if(delete){
            resultList = new ArrayList<Integer>();
        } else {
            resultList = new ArrayList<Student>();
        }

        switch (selectedItem){
            case 0:
                final String AVERAGE_SCORE        = criteriaList.get(1);
                Integer      studentsMinimalScore = 10,
                             studentsMaximalScore = 1,
                             studentsAverageScore;

                for(Student student:studentList){
                    for(Exam exam:student.getExamList()){
                        if(exam.getScore() < studentsMinimalScore){
                            studentsMinimalScore = exam.getScore();
                        }
                        if (exam.getScore() > studentsMaximalScore){
                            studentsMaximalScore = exam.getScore();
                        }
                    }
                    studentsAverageScore = (studentsMaximalScore + studentsMinimalScore) / 2;
                    if(student.getSurname().equals(SURNAME) && studentsAverageScore == Integer.valueOf(AVERAGE_SCORE)){
                        if(delete){
                            resultList.add(studentIndex);
                        }else{
                            resultList.add(student);
                        }
                    }
                    studentIndex++;
                }
                break;
            case 1:
                final String GROUP = criteriaList.get(2);

                for(Student student:studentList) {
                    if (student.getSurname().equals(SURNAME) & student.getGroup().equals(GROUP)) {
                        if(delete){
                            resultList.add(studentIndex);
                        }else{
                            resultList.add(student);
                        }
                    }
                    studentIndex++;
                }
                break;
            case 2:
                final String  DISCIPLINE    = criteriaList.get(3);
                final Integer SCORE         = Integer.valueOf(criteriaList.get(4));
                boolean       examExists    = false;

                for(Student student:studentList) {
                    for(Exam exam:student.getExamList()){
                        if(exam.getName().equals(DISCIPLINE) && exam.getScore() == SCORE){
                            examExists = true;
                        }
                    }
                    if (student.getSurname().equals(SURNAME) && examExists) {
                        if(delete){
                            resultList.add(studentIndex);
                        }else{
                            resultList.add(student);
                        }
                    }
                    studentIndex++;
                }
                break;
        }

        return resultList;
    }

    public void delete(List<Integer> indexList){
        for(int i:indexList){
            getStudentList().remove(i);
        }
    }
}
