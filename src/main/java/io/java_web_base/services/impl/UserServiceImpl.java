package io.java_web_base.services.impl;

import io.java_web_base.config.PasswordEncoderConfig;
import io.java_web_base.mappers.WebApiMapper;
import io.java_web_base.models.user.User;
import io.java_web_base.models.user.UserDTO;
import io.java_web_base.models.user.UserForm;
import io.java_web_base.repositories.UserRepository;
import io.java_web_base.services.BaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, BaseService<UserDTO, UserForm, Long> {

    private final UserRepository userRepository;
    private final WebApiMapper webApiMapper;
    private final PasswordEncoderConfig passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, WebApiMapper webApiMapper, PasswordEncoderConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.webApiMapper = webApiMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name : " + username + " does not exist !"));
    }

    @Override
    public List<UserDTO> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(this.webApiMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getOne(Long id) {
        return this.webApiMapper.toDTO(this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id : " + id + " does not exist !")));
    }

    @Override
    public boolean insert(UserForm form) {
        return this.userRepository.save(createUserToInsert(form)).getId() > 0;
    }

    @Override
    public UserDTO insertWithReturnValue(UserForm form) {
        return this.webApiMapper.toDTO(this.userRepository.save(createUserToInsert(form)));
    }

    @Override
    public boolean delete(Long id) {
        User userToDelete = this.webApiMapper.toEntity(getOne(id));

        this.userRepository.delete(userToDelete);

        return !this.userRepository.existsById(userToDelete.getId());
    }

    @Override
    public UserDTO update(UserForm form, Long id) {

        // todo check data !!
        User userToUpdate = this.webApiMapper.toEntity(getOne(id));

        userToUpdate.setUsername(form.getUsername());
        userToUpdate.setPassword(this.passwordEncoder.getPasswordEncoder().encode(form.getPassword()));
        userToUpdate.setRoles(form.getRoles());

        return this.webApiMapper.toDTO(this.userRepository.save(userToUpdate));
    }

    public UserDTO updatePatch(Map<String, Object> updates, Long id) throws IllegalAccessException {

        User userToUpdate = this.webApiMapper.toEntity(getOne(id));

        Class<?> clazz = userToUpdate.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            Field field = Arrays.stream(fields)
                    .filter(f -> f.getName().equals(entry.getKey()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Champ de la classe non trouvé"));

            field.setAccessible(true);
            field.set(userToUpdate, entry.getValue());
        }

        User userUpdated = this.userRepository.save(userToUpdate);

        return this.webApiMapper.toDTO(userUpdated);
    }

    private User createUserToInsert(UserForm form) {
        User userToInsert = this.webApiMapper.fromFormToEntity(form);

        userToInsert.setAccountNonExpired(true);
        userToInsert.setAccountNonLocked(true);
        userToInsert.setCredentialsNonExpired(true);
        userToInsert.setEnabled(true);
        userToInsert.setRoles(form.getRoles());
        userToInsert.setPassword(this.passwordEncoder.getPasswordEncoder().encode(form.getPassword()));

        return userToInsert;
    }

}
