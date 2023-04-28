//package com.mytry.crud.Repository;
//
//
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface StudentRepo extends JpaRepository<StudentEntity> {
//    @Query(value = "SELECT mark1 + mark2 + mark3 + mark4 + mark5  as total  \n" +
//                    "FROM student \n "+"WHERE register_number= ?;", nativeQuery = true)
//    int findByregister_number(int register_number);
//
//
//
//}
