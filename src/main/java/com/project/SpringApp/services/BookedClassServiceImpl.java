package com.project.SpringApp.services;

import com.project.SpringApp.entities.BookedClass;
import com.project.SpringApp.repositories.BookedClassRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookedClassServiceImpl implements BookedClassService{
    @Autowired
    private BookedClassRepository bookedClassRepository;

    public BookedClassServiceImpl() {
        super();
    }

    @Override
    public List<BookedClass> getAllBookedClasses() {
        return bookedClassRepository.findAll();
    }

    @Override
    public Optional<BookedClass> getBookedClassById(Long id) {
        return bookedClassRepository.findById(id);
    }

    @Override
    public BookedClass saveBookedClass(BookedClass bookedClass) {
       return bookedClassRepository.save(bookedClass);
    }

    @Override
    public void deleteBookedClassById(Long id) {
        bookedClassRepository.deleteById(id);

    }
}
