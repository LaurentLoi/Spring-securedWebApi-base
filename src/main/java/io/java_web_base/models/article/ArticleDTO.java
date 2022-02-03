package io.java_web_base.models.article;

import io.java_web_base.models.author.Author;
import io.java_web_base.models.image.Image;
import io.java_web_base.models.keyword.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {

    long id;

    String title;
    List<Author> authors;
    String date_published;
    String content;
    List<Keyword> keywords;
    List<Image> images;

}
