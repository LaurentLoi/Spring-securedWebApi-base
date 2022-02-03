package io.java_web_base.models.article;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.java_web_base.models.author.Author;
import io.java_web_base.models.image.Image;
import io.java_web_base.models.keyword.Keyword;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "article")
@Table(name = "article")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "title")
    String title;

//    @ManyToOne todo
//    @JoinColumn(name = "user_id")
//    User user;

    @ManyToMany
    @JoinTable(
            name = "article_author",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonManagedReference
    @ToString.Exclude
    List<Author> authors;

    @Column(name = "date_published")
    String date_published;

    @Column(name = "content", columnDefinition = "text")
    String content;

    @ManyToMany
    @JoinTable(
            name = "article_keyword",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    @JsonManagedReference
    @ToString.Exclude
    List<Keyword> keywords;

    @OneToMany(mappedBy = "article")
    List<Image> images;
}
