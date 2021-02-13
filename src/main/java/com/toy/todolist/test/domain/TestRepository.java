package com.toy.todolist.test.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestDomain, Long> {
}
