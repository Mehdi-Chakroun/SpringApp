package com.project.SpringApp.services;

import com.project.SpringApp.entities.BookedClass;

import java.util.List;
import java.util.Optional;

public interface BookedClassService {
    List<BookedClass> getAllBookedClasses();
    Optional<BookedClass> getBookedClassById(Long id);
    BookedClass saveBookedClass(BookedClass bookedClass);
    void deleteBookedClassById(Long id);
}
