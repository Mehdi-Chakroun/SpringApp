package com.project.SpringApp.services;

import com.project.SpringApp.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(Long id);
    Teacher saveTeacher(Teacher teacher);

    Teacher updateTeacher(Long id, Teacher teacher);

    void deleteAllTeachers();

    void deleteTeacherById(Long id);
}
