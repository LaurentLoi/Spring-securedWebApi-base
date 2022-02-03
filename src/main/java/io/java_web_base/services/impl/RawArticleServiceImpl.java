package io.java_web_base.services.impl;

import com.querydsl.core.BooleanBuilder;
import io.java_web_base.mappers.WebApiMapper;
import io.info_r_me.models.RawArticle.QRawArticle;
import io.java_web_base.models.RawArticle.RawArticle;
import io.java_web_base.models.RawArticle.RawArticleDTO;
import io.java_web_base.models.RawArticle.RawArticleForm;
import io.java_web_base.repositories.RawArticleRepository;
import io.java_web_base.services.RawArticleService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RawArticleServiceImpl implements RawArticleService {

    private final WebApiMapper webApiMapper;
    private final RawArticleRepository rawArticleRepository;

    public RawArticleServiceImpl(WebApiMapper webApiMapper, RawArticleRepository rawArticleRepository) {
        this.webApiMapper = webApiMapper;
        this.rawArticleRepository = rawArticleRepository;
    }

    @Override
    public List<RawArticleDTO> getAll() {
        return this.rawArticleRepository.findAll()
                .stream()
                .map(this.webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RawArticleDTO getOne(long id) throws Exception {

        return this.webApiMapper.entityToDTO(this.rawArticleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Keyword not found !")));
    }

    @Override
    public RawArticleDTO insertWithReturnValue(RawArticleForm rawArticleForm) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(rawArticleForm.getUrl()))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        rawArticleForm.setContent(response);

        return this.webApiMapper.entityToDTO(this.rawArticleRepository.save(this.webApiMapper.fromFormToEntity(rawArticleForm)));
    }

    @Override
    public void insert(RawArticleForm rawArticleForm) {
        this.rawArticleRepository.save(this.webApiMapper.fromFormToEntity(rawArticleForm));
    }

    @Override
    public String delete(long id) throws Exception {
        RawArticle rawArticleToDelete = this.webApiMapper.DtoToEntity(getOne(id));
        this.rawArticleRepository.delete(rawArticleToDelete);
        return "Deleted article: " + rawArticleToDelete.getId() + " - " + rawArticleToDelete.getUrl();
    }

    @Override
    public RawArticleDTO update(long id, RawArticleForm rawArticleForm) throws Exception {
        RawArticle rawArticleToUpdate = this.webApiMapper.DtoToEntity(getOne(id));

        if (rawArticleForm.getUrl() != null) {
            rawArticleToUpdate.setUrl(rawArticleForm.getUrl());
        }

        if (rawArticleForm.getContent() != null) {
            rawArticleToUpdate.setContent(rawArticleForm.getContent());
        }

        this.rawArticleRepository.save(rawArticleToUpdate);

        return this.webApiMapper.entityToDTO(rawArticleToUpdate);
    }

    @Override
    public List<RawArticleDTO> search(RawArticleForm rawArticleForm) {
        BooleanBuilder predicate = new BooleanBuilder();

        QRawArticle qRawArticle = QRawArticle.rawArticle;

        if (rawArticleForm.getUrl() != null) {
            predicate.and(qRawArticle.url.eq(rawArticleForm.getUrl()));
        }

        if (rawArticleForm.getContent() != null) {
            predicate.and(qRawArticle.content.eq(rawArticleForm.getContent()));
        }

        return StreamSupport.stream(this.rawArticleRepository.findAll(predicate).spliterator(), false)
                .map(webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}
