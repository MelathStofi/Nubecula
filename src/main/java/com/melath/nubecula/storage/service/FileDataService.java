package com.melath.nubecula.storage.service;

import com.melath.nubecula.storage.model.NubeculaFile;
import com.melath.nubecula.storage.model.exceptions.NotNubeculaDirectoryException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

public interface FileDataService {
    UUID store(UUID parentDirId, MultipartFile file, String username);

    Set<NubeculaFile> loadAll(UUID id) throws NotNubeculaDirectoryException;

    NubeculaFile load(UUID id);

    NubeculaFile load(String username);

    void createDirectory(UUID parentDirId, String dirname, String username);

    void createDirectory(String username);

    void rename(UUID id, String newName);

    void delete(UUID id);

}
