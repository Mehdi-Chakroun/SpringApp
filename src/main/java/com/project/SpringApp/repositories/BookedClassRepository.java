package com.project.SpringApp.repositories;

import com.project.SpringApp.entities.BookedClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedClassRepository extends JpaRepository<BookedClass, Long> {
}
