package yt.uz.students.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yt.uz.students.Dto.StudentDto;
import yt.uz.students.Dto.StudentDtoJoin;
import yt.uz.students.Dto.StudentObj;
import yt.uz.students.Repository.StudentDao;

import java.util.List;

@Service
public class StudentDtoServiceImpl implements StudentDtoService {


    @Autowired
    private StudentDao studentDao;

    @Override
    public List<StudentDto> findAllStudentData() {
        return studentDao.findAllStudentData();
    }

    @Override
    public List<StudentObj> findAllStudentJoinInfo(Long id) {
        return studentDao.findAllStudentJoinInfo(id);
    }
}
