package io.java_web_base.services;

import io.java_web_base.models.article.ArticleDTO;
import io.java_web_base.models.article.ArticleForm;

import java.util.List;

public interface ArticleService {

    List<ArticleDTO> getAll();

    ArticleDTO getOne(long id);

    ArticleDTO insertWithReturnValue(ArticleForm articleForm) throws Exception;

    void insert(ArticleForm articleForm) throws Exception;

    String delete(long id);

    ArticleDTO update(long id, ArticleForm articleForm);

    List<ArticleDTO> search(ArticleForm articleForm);

}
