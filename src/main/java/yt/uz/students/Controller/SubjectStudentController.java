package yt.uz.students.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.uz.students.Entity.StudentSubject;
import yt.uz.students.Repository.StudentSubjectRepository;
import yt.uz.students.Service.StudentService;

import java.util.HashMap;
import java.util.Map;

@Api(description = "Student va fanlar")
@RestController
@RequestMapping("api")
public class SubjectStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentSubjectRepository stdSubjRepo;


    @ApiOperation("Student o'qidigan fanlarni kiritish(parametr sifatida 'stdId' da student id si 'subjectId' da fanlar idlari keladi)")
    @PostMapping("/{stdId}/subject/{subjectId}")
    public Map<String, Long> stdSubjectsToStudent(
            @PathVariable Long stdId,
            @PathVariable Long subjectId
    ){
        StudentSubject ss = studentService.stdSubjectsToStudent(stdId,subjectId);

        return new HashMap<>() {{
            put("id", ss.getId());
        }};
    }


    @ApiOperation(value = "Student va fanlar bog'langan jafval ma'lumotlarini o'chirish(id orqali)")
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteStdSubjJoinTableId(@PathVariable Long id){
        studentService.deleteStdSubjJoinId(id);
        return ResponseEntity.ok("Ma'lumot o'chirildi");
    }

    @ApiOperation(value = "Student va fanlar bog'langan ma'lumotlarini olish apisi")
    @GetMapping("/all/std/subj")
    public ResponseEntity getAllStdSubj(){
        return ResponseEntity.ok().body(stdSubjRepo.findAll());
    }


}
