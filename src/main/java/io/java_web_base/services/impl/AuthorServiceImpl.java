package io.java_web_base.services.impl;

import com.querydsl.core.BooleanBuilder;
import io.java_web_base.mappers.WebApiMapper;
import io.java_web_base.models.author.Author;
import io.java_web_base.models.author.AuthorDTO;
import io.java_web_base.models.author.AuthorForm;
import io.info_r_me.models.author.QAuthor;
import io.java_web_base.repositories.AuthorRepository;
import io.java_web_base.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final WebApiMapper webApiMapper;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(WebApiMapper webApiMapper, AuthorRepository authorRepository) {
        this.webApiMapper = webApiMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> getAll() {
        return this.authorRepository.findAll()
                .stream()
                .map(this.webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getOne(long id) {
        return this.webApiMapper.entityToDTO(this.authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Keyword not found !")));
    }

    @Override
    public AuthorDTO insertWithReturnValue(AuthorForm authorForm) {
        return this.webApiMapper.entityToDTO(this.authorRepository.save(this.webApiMapper.fromFormToEntity(authorForm)));
    }

    @Override
    public void insert(AuthorForm authorForm) {
        this.authorRepository.save(this.webApiMapper.fromFormToEntity(authorForm));
    }

    @Override
    public String delete(long id) {
        Author authorToDelete = this.webApiMapper.DtoToEntity(getOne(id));
        this.authorRepository.delete(authorToDelete);
        return "Deleted author: " + authorToDelete.getId() + " - " + authorToDelete.getName();
    }

    @Override
    public AuthorDTO update(long id, AuthorForm authorForm) {
        Author authorToUpdate = this.webApiMapper.DtoToEntity(getOne(id));

        if (authorForm.getName() != null) {
            authorToUpdate.setName(authorForm.getName());
        }
//
//        if (authorForm.getContent() != null) {
//            authorToUpdate.setContent(authorForm.getContent());
//        }

        this.authorRepository.save(authorToUpdate);

        return this.webApiMapper.entityToDTO(authorToUpdate);
    }

    @Override
    public List<AuthorDTO> search(AuthorForm authorForm) {
        BooleanBuilder predicate = new BooleanBuilder();

        QAuthor qAuthor = QAuthor.author;

        if (authorForm.getName() != null) {
            predicate.and(qAuthor.name.eq(authorForm.getName()));
        }

//        if (authorForm.getContent() != null) {
//            predicate.and(qAuthor.content.eq(authorForm.getContent()));
//        }

        return StreamSupport.stream(this.authorRepository.findAll(predicate).spliterator(), false)
                .map(webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}
