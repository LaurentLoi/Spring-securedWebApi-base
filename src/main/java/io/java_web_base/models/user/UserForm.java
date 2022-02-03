package io.java_web_base.models.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    String username;

    String email;

    String password;

    List<String> roles = new ArrayList<>();

}
