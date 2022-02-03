package io.java_web_base.services;

import io.java_web_base.models.RawArticle.RawArticleDTO;
import io.java_web_base.models.RawArticle.RawArticleForm;

import java.util.List;

public interface RawArticleService {

    List<RawArticleDTO> getAll();

    RawArticleDTO getOne(long id) throws Exception;

    RawArticleDTO insertWithReturnValue(RawArticleForm rawArticleForm) throws Exception;

    void insert(RawArticleForm rawArticleForm) throws Exception;

    String delete(long id) throws Exception;

    RawArticleDTO update(long id, RawArticleForm rawArticleForm) throws Exception;

    List<RawArticleDTO> search(RawArticleForm rawArticleForm);

}
