package com.tnsif.Controller;

import com.tnsif.Payload.CollegeDTO;
import com.tnsif.Service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/College")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    // http://localhost:8080/api/College/Post

    @PostMapping("/Post")
    public ResponseEntity<CollegeDTO> createData(@RequestBody CollegeDTO collegeDTO){
        CollegeDTO DTO = collegeService.createCollegeData(collegeDTO);
        return new ResponseEntity<>(DTO, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/College

    @GetMapping
    public List<CollegeDTO> getAllData(){

        return collegeService.getAllStudentData();
    }

    // http://localhost:8080/api/College/1/UpdateData

    @PutMapping("/{clg_id}/UpdateData")
    public ResponseEntity<CollegeDTO> updateData(@PathVariable("clg_id") int id,@RequestBody  CollegeDTO collegeDTO){
        CollegeDTO DTO = collegeService.updateCollegeData(id, collegeDTO);
        return new ResponseEntity<>(DTO,HttpStatus.CREATED);
    }

    // http://localhost:8080/api/College/1/DeleteData

    @DeleteMapping("/{clg_id}/DeleteData")
    public ResponseEntity<String> deleteData(@PathVariable("clg_id") int id){
        collegeService.deleteCollegeData(id);
        return new ResponseEntity<>("Student Data Deleted successfully !!",HttpStatus.OK);
    }
}
