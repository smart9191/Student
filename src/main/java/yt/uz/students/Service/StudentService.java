package yt.uz.students.Service;


import yt.uz.students.Entity.Student;
import yt.uz.students.Entity.StudentSubject;
import yt.uz.students.Entity.Subjects;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    Subjects saveSubjects(Subjects subjects);

    StudentSubject stdSubjectsToStudent(Long stdId, Long subjectId);

    StudentSubject studentSubjectAdd(Student student, Subjects subject);

    Subjects findByName(String name);


    void deleteSubject(Long id);
    void deleteStudent(Long id);


    void deleteStdSubjJoinId(Long id);
}
