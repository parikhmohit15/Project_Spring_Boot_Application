package ca.sheridancollege.parikhmo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.parikhmo.beans.Ticket;
import ca.sheridancollege.parikhmo.beans.User;
import ca.sheridancollege.parikhmo.repositories.SecurityRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SecurityController {

	 private SecurityRepository secRepo;
	@GetMapping("/login")
	public String login() {
	return "login.html";

	}
	@GetMapping("/access-denied")
	public String denied() {
	return "Access-denied.html";
	}

	@GetMapping("/register")
	public String register() {
	return "Registration.html";

	}
//	@PostMapping("/register")
//	public String doneregister(@RequestParam String username ,
//			@RequestParam String password ) {
//	
//		secRepo.addUser(username, password);
//		
//		User user =secRepo.findUserAccount(username);
//		secRepo.addRoles(user.getUserId(), 1);
//		secRepo.addRoles(user.getUserId(), 2);
//		secRepo.addRoles(user.getUserId(), 3);
//	
//		
//		
//		return "redirect:/";
//}
	@PostMapping("/register")  
//    public String doRegister(@RequestParam String username,  
//                             @RequestParam String password,  
//                             @RequestParam(required = false) List<String> roles) {  
//        // Create the user  
//        secRepo.addUser(username, password);  
//        // Get the user  
//        User user = secRepo.findUserAccount(username);  
//        
//        // Assign roles based on the selected checkboxes  
//        if (roles != null) {  
//            for (String role : roles) {  
//                switch (role) {  
//                    case "ROLE_VENDER":  
//                        secRepo.addRoles(user.getUserId(), 1); // Admin role ID  
//                        break;   
//                    case "ROLE_GUEST":  
//                        secRepo.addRoles(user.getUserId(), 2); // Guest role ID  
//                        break;  
//                }  
//            }  
//        }  
	public String doRegister(@RequestParam String username,
			@RequestParam String password) {
		//Create the user
		secRepo.addUser(username, password);
		//Get the user
		User user = secRepo.findUserAccount(username);
		secRepo.addRoles(user.getUserId(), 2);

        return "redirect:/guest";  
    }  

	
	//If user successfully login then only we can access this one
//		@GetMapping("/user")
//		public String userRoot(Authentication authentication) {
//			String username = authentication.getName();
//			//we need list of roles, Can't access it directly so will use GrantedAuthorities , just opp of what we did in UserDetailsServiceImpl
//			//Where we converted list of roles into GrantedAuthority:
//			ArrayList<String>roles = new ArrayList<String>();
//			for(GrantedAuthority ga : authentication.getAuthorities()) {
//				roles.add(ga.getAuthority());
//			}
//			System.out.println(username);
//			System.out.println(roles);
//			return "/user/Home.html";
//					
//		}
		
		
}