package com.memetube.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.memetube.Meme;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class ApiController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "role/{role}", method = RequestMethod.GET)
	public Boolean login(@PathVariable final String role, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");

		return ((List<String>) claims.get("roles")).contains(role);
	}

	@RequestMapping(value = "/getMeme")
	public ResponseEntity<Meme> get() {
		Meme meme = new Meme();
		meme.setId(5L);
		meme.setTitle("John Snow");
		meme.setUserId(2L);

		return new ResponseEntity<Meme>(meme, HttpStatus.OK);
	}

}
