package com.project.SpringApp.repositories;

import com.project.SpringApp.entities.BookedClass;
import com.project.SpringApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedClassRepository extends JpaRepository<BookedClass, Long> {

    List<BookedClass> findByStudent(User student);

    List<BookedClass> findByTeacher(User teacher);
    @Query("SELECT bc FROM BookedClass bc WHERE bc.accepted = true  AND (bc.student.id = :userID OR bc.teacher.id = :userID)")
    List<BookedClass> findAcceptedBookedClasses(@Param("userID") Long id);
    @Query("SELECT bc FROM BookedClass bc WHERE bc.accepted = false  AND (bc.student.id = :userID OR bc.teacher.id = :userID)")
    List<BookedClass> findPendingBookedClasses(@Param("userID") Long id);

}

