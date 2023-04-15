package com.toy.study.domain;

import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member, Long> {
}
