package com.memetube.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memetube.models.Category;
import com.memetube.models.VoteMeme;
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
	public ResponseEntity<Integer> addVoteMeme(@RequestBody VoteMeme memeForAdd, final HttpServletRequest request) {

		final Claims claims = (Claims) request.getAttribute("claims");
		Integer userId = (Integer) claims.get("id");

		int memeId = ms.addMeme(memeForAdd.getTitle(), memeForAdd.getImage(), memeForAdd.getCategoryId(), userId);

		// ms.addVoteMeme
		return new ResponseEntity<Integer>(memeId, HttpStatus.OK);
	}

	@RequestMapping(value = "/memes/{id}/upvote", method = RequestMethod.POST)
	public ResponseEntity<VoteMeme> upVoteMeme(@PathVariable("id") int id, final HttpServletRequest request) {
		// ms.upVoteVoteMeme

		final Claims claims = (Claims) request.getAttribute("claims");
		Integer userId = (Integer) claims.get("id");

		return new ResponseEntity<VoteMeme>(HttpStatus.OK);
	}

	@RequestMapping(value = "/memes/{id}/downvote", method = RequestMethod.POST)
	public ResponseEntity<Integer> downVoteMeme(@PathVariable("id") int id, final HttpServletRequest request) {

		final Claims claims = (Claims) request.getAttribute("claims");
		Integer userId = (Integer) claims.get("id");

		// ms.downVoteVoteMeme
		return new ResponseEntity<Integer>(id, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> listOfAllCategories = ms.getAllCategories();
		// ms.getCategories
		return new ResponseEntity<List<Category>>(listOfAllCategories, HttpStatus.OK);
	}

	@RequestMapping(value = "/memes", method = RequestMethod.GET)
	public ResponseEntity<List<VoteMeme>> get(@RequestParam(value = "category_id", required = false) int categoryId,
			@RequestParam("page") int page, @RequestParam("page_size") int pageSize) {
		// ms.getFrankenstainVoteMeme ?
		List<VoteMeme> listOfMemes = ms.getMemesForCategory(categoryId, page, pageSize);
		return new ResponseEntity<List<VoteMeme>>(listOfMemes, HttpStatus.OK);
	}

	@RequestMapping(value = "/memeOfTheDay", method = RequestMethod.GET)
	public ResponseEntity<Category> getMemeOfTheDay() {
		Category category = ms.getMemeCategoryOfTheDay();
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

}
