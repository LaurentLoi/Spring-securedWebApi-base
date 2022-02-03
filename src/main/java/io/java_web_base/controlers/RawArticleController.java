package io.java_web_base.controlers;

import io.java_web_base.models.RawArticle.RawArticleDTO;
import io.java_web_base.models.RawArticle.RawArticleForm;
import io.java_web_base.services.RawArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/raw_article")
public class RawArticleController {

    private final RawArticleService rawArticleService;

    public RawArticleController(RawArticleService rawArticleService) {
        this.rawArticleService = rawArticleService;
    }

    @GetMapping
    public ResponseEntity<List<RawArticleDTO>> getAll() {
        return ResponseEntity.ok(this.rawArticleService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RawArticleDTO> getOne(@PathVariable(name = "id") long id) throws Exception {
        return ResponseEntity.ok(this.rawArticleService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<RawArticleDTO> insert(@Valid @RequestBody RawArticleForm rawArticleForm) throws Exception {
        return ResponseEntity.ok(this.rawArticleService.insertWithReturnValue(rawArticleForm));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) throws Exception {
        return ResponseEntity.ok(this.rawArticleService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RawArticleDTO> update(@Valid @PathVariable(name = "id") long id, @RequestBody RawArticleForm rawArticleForm) throws Exception {
        return ResponseEntity.ok(this.rawArticleService.update(id, rawArticleForm));
    }
}
