package ca.sheridancollege.parikhmo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncryption {
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
		}
	
//	public static void main(String[] args) {
//		String pass = "456";
//		System.out.println(encryptPassword(pass));
//		System.out.println(encryptPassword(pass));
//		System.out.println(encryptPassword(pass));
//		
//	}
}

