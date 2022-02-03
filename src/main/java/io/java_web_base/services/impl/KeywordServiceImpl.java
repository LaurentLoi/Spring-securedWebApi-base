package io.java_web_base.services.impl;

import com.querydsl.core.BooleanBuilder;
import io.java_web_base.mappers.WebApiMapper;
import io.java_web_base.models.keyword.Keyword;
import io.java_web_base.models.keyword.KeywordDTO;
import io.java_web_base.models.keyword.KeywordForm;
import io.info_r_me.models.keyword.QKeyword;
import io.java_web_base.repositories.KeywordRepository;
import io.java_web_base.services.KeywordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KeywordServiceImpl implements KeywordService {

    private final WebApiMapper webApiMapper;
    private final KeywordRepository keywordRepository;

    public KeywordServiceImpl(WebApiMapper webApiMapper, KeywordRepository keywordRepository) {
        this.webApiMapper = webApiMapper;
        this.keywordRepository = keywordRepository;
    }

    @Override
    public List<KeywordDTO> getAll() {
        return this.keywordRepository.findAll()
                .stream()
                .map(this.webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KeywordDTO getOne(long id) {
        return this.webApiMapper.entityToDTO(this.keywordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Keyword not found !")));
    }

    @Override
    public KeywordDTO insertWithReturnValue(KeywordForm keywordForm) {
        return this.webApiMapper.entityToDTO(this.keywordRepository.save(this.webApiMapper.fromFormToEntity(keywordForm)));
    }

    @Override
    public void insert(KeywordForm keywordForm) {
        this.keywordRepository.save(this.webApiMapper.fromFormToEntity(keywordForm));
    }

    @Override
    public String delete(long id) {
        Keyword keywordToDelete = this.webApiMapper.DtoToEntity(getOne(id));
        this.keywordRepository.delete(keywordToDelete);
        return "Deleted keyword: " + keywordToDelete.getId() + " - " + keywordToDelete.getName();
    }

    @Override
    public KeywordDTO update(long id, KeywordForm keywordForm) {
        Keyword keywordToUpdate = this.webApiMapper.DtoToEntity(getOne(id));

        if (keywordForm.getName() != null) {
            keywordToUpdate.setName(keywordForm.getName());
        }
//
//        if (keywordForm.getContent() != null) {
//            keywordToUpdate.setContent(keywordForm.getContent());
//        }

        this.keywordRepository.save(keywordToUpdate);

        return this.webApiMapper.entityToDTO(keywordToUpdate);
    }

    @Override
    public List<KeywordDTO> search(KeywordForm keywordForm) {
        BooleanBuilder predicate = new BooleanBuilder();

        QKeyword qKeyword = QKeyword.keyword;

        if (keywordForm.getName() != null) {
            predicate.and(qKeyword.name.eq(keywordForm.getName()));
        }

//        if (keywordForm.getContent() != null) {
//            predicate.and(qKeyword.content.eq(keywordForm.getContent()));
//        }

        return StreamSupport.stream(this.keywordRepository.findAll(predicate).spliterator(), false)
                .map(webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}
