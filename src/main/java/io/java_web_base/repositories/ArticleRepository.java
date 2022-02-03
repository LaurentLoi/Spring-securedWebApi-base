package io.java_web_base.repositories;

import io.java_web_base.models.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article> {
}
