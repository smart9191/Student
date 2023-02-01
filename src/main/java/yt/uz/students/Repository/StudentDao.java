package yt.uz.students.Repository;

import yt.uz.students.Dto.StudentDto;
import yt.uz.students.Dto.StudentDtoJoin;
import yt.uz.students.Dto.StudentObj;

import java.util.List;

public interface StudentDao {

    List<StudentDto> findAllStudentData();

    List<StudentObj> findAllStudentJoinInfo(Long id);

}
