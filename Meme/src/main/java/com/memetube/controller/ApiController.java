package com.memetube.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memetube.models.Meme;
import com.memetube.service.MemeService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	public MemeService ms;
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "role/{role}", method = RequestMethod.GET)
	public Boolean login(@PathVariable final String role, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");

		return ((List<String>) claims.get("roles")).contains(role);
	}

	
	@RequestMapping(value = "/memes", method = RequestMethod.POST)
	public ResponseEntity<Meme> addMeme(@RequestBody Meme memeForAdd) {
		//ms.addMeme
		return new ResponseEntity<Meme>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/memes/{id}/upvote", method = RequestMethod.POST)
	public ResponseEntity<Meme> upVoteMeme(@PathVariable("id") int id) {
		//ms.upVoteMeme
		return new ResponseEntity<Meme>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/memes/{id}/downvote", method = RequestMethod.POST)
	public ResponseEntity<Meme> downVoteMeme(@PathVariable("id") int id) {
		//ms.downVoteMeme
		return new ResponseEntity<Meme>(HttpStatus.OK);
	}
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<Meme> getCategories() {
		//ms.getCategories
		return new ResponseEntity<Meme>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/meme", method = RequestMethod.GET)
	public ResponseEntity<Meme> get(@RequestParam("category_id") int categoryId, @RequestParam("page") int page, @RequestParam("page_size") int pageSize) {
		//ms.getFrankenstainMeme ? 
		return new ResponseEntity<Meme>(HttpStatus.OK);
	}

}
