package io.java_web_base.services.impl;

import io.java_web_base.mappers.WebApiMapper;

import io.java_web_base.models.image.Image;
import io.java_web_base.models.image.ImageDTO;
import io.java_web_base.models.image.ImageForm;
import io.java_web_base.repositories.ImageRepository;
import io.java_web_base.services.ArticleService;
import io.java_web_base.services.BaseService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements BaseService<ImageDTO, ImageForm, Long> {

    private final ImageRepository imageRepository;
    private final WebApiMapper webApiMapper;
    private final ArticleService articleService;

    public ImageServiceImpl(ImageRepository imageRepository, WebApiMapper webApiMapper, ArticleService articleService) {
        this.imageRepository = imageRepository;
        this.webApiMapper = webApiMapper;
        this.articleService = articleService;
    }

    @Override
    public List<ImageDTO> getAll() {
        return this.imageRepository.findAll()
                .stream()
                .map(this.webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDTO getOne(Long id) {
        return this.webApiMapper.entityToDTO(this.imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image with id : " + id + " does not exist !")));
    }

    @Override
    public boolean insert(ImageForm form) {

        Image imageToInsert = this.webApiMapper.fromFormToEntity(form);

        imageToInsert.setArticle(this.webApiMapper.DtoToEntity(this.articleService.getOne(form.getArticle_id())));
        imageToInsert.setLegend(form.getLegend());
        imageToInsert.setCaption(form.getCaption());
        imageToInsert.setMiniature(form.getMiniature());
        imageToInsert.setBig(form.getBig());
        imageToInsert.setCopyright(form.getCopyright());
        imageToInsert.setAuthor(form.getAuthor());
        imageToInsert.setCredit(form.getCredit());
        imageToInsert.setCreatedAt(form.getCreatedAt());

        Image imageInserted = this.imageRepository.save(imageToInsert);

        return imageInserted.getId() > 0;
    }

    @Override
    public ImageDTO insertWithReturnValue(ImageForm form) {
        Image imageToInsert = this.webApiMapper.fromFormToEntity(form);

        imageToInsert.setArticle(this.webApiMapper.DtoToEntity(this.articleService.getOne(form.getArticle_id())));
//        imageToInsert.setLegend(form.getLegend());
//        imageToInsert.setCaption(form.getCaption());
//        imageToInsert.setMiniature(form.getMiniature());
//        imageToInsert.setBig(form.getBig());
//        imageToInsert.setCopyright(form.getCopyright());
//        imageToInsert.setAuthor(form.getAuthor());
//        imageToInsert.setCredit(form.getCredit());
//        imageToInsert.setCreatedAt(form.getCreatedAt());

        ImageDTO imageInserted = this.webApiMapper.entityToDTO(this.imageRepository.save(imageToInsert));
        System.out.println("imageInserted: " + imageInserted + "\n\n");

        return imageInserted;
    }

    @Override
    public boolean delete(Long id) {
        Image imageToDelete = this.webApiMapper.DtoToEntity(getOne(id));

        this.imageRepository.delete(imageToDelete);

        return !this.imageRepository.existsById(imageToDelete.getId());
    }

    @Override
    public ImageDTO update(ImageForm form, Long id) {
        Image imageToUpdate = this.webApiMapper.DtoToEntity(getOne(id));

//        imageToUpdate.setImagename(form.getImagename());
//        imageToUpdate.setPassword(this.passwordEncoder.getPasswordEncoder().encode(form.getPassword()));
//        imageToUpdate.setRoles(form.getRoles());

        return this.webApiMapper.entityToDTO(this.imageRepository.save(imageToUpdate));
    }

    public ImageDTO updatePatch(Map<String, Object> updates, Long id) throws IllegalAccessException {

        Image imageToUpdate = this.webApiMapper.DtoToEntity(getOne(id));

        Class<?> clazz = imageToUpdate.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            Field field = Arrays.stream(fields)
                    .filter(f -> f.getName().equals(entry.getKey()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Champ de la classe non trouvé"));

            field.setAccessible(true);
            field.set(imageToUpdate, entry.getValue());
        }

        Image imageUpdated = this.imageRepository.save(imageToUpdate);

        return this.webApiMapper.entityToDTO(imageUpdated);
    }

}
