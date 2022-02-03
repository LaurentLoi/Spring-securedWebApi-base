package io.java_web_base.models.RawArticle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity(name = "raw_article")
@Table(name = "raw_article")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RawArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "url")
    String url;

    @Column(name = "content", columnDefinition = "text")
    String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RawArticle rawArticle = (RawArticle) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
