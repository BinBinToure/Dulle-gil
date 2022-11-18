package com.introduce.seoulgil.repository;


import com.introduce.seoulgil.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);

    //이메일 중복 검사
    boolean existsByEmail(String email);
}
