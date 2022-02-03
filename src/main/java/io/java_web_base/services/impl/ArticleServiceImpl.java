package io.java_web_base.services.impl;

import com.querydsl.core.BooleanBuilder;
import io.java_web_base.mappers.WebApiMapper;
import io.java_web_base.models.article.Article;
import io.java_web_base.models.article.ArticleDTO;
import io.java_web_base.models.article.ArticleForm;
import io.info_r_me.models.article.QArticle;
import io.java_web_base.repositories.ArticleRepository;
import io.java_web_base.services.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final WebApiMapper webApiMapper;
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(WebApiMapper webApiMapper, ArticleRepository articleRepository) {
        this.webApiMapper = webApiMapper;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleDTO> getAll() {
        return this.articleRepository.findAll()
                .stream()
                .map(this.webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getOne(long id) {
        return this.webApiMapper.entityToDTO(this.articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found !")));
    }

    @Override
    public ArticleDTO insertWithReturnValue(ArticleForm articleForm) {
        return this.webApiMapper.entityToDTO(this.articleRepository.save(this.webApiMapper.fromFormToEntity(articleForm)));
    }

    @Override
    public void insert(ArticleForm articleForm) {
        this.articleRepository.save(this.webApiMapper.fromFormToEntity(articleForm));
    }

    @Override
    public String delete(long id) {
        Article articleToDelete = this.webApiMapper.DtoToEntity(getOne(id));
        this.articleRepository.delete(articleToDelete);
        return "Deleted article: " + articleToDelete.getId() + " - " + articleToDelete.getTitle();
    }

    @Override
    public ArticleDTO update(long id, ArticleForm articleForm) {
        Article articleToUpdate = this.webApiMapper.DtoToEntity(getOne(id));

        if (articleForm.getTitle() != null) {
            articleToUpdate.setTitle(articleForm.getTitle());
        }

        if (articleForm.getContent() != null) {
            articleToUpdate.setContent(articleForm.getContent());
        }

        this.articleRepository.save(articleToUpdate);

        return this.webApiMapper.entityToDTO(articleToUpdate);
    }

    @Override
    public List<ArticleDTO> search(ArticleForm articleForm) {
        BooleanBuilder predicate = new BooleanBuilder();

        QArticle qArticle = QArticle.article;

        if (articleForm.getTitle() != null) {
            predicate.and(qArticle.title.eq(articleForm.getTitle()));
        }

        if (articleForm.getContent() != null) {
            predicate.and(qArticle.content.eq(articleForm.getContent()));
        }

        return StreamSupport.stream(this.articleRepository.findAll(predicate).spliterator(), false)
                .map(webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}
