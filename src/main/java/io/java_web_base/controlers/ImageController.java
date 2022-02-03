package io.java_web_base.controlers;

import io.java_web_base.models.image.ImageDTO;
import io.java_web_base.models.image.ImageForm;
import io.java_web_base.services.impl.ImageServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/image")
public class ImageController {

    private final ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ImageDTO>> getAll() {
        return ResponseEntity.ok(this.imageService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ImageDTO> getOne(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(this.imageService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> insert(@Valid @RequestBody ImageForm imageForm) throws Exception {
        return ResponseEntity.ok(this.imageService.insert(imageForm));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(this.imageService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ImageDTO> update(@Valid @PathVariable(name = "id") long id, @RequestBody ImageForm imageForm) {
        return ResponseEntity.ok(this.imageService.update(imageForm, id));
    }
}
