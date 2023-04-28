//package com.mytry.crud;
//
//import com.mytry.crud.Entity.StudentEntity;
//import com.mytry.crud.Repository.StudentRepo;
//import com.mytry.crud.Service.Impl.StudentImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.slf4j.Logger;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class JunitTest {
//
//    @Mock
//    StudentRepo studentRepo;
//
//    @InjectMocks
//    StudentImpl studentimpl;
//
//    @Mock
//    Logger logger;
//
////    @Test
////    public void insertStudent(){
////        StudentEntity student=new StudentEntity(1,"Ajith",23,67,78,98,76,98,450,89);
////        when(logger.isInfoEnabled()).thenReturn(false);
////        when(studentRepo.save(student)).thenReturn(student);
////        assertEquals(student,studentimpl.insertStudent(student));
////    }
////    @Test
////    public void getById(){
////        StudentEntity student=new StudentEntity(1,"Ajith",23,67,78,98,76,98,450,89);
////        StudentEntity student1=new StudentEntity(2,"Abi",23,67,78,98,76,98,450,89);
////        StudentEntity student2=new StudentEntity(3,"Ajith",23,67,78,98,76,98,450,89);
////        StudentEntity student3=new StudentEntity(4,"Ajith",23,67,78,98,76,98,450,89);
////        List<StudentEntity> list = new ArrayList<>();
////        list.add(student);
////        list.add(student1);
////        list.add(student2);
////        list.add(student3);
////        when(studentRepo.findAll()).thenReturn(list);
////        assertEquals(4,studentimpl.getStudent().size());
////    }
//    @Test
//   void insertStudent(){
//        when(studentRepo.findByregister_number(3)).thenReturn(390);
//        assertEquals(390,studentimpl.total(3));
//
////        StudentEntity studentEntity=new StudentEntity();
////        studentEntity.setTotal(397);
////        studentEntity.getTotal();
////        studentEntity.setPercentage(67);
////        studentEntity.getPercentage();
////         when(studentRepo.save(studentEntity)).thenReturn(studentEntity);
//
//    }
//
////    @Test
////    public void getEmployeeById() {
////
////        Optional<BasicDetails> basicDetails = Optional.ofNullable(new BasicDetails(2, "xxx", "yyy", "Chennai", "7894561222"));
////
////        when(basicRepository.findById(2)).thenReturn(basicDetails);
////        assertEquals(2, basicService.deleteData(2));
////
////    }
//
//
//    @Test
//    void getAll(){
//          StudentEntity studentEntity=new StudentEntity();
//          studentEntity.setTotal(397);
//          when(studentRepo.findByregister_number(1)).thenReturn(studentEntity.getTotal());
//          assertEquals(390,studentimpl.total(1));
//
//    }
//}
