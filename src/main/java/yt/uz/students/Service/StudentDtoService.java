package yt.uz.students.Service;

import yt.uz.students.Dto.StudentDto;
import yt.uz.students.Dto.StudentDtoJoin;
import yt.uz.students.Dto.StudentObj;
import yt.uz.students.Entity.Student;

import java.util.List;

public interface StudentDtoService {

    List<StudentDto> findAllStudentData();

    List<StudentObj> findAllStudentJoinInfo(Long id);

}
