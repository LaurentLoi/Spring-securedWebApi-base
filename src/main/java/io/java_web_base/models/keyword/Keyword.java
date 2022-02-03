package io.java_web_base.models.keyword;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.java_web_base.models.article.Article;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity(name = "keyword")
@Table(name = "keyword")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    String name;

    @ManyToMany(mappedBy = "keywords")
    @ToString.Exclude
    @JsonBackReference
    List<Article> articles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Keyword keyword = (Keyword) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
