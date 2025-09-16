package com.example.chessmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.example.chessmarket.storage.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class ChessMarketApplication {
  public static void main(String[] args) {
    SpringApplication.run(ChessMarketApplication.class, args);
  }
}
