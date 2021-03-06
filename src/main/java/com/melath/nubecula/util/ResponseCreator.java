package com.melath.nubecula.util;

import com.melath.nubecula.storage.model.entity.NubeculaFile;
import com.melath.nubecula.storage.model.reponse.ResponseFile;
import com.melath.nubecula.util.NubeculaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResponseCreator {

    @Value("${base.url}")
    private String baseUrl;


    @Transactional
    public List<ResponseFile> create(Stream<NubeculaFile> filesInDirectory) {
        return filesInDirectory.map(file -> {
            ResponseFile responseFile;
            if (file.isDirectory()) {
                responseFile = createDir(file);
            } else {
                responseFile = createFile(file);
            }
            return responseFile;
        }).collect(Collectors.toList());
    }


    @Transactional
    public List<ResponseFile> createDirs(Stream<NubeculaFile> dirsInDirectory) {
        return dirsInDirectory.map(this::createDir).collect((Collectors.toList()));
    }


    public ResponseFile createDir(NubeculaFile directory) {
        return ResponseFile.builder()
                .id(directory.getId())
                .filename(directory.getFilename())
                .type(directory.getType())
                .size(NubeculaUtils.getSizeString(directory.getSize()))
                .createDate(NubeculaUtils.getDateString(directory.getCreateDate()))
                .modificationDate(NubeculaUtils.getDateString(directory.getModificationDate()))
                .isDirectory(true)
                .parentDirectoryId(
                        !directory.getParentDirectory().getType().equals("root directory")
                                ? directory.getParentDirectory().getId()
                                : null
                )
                .shared(directory.isShared())
                .url(baseUrl + "/" + directory.getId())
                .build();
    }


    public ResponseFile createFile(NubeculaFile file) {
        return ResponseFile.builder()
                .id(file.getId())
                .filename(file.getFilename())
                .type(file.getType())
                .extension(file.getExtension())
                .size(NubeculaUtils.getSizeString(file.getSize()))
                .createDate(NubeculaUtils.getDateString(file.getCreateDate()))
                .modificationDate(NubeculaUtils.getDateString(file.getModificationDate()))
                .isDirectory(false)
                .parentDirectoryId(file.getParentDirectory().getId())
                .shared(file.isShared())
                .url(baseUrl + "/files/" + file.getId())
                .build();
    }

}
