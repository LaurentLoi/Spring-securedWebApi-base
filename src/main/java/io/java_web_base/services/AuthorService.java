package io.java_web_base.services;

import io.java_web_base.models.author.AuthorDTO;
import io.java_web_base.models.author.AuthorForm;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> getAll();

    AuthorDTO getOne(long id);

    AuthorDTO insertWithReturnValue(AuthorForm authorForm) throws Exception;

    void insert(AuthorForm authorForm) throws Exception;

    String delete(long id);

    AuthorDTO update(long id, AuthorForm authorForm);

    List<AuthorDTO> search(AuthorForm authorForm);

}
