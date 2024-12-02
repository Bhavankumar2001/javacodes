package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.studentRepository;
@RestController
public class studentController {
	@Autowired
	
	studentRepository repo;
	
	  private boolean studentExists = false;
	
//	get all students
	@GetMapping("/students")
	public List<Student> getallstudents(){
		List<Student> students = repo.findAll();
	 
		return students;
		
	}
	
//	get students based on id
	@GetMapping("/students/{id}")
	public Student getStudentbyid(@PathVariable int  id ) {
		Student student = repo.findById(id).get();
		return student;
		
	}
	
	@PostMapping("/student/add")
    public void createOrUpdateStudent(@RequestBody Student student) {
        // Check if the student ID already exists
        if (repo.existsById(student.getRollNo())) {
            studentExists = true; // Set flag if student exists
        } else {
            repo.save(student); // Save the new student
            studentExists = false;
        }
    }
	
	 // Scheduler to log "Already exists" message every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void logIfStudentExists() {
        if (studentExists) {
            System.out.println("Student already exists with the given ID.");
        }
    }
    
	
	@PutMapping("/student/update/{id}")
	public Student updatestudent(@PathVariable int id) {
		Student student =repo.findById(id).get();
		student.setName("Manish");
		student.setBranch("Fashion");
		repo.save(student);
		return student;
	}
	@DeleteMapping("/student/delete/{id}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseEntity<String> deletestudent(@PathVariable int id ) {
		Student student = repo.findById(id).get();
		repo.delete(student);
		   return ResponseEntity.ok("deleted");
		
	}
	
	 @PostMapping("/savedata")
	    public ResponseEntity<List<Student>> addProducts(@RequestBody List<Student> products) {
	        List<Student> savedProducts = repo.saveAll(products);
	        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
	    }
}
