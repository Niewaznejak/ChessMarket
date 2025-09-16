package com.example.chessmarket.listing;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Listing {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String title;

  @NotBlank @Column(length = 4000)
  private String description;

  @NotBlank
  private String conditionLabel;

  @NotNull @DecimalMin("0.0")
  private BigDecimal price;

  @NotBlank
  private String phone;

  private String imageFilename;

  private LocalDateTime createdAt = LocalDateTime.now();

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public String getConditionLabel() { return conditionLabel; }
  public void setConditionLabel(String conditionLabel) { this.conditionLabel = conditionLabel; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public String getImageFilename() { return imageFilename; }
  public void setImageFilename(String imageFilename) { this.imageFilename = imageFilename; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
