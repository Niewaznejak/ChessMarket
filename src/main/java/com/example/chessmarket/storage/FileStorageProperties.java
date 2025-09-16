package com.example.chessmarket.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
public class FileStorageProperties {
  private String uploadDir = "uploads";
  public String getUploadDir() { return uploadDir; }
  public void setUploadDir(String uploadDir) { this.uploadDir = uploadDir; }
}
