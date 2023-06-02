package com.tnsif.Service;

import com.tnsif.Payload.CollegeDTO;

import java.util.List;

public interface CollegeService {
    public CollegeDTO createCollegeData(CollegeDTO collegeDTO);

    public List<CollegeDTO> getAllStudentData();

    public CollegeDTO updateCollegeData(int id, CollegeDTO collegeDTO);

    public void deleteCollegeData(int id);
}
