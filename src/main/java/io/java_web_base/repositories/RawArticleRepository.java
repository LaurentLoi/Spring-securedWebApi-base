package io.java_web_base.repositories;

import io.java_web_base.models.RawArticle.RawArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RawArticleRepository extends JpaRepository<RawArticle, Long>, QuerydslPredicateExecutor<RawArticle> {
}
