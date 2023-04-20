package com.project.SpringApp.repositories;

import com.project.SpringApp.entities.BookedClass;
import com.project.SpringApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedClassRepository extends JpaRepository<BookedClass, Long> {

    List<BookedClass> findByStudent(User student);

    List<BookedClass> findByTeacher(User teacher);

}

