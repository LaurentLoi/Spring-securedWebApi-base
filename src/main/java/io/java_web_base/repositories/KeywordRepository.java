package io.java_web_base.repositories;

import io.java_web_base.models.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface KeywordRepository extends JpaRepository<Keyword, Long>, QuerydslPredicateExecutor<Keyword> {
}
