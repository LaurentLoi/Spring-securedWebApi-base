package io.java_web_base.repositories;

import io.java_web_base.models.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthorRepository extends JpaRepository<Author, Long>, QuerydslPredicateExecutor<Author> {
}
