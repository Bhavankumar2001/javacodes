//package com.example.demo.securitypack;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
////    @Override
//    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
//        EmployeeEntity user = employeeRepository.findByUseremail(useremail)
//            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return User.builder()
//                .username(user.getUseremail())
//                .password(user.getPassword())
//                .authorities(user.getRole()) // here, if role is "ROLE_ADMIN", it's fine
//                .build();
//
//    }
//}
