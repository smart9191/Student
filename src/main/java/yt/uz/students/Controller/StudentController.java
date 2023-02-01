package yt.uz.students.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.uz.students.Dto.StudentDto;
import yt.uz.students.Entity.Student;
import yt.uz.students.Entity.Subjects;
import yt.uz.students.Repository.StudentRepository;
import yt.uz.students.Service.ExcelService;
import yt.uz.students.Service.StudentDtoService;
import yt.uz.students.Service.StudentService;
import yt.uz.students.request.StudentSubjectRequest;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Api(description = "Student info project")
@RestController
@RequestMapping("/api")

public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDtoService studentDtoService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExcelService excelService;



    @ApiOperation(value = "Yangi student kirirish apisi")
    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return ResponseEntity.ok().body(studentService.saveStudent(student));
    }

    @ApiOperation(value = "Mavjud student ma'lumotlarini yangilash")
    @PutMapping("/update/student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        return ResponseEntity.ok().body(studentService.saveStudent(student));
    }


    @ApiOperation(value = "Student va fanlar ma'lumotlari birgalikda kelsa ularni saqlash apisi")
    @Transactional
    @PostMapping("/save-multi")
    public Student saveMulti(@RequestBody StudentSubjectRequest data) {
        Student student = studentService.saveStudent(data.getStudent());


        for(Subjects s : data.getSubjects()) {

            Subjects s1 = studentService.findByName(s.getName());
            if(s1 != null){
                studentService.studentSubjectAdd(student,s1);

            }else {

                Subjects sbj = studentService.saveSubjects(s);

                studentService.studentSubjectAdd(student, sbj);
            }

        }

        return student;
    }

    @ApiOperation(value = "Hamma studentlar ro'yxatini olish")
    @GetMapping("/all")
    public ResponseEntity findAllStudent(){
        return ResponseEntity.ok().body(studentRepository.findAll());
    }


    @ApiOperation(value = "Studentni bazadan o'chirish apisi(parametr sifatida id si beriladi)")
    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Talaba o'chirildi");
    }


    @ApiOperation(value = "Studentlar ma'lumotlarini excelga exxport qilish apisi")
    @GetMapping("/excel-export")
    public void createDataExcelStudent(HttpServletResponse response) throws Exception{
        List<StudentDto> studentDtoList = studentDtoService.findAllStudentData();
        excelService.StudentAllDataListExcel(studentDtoList,response);
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentId(@PathVariable Long id){
        Student student = studentRepository.findById(id).get();

        return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
    }



}
