package yt.uz.students.request;


import yt.uz.students.Entity.Student;
import yt.uz.students.Entity.Subjects;

import java.util.List;

public class StudentSubjectRequest {

    private Student student;
    private List<Subjects> subjects;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }
}
