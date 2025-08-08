package com.example.demo.securitysample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	   @Autowired
	    private AppUserDAO appUserDAO;
	 
	    @Autowired
	    private AppRoleDAO appRoleDAO;
	    
	    @Autowired
	    private UserReporsitory userRepo;
	    @Autowired
	    private UserlogRepositrory userlogrepo;
	    
	    @Override
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	        AppUser appUser = this.appUserDAO.findUserAccount(userName);
	 
	        if (appUser == null) {
	            System.out.println("User not found! " + userName);
	            UserLog datalog=UserLog.builder().user(userName ).endpoint("/Login").requestBody("failed").status("400").requestMethod("Post").build();
		         userlogrepo.save(datalog);
	            throw new UsernameNotFoundException("User " + userName + " was not found !!");
	        }
	 
	        // [ROLE_USER, ROLE_ADMIN,..]
	        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getId());
	         String datas="Found User: " + appUser.getUsername() +" , With Role : "+roleNames + " On "+ new Date();

	         System.out.println(datas);
	         UserLog datalog=UserLog.builder().user(appUser.getUsername() ).endpoint("/Login").requestBody(datas).status("200").requestMethod("Post").build();
	         userlogrepo.save(datalog);
	         try {
	        	 //InetAddress localhost = InetAddress.getLocalHost();
	           //  System.out.println("System IP Address : " +  (localhost.getHostAddress()).trim());
	             
	        	 AppUser userInfo = userRepo.getById(appUser.getId());
	        	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Unable to Save the AccessLog !! due to  :" +e.getMessage());
			}
	         
	 
	        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
	        if (roleNames != null) {
	            for (String role : roleNames) {
	                // ROLE_USER, ROLE_ADMIN,..
	                GrantedAuthority authority = new SimpleGrantedAuthority(role);
	                grantList.add(authority);
	            }
	        }
	 
	        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(), 
	                appUser.getPassword(), grantList);
	 
	        
	        return userDetails;
	    }
}
