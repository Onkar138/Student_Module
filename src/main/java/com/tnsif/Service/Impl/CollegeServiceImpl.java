package com.tnsif.Service.Impl;

import com.tnsif.Entity.College;
import com.tnsif.Exception.ResourceNotFoundException;
import com.tnsif.Payload.CollegeDTO;
import com.tnsif.Repository.CollegeRepository;
import com.tnsif.Service.CollegeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepo;
    private ModelMapper mapper;

    public CollegeServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CollegeDTO createCollegeData(CollegeDTO collegeDTO) {
        College newcollege = mapToEntity(collegeDTO);
        College college = collegeRepo.save(newcollege);
        CollegeDTO collegeDTO1 = mapToDTO(college);
        return collegeDTO1;
    }

    @Override
    public List<CollegeDTO> getAllStudentData() {
        List<College> collegeList = collegeRepo.findAll();
        return collegeList.stream().map(college -> mapToDTO(college)).collect(Collectors.toList());
    }

    @Override
    public CollegeDTO updateCollegeData(int id, CollegeDTO collegeDTO) {
        College college = collegeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("College", "Id", id));

        college.setName(collegeDTO.getName());
        college.setLocation(collegeDTO.getLocation());
        College newcollege = collegeRepo.save(college);
        return mapToDTO(newcollege);
    }

    @Override
    public void deleteCollegeData(int id) {
        College college = collegeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("College", "Id", id));
        collegeRepo.deleteById(id);
    }

    private College mapToEntity(CollegeDTO collegeDTO) {
        College college = mapper.map(collegeDTO, College.class);
        return college;
    }

    private CollegeDTO mapToDTO(College college) {
        CollegeDTO collegeDTO = mapper.map(college, CollegeDTO.class);
        return collegeDTO;
    }

}
