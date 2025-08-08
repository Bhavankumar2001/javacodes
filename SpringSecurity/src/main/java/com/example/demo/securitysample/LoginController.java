/**
 * 
 */
package com.example.demo.securitysample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author  bhavan
 *
 */

@Controller
public class LoginController {
	
	/**
	 * @author bhavan
	 * @return
	 */
	@GetMapping(value= {"/","","/login"})
	public String Login() {
		return "login";
	}
	
	/**
	 * @author bhavan
	 * @return
	 */
	@GetMapping(value= {"/logout"})
	public String LogOut() {
		return "login";
	}

}
