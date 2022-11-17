package com.introduce.seoulgil.repository;

import com.introduce.seoulgil.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositroy extends JpaRepository<Member, Long> {
}
