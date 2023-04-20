package com.project.SpringApp.controllers;

import com.project.SpringApp.entities.BookedClass;
import com.project.SpringApp.services.BookedClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/booked-classes")
@CrossOrigin(origins = "http://localhost:4200")
public class BookedClassController {

    @Autowired
    private BookedClassService bookedClassService;

    @GetMapping("/{id}")
    public Optional<BookedClass> getBookedClass(@PathVariable Long id) {
        return bookedClassService.getBookedClassById(id);
    }

    @PostMapping
    public BookedClass createBookedClass(@RequestBody BookedClass bookedClass) {
        return bookedClassService.saveBookedClass(bookedClass);
    }


    @DeleteMapping("/{id}")
    public void deleteBookedClass(@PathVariable Long id) {
        bookedClassService.deleteBookedClassById(id);
    }
}
