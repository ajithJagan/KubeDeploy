//package com.mytry.crud.Service.Impl;
//
//import com.mytry.crud.Entity.StudentEntity;
//import com.mytry.crud.Repository.StudentRepo;
//import com.mytry.crud.Service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class StudentImpl implements StudentService {
//
//    @Autowired
//    StudentRepo studentRepo;
//    @Override
//    public List<StudentEntity> getStudent() {
//        return studentRepo.findAll();
//    }
//    @Override
//    public StudentEntity insertStudent(StudentEntity studentEntity) {
//        return studentRepo.save(studentEntity);
//    }
//    @Override
//    public Optional<StudentEntity> getByid(int id) {
//        return studentRepo.findById(id);
//    }
//    @Override
//    public int total(int register_number) {
//        int s= studentRepo.findByregister_number(register_number);
//        return s;
//    }
//    @Override
//    public int percentage(int register_number) {
//        double percentage;
//        int s= studentRepo.findByregister_number(register_number);
//        percentage = s/5;
//        System.out.println(percentage);
//        return (int) percentage;
//    }
//
//}
