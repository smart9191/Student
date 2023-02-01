package yt.uz.students.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yt.uz.students.Entity.Student;
import yt.uz.students.Entity.StudentSubject;
import yt.uz.students.Entity.Subjects;
import yt.uz.students.Repository.StudentRepository;
import yt.uz.students.Repository.StudentSubjectRepository;
import yt.uz.students.Repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Subjects saveSubjects(Subjects subjects) {
        return subjectRepository.save(subjects);
    }

    @Override
    public StudentSubject studentSubjectAdd(Student student, Subjects subject) {
        List<StudentSubject> found = student.getSubjects().stream()
                .filter(ss -> ss.getSubject().getId().equals(subject.getId()))
                .collect(Collectors.toList());

        if (found.size() > 0) {
            return found.get(0);
        }

        StudentSubject ss = new StudentSubject();
        ss.setStudent(student);
        ss.setSubject(subject);

        studentSubjectRepository.save(ss);

        return ss;
    }

    @Override
    public Subjects findByName(String name) {
        return subjectRepository.findByName(name);
    }


    @Override
    public StudentSubject stdSubjectsToStudent(Long stdId, Long subjectId) {

        Student student = studentRepository.findById(stdId).get();
        Subjects subject = subjectRepository.findById(subjectId).get();

        return studentSubjectAdd(student, subject);
    }

    @Override
    public void deleteSubject(Long id) {

        if(id !=null){
            Subjects sub = subjectRepository.getOne(id);
            subjectRepository.delete(sub);

        }

    }

    @Override
    public void deleteStudent(Long id) {
        Student std = studentRepository.getOne(id);
        studentRepository.delete(std);
    }

    @Override
    public void deleteStdSubjJoinId(Long id) {
        studentSubjectRepository.deleteById(id);
    }


}