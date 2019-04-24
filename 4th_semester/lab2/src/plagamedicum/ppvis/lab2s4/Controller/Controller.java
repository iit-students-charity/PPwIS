package plagamedicum.ppvis.lab2s4.Controller;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import plagamedicum.ppvis.lab2s4.model.Model;
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

    public void openDoc(File file) throws ParserConfigurationException, SAXException {
        SAXParserFactory parserFactory;
        SAXParser        parser;

        parserFactory = SAXParserFactory.newInstance();
        parser        = parserFactory.newSAXParser();
    }

    private static class XMLHandler extends DefaultHandler{
        @Override
        public void startDocument()throws SAXException{

        }

        @Override
        public void endDocument() throws SAXException {

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException{

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException{

        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException{
            
        }
    }

    public void saveDoc(File file) {
        List<Student> studentList = model.getStudentList();
        Student                studenti;
        Element                students,
                               student,
                               snp,
                               group,
                               exams,
                               exam;
        Attr                   studentNum,
                               surname,
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

            //TODO: move creations in declarations
            //TODO: foreach
            for (int i = 0; i < studentList.size(); i++){
                studenti = studentList.get(i);

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
                studentNum = doc.createAttribute("number");
                studentNum.setValue(((Integer)i).toString());
                student.setAttributeNode(studentNum);
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
}
