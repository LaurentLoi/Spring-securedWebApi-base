package io.java_web_base.services;

import io.java_web_base.models.keyword.KeywordDTO;
import io.java_web_base.models.keyword.KeywordForm;

import java.util.List;

public interface KeywordService {

    List<KeywordDTO> getAll();

    KeywordDTO getOne(long id);

    KeywordDTO insertWithReturnValue(KeywordForm keywordForm) throws Exception;

    void insert(KeywordForm keywordForm) throws Exception;

    String delete(long id);

    KeywordDTO update(long id, KeywordForm keywordForm);

    List<KeywordDTO> search(KeywordForm keywordForm);

}
