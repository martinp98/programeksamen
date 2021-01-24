package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Supervisor;
import com.example.demo.repository.StudentRepo;
import com.example.demo.repository.SupervisorRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudentController {

    SupervisorRepo supervisorRepo;
    StudentRepo studentRepo;

    public StudentController(SupervisorRepo supervisorRepo, StudentRepo studentRepo)
    {
        this.supervisorRepo = supervisorRepo;
        this.studentRepo = studentRepo;
    }

    @GetMapping("/student")
    public Iterable<Student> index()
    {
        return studentRepo.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> readOne(@PathVariable int id)
    {
        Optional<Student> res = studentRepo.findById(id);
        if(res.isPresent()){
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).body(res); // TODO send
        }
    }

    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/student")
    public ResponseEntity<String> create(@ModelAttribute Student p){
        Student student = studentRepo.save(p);
        return ResponseEntity.status(201).header("Location", "/Student/" + student.getId()).body("{'Msg': 'Student created'}");
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<String> update(@ModelAttribute Student p)
    {
        studentRepo.save(p);
        return ResponseEntity.status(204).body("{'msg':'Hello'");
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable int id)
    {
        studentRepo.deleteById(id);
        return ResponseEntity.status(200).body("{'msg:'Deleted'}");
    }

}
