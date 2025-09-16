package com.example.chessmarket.listing;

import com.example.chessmarket.storage.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ListingController {
  private final ListingService service;
  private final FileStorageService storage;

  public ListingController(ListingService service, FileStorageService storage) {
    this.service = service; this.storage = storage;
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("listings", service.findAll());
    return "index";
  }

  @GetMapping("/listing/{id}")
  public String details(@PathVariable Long id, Model model) {
    model.addAttribute("listing", service.get(id));
    return "listing-detail";
  }

  @GetMapping("/listing/new")
  public String createForm(Model model) {
    model.addAttribute("listing", new Listing());
    return "listing-form";
  }

  @PostMapping("/listing/save")
  public String save(@Valid @ModelAttribute("listing") Listing listing,
                     BindingResult br,
                     @RequestParam("image") MultipartFile image) throws IOException {
    if (br.hasErrors()) return "listing-form";
    if (image != null && !image.isEmpty()) {
      String filename = storage.store(image);
      listing.setImageFilename(filename);
    }
    Listing saved = service.save(listing);
    return "redirect:/listing/" + saved.getId();
  }
}
