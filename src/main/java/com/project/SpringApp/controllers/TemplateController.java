package com.project.SpringApp.controllers;

import com.project.SpringApp.entities.BookedClass;
import com.project.SpringApp.entities.User;
import com.project.SpringApp.services.BookedClassServiceImpl;
import com.project.SpringApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class TemplateController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    BookedClassServiceImpl bookedClassService;
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Return the name of the register form template
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.user", "Passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            // If there are validation errors, return back to the registration form
            return "register";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPassword(passwordEncoder.encode(user.getConfirmPassword()));
            userService.saveUser(user);
            // If registration is successful, redirect to a success page or login page
            return "redirect:/login";
        }
    }
    @RequestMapping(value = "/teacher/dashboard", method = RequestMethod.GET)
    public String showTeacherDashboard(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> optTeacher = userService.findByUsername(username);
        User teacher = optTeacher.orElse(null);
        model.addAttribute("teacher", teacher);
        model.addAttribute("availability", teacher.isAvailability());
        List<BookedClass> pending = bookedClassService.getPendingBookedClasses(teacher.getId());
        List<BookedClass> accepted = bookedClassService.getAcceptedBookedClasses(teacher.getId());
        model.addAttribute("pending", pending);
        model.addAttribute("accepted", accepted);
        return "teacher-dashboard"; // Return the template name for the teacher dashboard
    }
    @PostMapping("/teacher/toggleAvailability")
    public String toggleAvailability(Principal principal) {
        String username = principal.getName();
        Optional<User> opt = userService.findByUsername(username);
        User teacher = opt.orElse(null);

        // Toggle the availability value
        teacher.setAvailability(!teacher.isAvailability());

        // Save the updated user object to the database
        userService.saveUser(teacher);

        return "redirect:/teacher/dashboard";
    }


    @GetMapping("/student/dashboard")
    public String showStudentDashboard(Model model, Principal principal) {
        User student = userService.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("student",student);
        List<User> teachers = userService.findTeachersWithAvailability();
        model.addAttribute("teachers", teachers);
        List<BookedClass> pending = bookedClassService.getPendingBookedClasses(student.getId());
        List<BookedClass> accepted = bookedClassService.getAcceptedBookedClasses(student.getId());
        model.addAttribute("pending", pending);
        model.addAttribute("accepted", accepted);
        return "student-dashboard"; // Return the template name for the student dashboard
    }
    @PostMapping("/bookClass")
    public String bookClass(@RequestParam("teacherId") Long teacherId, @RequestParam("classDate") LocalDate classDate, @RequestParam("classTime") LocalTime classTime, Principal principal){
        User teacher = userService.findUserById(teacherId).orElse(null);
        User student = userService.findByUsername(principal.getName()).orElse(null);
        BookedClass bookedClass = new BookedClass();
        bookedClass.setTeacher(teacher);
        bookedClass.setClassDate(classDate);
        bookedClass.setClassTime(classTime);
        bookedClass.setSubject(teacher.getSubject());
        bookedClass.setStudent(student);
        bookedClassService.saveBookedClass(bookedClass);
        return "redirect:/student/dashboard";
    }
    @PostMapping("/classAction")
    public String classAction(@RequestParam("requestId") Long id,
                              @RequestParam(value = "acceptBtn", required = false) String acceptBtn,
                              @RequestParam(value = "cancelBtn", required = false) String cancelBtn,
                              Principal principal){
        if (cancelBtn != null) bookedClassService.deleteBookedClassById(id);
        else if (acceptBtn != null) {
            BookedClass bc = bookedClassService.getBookedClassById(id).orElse(null);
            bc.setAccepted(true);
            bookedClassService.saveBookedClass(bc);
        }
        User user = userService.findByUsername(principal.getName()).orElse(null);
        if(user.getRole().equals("TEACHER")) return "redirect:/teacher/dashboard";
        else return "redirect:/student/dashboard";
    }
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}
