package com.sochoeun.service.impl;

import com.sochoeun.constants.Constants;
import com.sochoeun.entity.ImportImage;
import com.sochoeun.repository.ImportImageRepository;
import com.sochoeun.service.ImportImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RequiredArgsConstructor
@Service
public class ImportImageServiceImpl implements ImportImageService {
    private final ImportImageRepository importImageRepository;
    @Override
    public void create(ImportImage req) {
        importImageRepository.save(req);
    }

    @Override
    public List<ImportImage> getAllImportImage() {
        return importImageRepository.findAll();
    }

    @Override
    public ImportImage getById(String id) {
        return importImageRepository.findById(id).orElseThrow();
    }

    @Override
    public String uploadPhoto(String id, MultipartFile file) {
        ImportImage image = getById(id);
        String photoUrl = photoFunction.apply(id,file);
        image.setImageUrl(photoUrl);
        importImageRepository.save(image);
        return photoUrl;
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(Constants.PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/content/image/" + filename).toUriString();
        }catch (Exception exception) {
            throw new RuntimeException("Unable to save image");
        }
    };
}
