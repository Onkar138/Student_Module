package com.tnsif.Controller;

import com.tnsif.Payload.PageResponse;
import com.tnsif.Payload.StudentDTO;
import com.tnsif.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RequestMapping("/api/Student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // http://localhost:8080/api/Student/Post

    @PostMapping("/Post")
    public ResponseEntity<StudentDTO> createData(@RequestBody StudentDTO studentDTO){
        StudentDTO DTO = studentService.createStudentData(studentDTO);
        return new ResponseEntity<>(DTO, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/Student

    @GetMapping
    public List<StudentDTO> getAllData(){

        return studentService.getAllStudentData();
    }

    // http://localhost:8080/api/Student/1

    @GetMapping("/{s_id}")
    public ResponseEntity<StudentDTO> getAllDataById(@PathVariable("s_id") long id){

        StudentDTO DTO = studentService.getAllStudentDataById(id);
        return new ResponseEntity<>(DTO,HttpStatus.OK);
    }

    // http://localhost:8080/api/Student/Pagination?pageNo=0&pageSize=2&sortBy=course&sortDir=asc

    @GetMapping("/Pagination")
    public PageResponse getAllData(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "2",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
        return studentService.getAllStudentPaginationData(pageNo,pageSize,sortBy,sortDir);
    }

    // http://localhost:8080/api/Student/1

    @PutMapping("/{s_id}")
    public ResponseEntity<StudentDTO> updateData(@PathVariable("s_id") long id,@RequestBody StudentDTO studentDTO){
        StudentDTO DTO = studentService.updateStudentData(id, studentDTO);
        return new ResponseEntity<>(DTO,HttpStatus.CREATED);
    }

    // http://localhost:8080/api/Student/1

    @DeleteMapping("/{s_id}")
    public ResponseEntity<String> deleteData(@PathVariable("s_id") long id){
        studentService.deleteStudentData(id);
        return new ResponseEntity<>("Student Data Deleted successfully !!",HttpStatus.OK);
    }
}
