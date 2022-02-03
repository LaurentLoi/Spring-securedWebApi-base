package io.java_web_base.models.image;

import io.java_web_base.models.article.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {

    long id;

    Article article;
    String legend;
    String caption;
    String miniature;
    String big;
    String copyright;
    String author;
    String credit;
    String createdAt;

}
