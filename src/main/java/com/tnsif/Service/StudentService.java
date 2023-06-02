package com.tnsif.Service;

import com.tnsif.Payload.PageResponse;
import com.tnsif.Payload.StudentDTO;

import java.util.List;

public interface StudentService {

    public StudentDTO createStudentData(StudentDTO studentDTO);

    public List<StudentDTO> getAllStudentData();

    public PageResponse getAllStudentPaginationData(int pageNo, int pageSize, String sortBy, String sortDir);

    public StudentDTO getAllStudentDataById(long id);

    public StudentDTO updateStudentData(long id, StudentDTO studentDTO);

    public void deleteStudentData(long id);
}
