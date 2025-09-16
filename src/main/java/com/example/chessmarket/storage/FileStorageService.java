package com.example.chessmarket.storage;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {
  private final Path root;
  public FileStorageService(FileStorageProperties props) {
    this.root = Paths.get(props.getUploadDir()).toAbsolutePath().normalize();
    try { Files.createDirectories(root); } catch (IOException ignored) {}
  }
  public String store(MultipartFile file) throws IOException {
    String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
    String name = UUID.randomUUID().toString() + (ext != null ? "." + ext : "");
    Files.copy(file.getInputStream(), root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
    return name;
  }
}
