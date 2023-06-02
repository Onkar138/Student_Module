package com.tnsif.Repository;

import com.tnsif.Entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Integer> {
}
