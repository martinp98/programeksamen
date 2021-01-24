package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Supervisor;
import com.example.demo.repository.StudentRepo;
import com.example.demo.repository.SupervisorRepo;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SupervisorController {

    SupervisorRepo supervisorRepo;
    StudentRepo studentRepo;

    public SupervisorController(SupervisorRepo supervisorRepo, StudentRepo studentRepo)
    {
        this.supervisorRepo = supervisorRepo;
        this.studentRepo = studentRepo;
    }

    @GetMapping("/supervisor")
    public Iterable<Supervisor> index()
    {
        return supervisorRepo.findAll();
    }

    @GetMapping("/supervisor/{id}")
    public ResponseEntity<Optional<Supervisor>> readOne(@PathVariable int id)
    {
        Optional<Supervisor> res = supervisorRepo.findById(id);
        if(res.isPresent()){
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).body(res); // TODO send
        }
    }

    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/supervisor")
    public ResponseEntity<String> create(@ModelAttribute Supervisor p){
        Supervisor supervisor = supervisorRepo.save(p);
        return ResponseEntity.status(201).header("Location", "/Student/" + supervisor.getId()).body("{'Msg': 'Supervisor created'}");
    }

    @PutMapping("/supervisor")
    public ResponseEntity<String> update(@ModelAttribute Supervisor p)
    {
        supervisorRepo.save(p);
        return ResponseEntity.status(204).body("{'msg':'Hello'");
    }

    @DeleteMapping("/supervisor/{id}")
    public ResponseEntity<String> delete(@PathVariable int id)
    {
        supervisorRepo.deleteById(id);
        return ResponseEntity.status(200).body("{'msg:'Deleted'}");
    }
}
