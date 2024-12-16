package ca.sheridancollege.parikhmo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.parikhmo.repositories.SecurityRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	private SecurityRepository secRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//Find the user based on their username
		ca.sheridancollege.parikhmo.beans.User user= secRepo.findUserAccount(username);
		
		//If the user doesn't exist then throw exception
		if (user == null) {
			System.out.println("User could not be found");
			throw new UsernameNotFoundException("User not found");
			
		}
		
		//Get a list of roles for that user
		List<String> roles = secRepo.getRolesById(user.getUserId());
		
		//change the list of roles into a list of Granted Authorities
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (String role:roles) {
			grantList.add(new SimpleGrantedAuthority(role));
		}
		
		//Create a Spring user based on the above information
		//Import user from spring security core
		//import org.springframework.security.core.userdetails.User;
		
		User springUser = new User(user.getUserName(),
				user.getEncryptedPassword(),grantList);
		return (UserDetails)springUser;
	}

}
