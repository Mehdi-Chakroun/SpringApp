package com.project.SpringApp.services;

import com.project.SpringApp.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Long id, Student student);

    void deleteStudentById(Long id);
    void deleteAllStudents();
}
