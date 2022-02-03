package io.java_web_base.models.image;

import io.java_web_base.models.article.Article;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "image")
@Table(name = "image")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    Article article;

    @Column(name = "legend")
    String legend;

    @Column(name = "caption")
    String caption;

    @Column(name = "miniature")
    String miniature;

    @Column(name = "big")
    String big;

    @Column(name = "copyright")
    String copyright;

    @Column(name = "author")
    String author;

    @Column(name = "credit")
    String credit;

    @Column(name = "created_at")
    String createdAt;

}
