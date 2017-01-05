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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memetube.models.VoteMeme;
import com.memetube.service.MemeService;
import com.memetube.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService us;

	@Autowired 
	private MemeService ms;
	
	private final Map<String, List<String>> userDb = new HashMap<>();

	public UserController() {
		userDb.put("tom", Arrays.asList("user"));
		userDb.put("sally", Arrays.asList("user", "admin"));
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody final UserCredentials login) throws ServletException {


		if (!us.authenticateUser(login.username, login.password)) {
			throw new ServletException("Invalid login");
		}
		
		int id = us.getUserByUsername(login.username).getId();
		
		return new LoginResponse(Jwts.builder().setSubject(login.username).claim("roles", "").claim("id", id)
				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
	}

	@RequestMapping(value = "auth", method = RequestMethod.POST)
	public LoginResponse auth(@RequestBody final UserCredentials login) throws ServletException {

		if (login.username == null || login.password == null) {
			throw new ServletException("Invalid login");
		}

		us.insertUser(login.username, login.password);
		int id = us.getUserByUsername(login.username).getId();

		return new LoginResponse(Jwts.builder().setSubject(login.username).claim("roles", "").claim("id", id)
				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
	}
	
	@RequestMapping(value = "/memes", method = RequestMethod.GET)
	public ResponseEntity<List<VoteMeme>> get(@RequestParam("category_id") int categoryId, @RequestParam("page") int page, @RequestParam("page_size") int pageSize) {
		//ms.getFrankenstainMeme ? 
		
		List<VoteMeme> listOfmemes = ms.getMemesForCategory(categoryId, page, pageSize);
		
		return new ResponseEntity<List<VoteMeme>>(listOfmemes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dayMemeType", method = RequestMethod.GET)
	public ResponseEntity<Integer> getDayMemeType() {
		//ms.getFrankenstainMeme ? 
		
		
		return new ResponseEntity<>(null, HttpStatus.OK);
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
