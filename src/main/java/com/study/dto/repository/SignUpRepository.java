package com.study.dto.repository;

import com.study.dto.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<Member, Object> {
    Member findByEmailId(String emailId);
}
