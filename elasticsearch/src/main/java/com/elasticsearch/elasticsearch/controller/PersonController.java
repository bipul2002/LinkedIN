//package com.elasticsearch.elasticsearch.controller;
//
//import com.elasticsearch.elasticsearch.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/person")
//public class PersonController {
//    @Autowired
//    private PersonService personService;
//
//    @GetMapping("/all")
//    public Iterable<User> findAllPerson()
//    {
//        return personService.findAll();
//    }
//
//
//    @PostMapping("/create")
//    public ResponseEntity<String> addAPerson(@RequestBody User p)
//    {
//        personService.create(p);
//        return  ResponseEntity.ok("Person is added");
//    }
//
//    @GetMapping("/{id}")
//    public User findPersonById(@PathVariable("id") Long id)
//    {
//        return personService.findById(id);
//    }
//}
