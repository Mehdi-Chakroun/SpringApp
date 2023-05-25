package com.project.SpringApp.services;

import com.project.SpringApp.entities.BookedClass;
import com.project.SpringApp.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface BookedClassService {
    List<BookedClass> getAllBookedClasses();
    Optional<BookedClass> getBookedClassById(Long id);
    BookedClass saveBookedClass(BookedClass bookedClass);
    void deleteBookedClassById(Long id);
    List<BookedClass> findByStudent(User student);
    List<BookedClass> findByTeacher(User teacher);
    List<BookedClass> getAcceptedBookedClasses(Long id);
    List<BookedClass> getPendingBookedClasses(Long id);

}
