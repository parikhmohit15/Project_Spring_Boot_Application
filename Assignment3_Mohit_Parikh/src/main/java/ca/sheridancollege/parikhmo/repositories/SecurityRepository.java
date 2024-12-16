package ca.sheridancollege.parikhmo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.parikhmo.beans.User;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Repository
public class SecurityRepository {
	private NamedParameterJdbcTemplate jdbc;

	public User findUserAccount(String username) {
	MapSqlParameterSource params = new MapSqlParameterSource();
		
		String query = "Select * from sec_user WHERE userName=:name";
		params.addValue("name", username);
		
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, params, new BeanPropertyRowMapper<User>(User.class));
		
		return (users.size() > 0 ) ? users.get(0) : null;		
	}
	
	public List<String> getRolesById(long userId){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName "
		+ "FROM user_role, sec_role WHERE "
		+ "user_role.roleId=sec_role.roleId and userId=:id";
		parameters.addValue("id", userId);
		ArrayList<String> roles = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
		roles.add((String) row.get("roleName"));
		}
		return roles;
		
		}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void addUser(String username,String password) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String query = "Insert into SEC_User (userName, encryptedPassword, ENABLED)"
				+ "values (:un, :ps, 1);"
				+ " ";
		
		params.addValue("un", username);
//		params.addValue("ps", encoder.encode(password));
		
		params.addValue("ps", passwordEncoder().encode(password));
		jdbc.update(query,params);
	}
	
	public void addRoles(long userId,long roleId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String query = "Insert into user_role (userId, roleId)"
				+ "values (:uid, :rid);"
				+ " ";
		
		params.addValue("uid", userId);
//		params.addValue("ps", encoder.encode(password));
		
		params.addValue("rid", roleId);
		jdbc.update(query,params);
	}

	public List<User> getUsersByRole(String roleName) {  
	    MapSqlParameterSource params = new MapSqlParameterSource();  
	    String query = "SELECT * FROM SEC_USER WHERE userId IN " +  
                "(SELECT userId FROM USER_ROLE WHERE roleId IN " +  
                "(SELECT roleId FROM SEC_ROLE WHERE roleName = :roleName)) " +  
                "ORDER BY userName ASC"
                ;  
	    params.addValue("roleName", roleName);  
	    return jdbc.query(query, params, new BeanPropertyRowMapper<>(User.class));  
	}

	}	

