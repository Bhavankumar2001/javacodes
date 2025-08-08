package com.example.demo.securitysample;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,Authentication authentication) throws IOException, ServletException {

		boolean hasUserRole = false;
		boolean hasAdminRole = false;
		boolean hasApproverRole = false;
		boolean hasGmApproverRole = false;
		boolean hasReportRole = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				hasAdminRole = true;
				break;
			}else if (grantedAuthority.getAuthority().equals("ROLE_EMPLOYEE")) {
				hasUserRole = true;
				break;
			}  else if (grantedAuthority.getAuthority().equals("ROLE_APPROVER")){
				hasApproverRole = true;
			} else if (grantedAuthority.getAuthority().equals("ROLE_GM_APPRVOER")){
				hasGmApproverRole = true;
			} else if (grantedAuthority.getAuthority().equals("ROLE_REPORT")){
				hasReportRole = true;
			}
		}

		if (hasAdminRole) {
			redirectStrategy.sendRedirect(arg0, arg1, "/capex/sterling/description/create");
		} else if (hasUserRole) {
			redirectStrategy.sendRedirect(arg0, arg1, "/capex/sterling/description/create");
		} else if(hasApproverRole){
			redirectStrategy.sendRedirect(arg0, arg1, "/intentApprovel");
		} else if(hasGmApproverRole){
			redirectStrategy.sendRedirect(arg0, arg1, "/intentApprovel");
		}else if(hasReportRole){
			redirectStrategy.sendRedirect(arg0, arg1, "/reportAccess");
		} else {
			throw new IllegalStateException();
		}
	}

}