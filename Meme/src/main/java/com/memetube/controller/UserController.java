package com.memetube.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.memetube.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService us;
	
    private final Map<String, List<String>> userDb = new HashMap<>();

    public UserController() {
        userDb.put("tom", Arrays.asList("user"));
        userDb.put("sally", Arrays.asList("user", "admin"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserCredentials login)
        throws ServletException {
        if (login.username == null || !userDb.containsKey(login.username)) {
            throw new ServletException("Invalid login");
        }
        return new LoginResponse(Jwts.builder().setSubject(login.username)
            .claim("roles", userDb.get(login.username)).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }
    
    
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public LoginResponse auth(@RequestBody final UserCredentials login)
    	
    
    
        throws ServletException {
        if (login.username == null || !userDb.containsKey(login.username)) {
            throw new ServletException("Invalid login");
        }
        return new LoginResponse(Jwts.builder().setSubject(login.username)
            .claim("roles", userDb.get(login.username)).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }
    
    
    

    @SuppressWarnings("unused")
    private static class UserCredentials {
        public String username;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
