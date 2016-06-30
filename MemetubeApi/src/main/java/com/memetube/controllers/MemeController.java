package com.memetube.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memetube.models.Meme;

@RestController
public class MemeController {
	
	@RequestMapping(value = "/getMeme")
	public ResponseEntity<Meme> get(){
		Meme meme = new Meme();
		meme.setId(5L);
		meme.setTitle("John Snow");
		meme.setUserId(2L);
		
		return new ResponseEntity<Meme>(meme, HttpStatus.OK);
		
		
		
	}

}
