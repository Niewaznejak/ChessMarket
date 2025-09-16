package com.example.chessmarket.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepo;
  private final RoleRepository roleRepo;
  private final BCryptPasswordEncoder encoder;

  public UserService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder encoder) {
    this.userRepo = userRepo; this.roleRepo = roleRepo; this.encoder = encoder;
  }

  public User register(String username, String email, String rawPassword) {
    if (userRepo.existsByUsername(username)) throw new RuntimeException("Użytkownik istnieje");
    User u = new User();
    u.setUsername(username);
    u.setEmail(email);
    u.setPassword(encoder.encode(rawPassword));
    Role role = roleRepo.findByName("ROLE_USER").orElseGet(() -> {
      Role r = new Role(); r.setName("ROLE_USER"); return roleRepo.save(r);
    });
    u.getRoles().add(role);
    return userRepo.save(u);
  }
}
