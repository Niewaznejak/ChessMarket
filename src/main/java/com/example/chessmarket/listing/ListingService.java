package com.example.chessmarket.listing;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListingService {
  private final ListingRepository repo;
  public ListingService(ListingRepository repo) { this.repo = repo; }
  public List<Listing> findAll() { return repo.findAll(); }
  public Listing get(Long id) { return repo.findById(id).orElseThrow(); }
  public Listing save(Listing l) { return repo.save(l); }
}
