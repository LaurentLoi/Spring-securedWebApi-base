package io.java_web_base.controlers;

import io.java_web_base.models.user.UserDTO;
import io.java_web_base.models.user.UserForm;
import io.java_web_base.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody UserForm userForm) {
        boolean inserted = this.userService.insert(userForm);

        return inserted ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        boolean deleted = this.userService.delete(id);

        return deleted ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserForm userForm, @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.update(userForm, id));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDTO> patch(@RequestBody Map<String, Object> updates, @PathVariable("id") Long id) throws IllegalAccessException {
        return ResponseEntity.ok(this.userService.updatePatch(updates, id));
    }

}
