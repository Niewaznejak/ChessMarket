package com.example.chessmarket.auth;

import com.example.chessmarket.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
  private final UserService userService;
  public AuthController(UserService userService) { this.userService = userService; }

  @GetMapping("/login")
  public String login() { return "login"; }

  @GetMapping("/register")
  public String registerForm() { return "register"; }

  @PostMapping("/register")
  public String register(@RequestParam("username") String username,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         RedirectAttributes ra) {
    try {
      userService.register(username, email, password);
      ra.addFlashAttribute("msg", "Konto utworzone. Zaloguj się.");
      return "redirect:/login";
    } catch (Exception ex) {
      ra.addFlashAttribute("error", ex.getMessage() == null ? "Błąd rejestracji" : ex.getMessage());
      return "redirect:/register";
    }
  }
}
