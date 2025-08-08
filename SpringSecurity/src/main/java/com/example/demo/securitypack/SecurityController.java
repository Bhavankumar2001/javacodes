//package com.example.demo.securitypack;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class SecurityController {
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @GetMapping("/default")
//    public String redirectAfterLogin(Authentication authentication) {
//        String role = authentication.getAuthorities().toString();
//        if (role.contains("ADMIN")) {
//            return "redirect:/admin/home";
//        } else if (role.contains("USER")) {
//            return "redirect:/user/home";
//        } else {
//            return "redirect:/login?error";
//        }
//    }
//
//    @GetMapping("/forgot-password")
//    public String forgotPasswordPage() {
//        return "forgot-password"; // Create forgot-password.html later if needed
//    }
//
//    @GetMapping("/admin/home")
//    public String adminHome() {
//        return "home";
//    }
//
//    @GetMapping("/user/home")
//    public String userHome() {
//        return "home";
//    }
//}
