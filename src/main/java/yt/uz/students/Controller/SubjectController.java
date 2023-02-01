package yt.uz.students.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.uz.students.Entity.Subjects;
import yt.uz.students.Repository.SubjectRepository;
import yt.uz.students.Service.StudentService;

@Api(description = "Studentlar fanlari ro'yxati")
@RestController
@RequestMapping("api")
public class SubjectController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectRepository subjectRepository;


    @ApiOperation(value = "Fanlarni bazaga saqlash apisi")
    @PostMapping("/save/subj")
    public ResponseEntity<Subjects> saveSubject(@RequestBody Subjects subjects){
        return ResponseEntity.ok().body(studentService.saveSubjects(subjects));
    }

    @ApiOperation(value = "Fanlarni yangilash apisi")
    @PutMapping("/update/subj")
    public ResponseEntity<Subjects> updateSubject(@RequestBody Subjects subjects){
        return ResponseEntity.ok().body(studentService.saveSubjects(subjects));
    }


    @ApiOperation(value = "Fanlarni bazadan olib tashlash apisi(parametr sifatida id si beriladi)")
    @DeleteMapping("/subject/{id}")
    public ResponseEntity deleteSubject(@PathVariable Long id){
        studentService.deleteSubject(id);
        return ResponseEntity.ok("Fan o'chirildi");
    }

    @ApiOperation(value = "Hamma fanlar ro'yxatini olish")
    @GetMapping("/all/subject")
    public ResponseEntity getAllSubject(){
        return ResponseEntity.ok().body(subjectRepository.findAll());
    }



}
