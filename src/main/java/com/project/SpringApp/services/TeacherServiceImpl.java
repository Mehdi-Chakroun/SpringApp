package com.project.SpringApp.services;

import com.project.SpringApp.entities.Teacher;
import com.project.SpringApp.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    public TeacherServiceImpl() {
        super();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher existingTeacher = optionalTeacher.get();
            existingTeacher.setFirstName(teacher.getFirstName());
            existingTeacher.setLastName(teacher.getLastName());
            existingTeacher.setSubject(teacher.getSubject());
            existingTeacher.setAddress(teacher.getAddress());
            return teacherRepository.save(existingTeacher);
        }
        return null;
    }

    @Override
    public void deleteAllTeachers() {
        teacherRepository.deleteAll();
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);

    }
}
