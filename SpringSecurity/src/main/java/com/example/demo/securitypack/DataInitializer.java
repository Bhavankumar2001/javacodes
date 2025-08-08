//package com.example.demo.securitypack;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.PostConstruct;
//
//@Component
//public class DataInitializer {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    public void initUser() {
//        if (employeeRepository.findByUseremail("admin@example.com").isEmpty()) {
//            EmployeeEntity user = new EmployeeEntity();
//            user.setUseremail("admin@example.com");
//            user.setPassword(passwordEncoder.encode("admin123"));
//            user.setRole("ROLE_ADMIN"); // should match security config
//            employeeRepository.save(user);
//
//            System.out.println("✅ Default admin user created.");
//        } else {
//            System.out.println("ℹ️ Default admin user already exists.");
//        }
//    }
//}