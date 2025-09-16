package com.example.chessmarket.user;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository repo;
  public CustomUserDetailsService(UserRepository repo) { this.repo = repo; }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User u = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono"));
    return new org.springframework.security.core.userdetails.User(
      u.getUsername(), u.getPassword(), u.isEnabled(), true, true, true,
      u.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toSet())
    );
  }
}
