package io.java_web_base.mappers;

import io.java_web_base.models.RawArticle.RawArticle;
import io.java_web_base.models.RawArticle.RawArticleDTO;
import io.java_web_base.models.RawArticle.RawArticleForm;
import io.java_web_base.models.article.Article;
import io.java_web_base.models.article.ArticleDTO;
import io.java_web_base.models.article.ArticleForm;
import io.java_web_base.models.author.Author;
import io.java_web_base.models.author.AuthorDTO;
import io.java_web_base.models.author.AuthorForm;
import io.java_web_base.models.image.Image;
import io.java_web_base.models.image.ImageDTO;
import io.java_web_base.models.image.ImageForm;
import io.java_web_base.models.keyword.Keyword;
import io.java_web_base.models.keyword.KeywordDTO;
import io.java_web_base.models.keyword.KeywordForm;
import io.java_web_base.models.user.User;
import io.java_web_base.models.user.UserDTO;
import io.java_web_base.models.user.UserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface WebApiMapper {

    // USERS
    @Mappings({})
    UserDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "authorities", ignore = true)
    })
    User toEntity(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "accountNonExpired", ignore = true),
            @Mapping(target = "accountNonLocked", ignore = true),
            @Mapping(target = "credentialsNonExpired", ignore = true),
            @Mapping(target = "enabled", ignore = true),
            @Mapping(target = "salt", ignore = true)
    })
    User fromFormToEntity(UserForm userForm);

    // RAW ARTICLES
    @Mappings({
            @Mapping(target = "id")
    })
    RawArticle DtoToEntity(RawArticleDTO rawArticleDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    RawArticle fromFormToEntity(RawArticleForm rawArticleForm);

    @Mappings({
            @Mapping(target = "id")
    })
    RawArticleDTO entityToDTO(RawArticle rawArticleEntity);

    // ARTICLES
    @Mappings({
            @Mapping(target = "id")
    })
    Article DtoToEntity(ArticleDTO articleDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Article fromFormToEntity(ArticleForm articleForm);

    @Mappings({
            @Mapping(target = "id"),
    })
    ArticleDTO entityToDTO(Article articleEntity);

    // AUTHOR
    @Mappings({
            @Mapping(target = "id")
    })
    Author DtoToEntity(AuthorDTO authorDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "articles", ignore = true)
    })
    Author fromFormToEntity(AuthorForm authorForm);

    @Mappings({
            @Mapping(target = "id")
    })
    AuthorDTO entityToDTO(Author authorEntity);

    // KEYWORD
    @Mappings({
            @Mapping(target = "id")
    })
    Keyword DtoToEntity(KeywordDTO keywordDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "articles", ignore = true)
    })
    Keyword fromFormToEntity(KeywordForm keywordForm);

    @Mappings({
            @Mapping(target = "id")
    })
    KeywordDTO entityToDTO(Keyword keywordEntity);

    // IMAGES
    @Mappings({
            @Mapping(target = "id")
    })
    Image DtoToEntity(ImageDTO imageDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "article", ignore = true)
    })
    Image fromFormToEntity(ImageForm imageForm);

    @Mappings({
            @Mapping(target = "id")
    })
    ImageDTO entityToDTO(Image imageEntity);

}
