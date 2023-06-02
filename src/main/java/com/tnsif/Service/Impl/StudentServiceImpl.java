package com.tnsif.Service.Impl;

import com.tnsif.Entity.Student;
import com.tnsif.Exception.ResourceNotFoundException;
import com.tnsif.Payload.PageResponse;
import com.tnsif.Payload.StudentDTO;
import com.tnsif.Repository.StudentRepository;
import com.tnsif.Service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;
    private ModelMapper mapper;

    public StudentServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public StudentDTO createStudentData(StudentDTO studentDTO) {
        Student student = mapToEntity(studentDTO);
        Student newstudent = studentRepo.save(student);
        StudentDTO studentDTO1 = mapToDTO(newstudent);
        return studentDTO1;
    }

    @Override
    public StudentDTO getAllStudentDataById(long id) {
        Student newstudent = studentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "Id", id));
        return mapToDTO(newstudent);
    }

    @Override
    public List<StudentDTO> getAllStudentData() {
        List<Student> students = studentRepo.findAll();
        return students.stream().map(student -> mapToDTO(student)).collect(Collectors.toList());
    }

    @Override
    public PageResponse getAllStudentPaginationData(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> page = studentRepo.findAll(pageable);
        List<Student> ListOf = page.getContent();
        List<StudentDTO> content = ListOf.stream().map(student -> mapToDTO(student)).collect(Collectors.toList());

        PageResponse pageResponse=new PageResponse();

        pageResponse.setContent(content);
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setTotalPage(page.getTotalPages());
        pageResponse.setLastPage(page.isLast());
        return pageResponse;
    }

    @Override
    public StudentDTO updateStudentData(long id, StudentDTO studentDTO) {
        Student newstudent = studentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "Id", id));
        newstudent.setS_name(studentDTO.getS_name());
        newstudent.setS_roll(studentDTO.getS_roll());
        newstudent.setS_qualification(studentDTO.getS_qualification());
        newstudent.setCourse(studentDTO.getCourse());
        newstudent.setS_year(studentDTO.getS_year());
        newstudent.setS_hallTicketNo(studentDTO.getS_hallTicketNo());

        Student save = studentRepo.save(newstudent);
        return mapToDTO(save);
    }

    @Override
    public void deleteStudentData(long id) {
        Student newstudent = studentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "Id", id));
        studentRepo.deleteById(id);
    }

    private Student mapToEntity(StudentDTO studentDTO) {
        Student student = mapper.map(studentDTO, Student.class);
        return student;
    }

    private StudentDTO mapToDTO(Student newstudent) {
        StudentDTO studentDTO = mapper.map(newstudent, StudentDTO.class);
        return studentDTO;
    }
}
