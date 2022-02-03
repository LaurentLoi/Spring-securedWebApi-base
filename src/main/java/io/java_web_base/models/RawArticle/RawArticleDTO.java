package io.java_web_base.models.RawArticle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RawArticleDTO {

    long id;

    String url;

    String content;

}
