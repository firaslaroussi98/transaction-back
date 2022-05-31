package com.bns.app.drools.repository;

import com.bns.app.drools.rules.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RulesRepository extends JpaRepository<Rules, Long> {

}
