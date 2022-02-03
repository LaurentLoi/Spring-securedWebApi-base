package io.java_web_base.models.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {

    Long id;

    String username;

    String email;

//    todo add relation to articles
//    List<Article> articles;

    String password;

    String salt;

    List<String> roles = new ArrayList<>();

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;
}
