package io.java_web_base.models.keyword;

import io.java_web_base.models.article.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeywordDTO {

    long id;

    String name;

    List<Article> articles;

}
