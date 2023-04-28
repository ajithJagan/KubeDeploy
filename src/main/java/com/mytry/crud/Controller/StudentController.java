package com.mytry.crud.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
//
//    @Autowired
//    StudentRepo studentRepo;
//
//    @Autowired
//    StudentService studentService;
//
//    @GetMapping("/getAllStudent")
//    List<StudentEntity>getAllStudent(){
//        return studentService.getStudent();
//    }
//
//    @PostMapping("/insertStudent")
//    StudentEntity insertStudent(@RequestBody StudentEntity studentEntity){
//        return studentService.insertStudent(studentEntity);
//    }
//
//    @GetMapping("/getById{id}")
//    Optional<StudentEntity> getById(@RequestParam int id){
//        return studentService.getByid(id);
//    }
//
//    @PostMapping("/total")
//    int total(@RequestParam int id){
//        return studentService.total(id);
//    }
//
//    @PostMapping("/percentage")
//    int percentage(@RequestParam int register_number){
//        return studentService.percentage(register_number);
//    }
//
//


    @GetMapping("/show")
    public String backEnd() {
        return "Hi I'm Backend >>>>>>>>>>>>>>>";
    }


}
