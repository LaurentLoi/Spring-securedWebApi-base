package io.java_web_base.controlers;

import io.java_web_base.models.article.ArticleDTO;
import io.java_web_base.models.article.ArticleForm;
import io.java_web_base.services.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ArticleDTO>> getAll() {
        return ResponseEntity.ok(this.articleService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ArticleDTO> getOne(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(this.articleService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> insert(@Valid @RequestBody ArticleForm articleForm) throws Exception {
        return ResponseEntity.ok(this.articleService.insertWithReturnValue(articleForm));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(this.articleService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ArticleDTO> update(@Valid @PathVariable(name = "id") long id, @RequestBody ArticleForm articleForm) {
        return ResponseEntity.ok(this.articleService.update(id, articleForm));
    }
}
