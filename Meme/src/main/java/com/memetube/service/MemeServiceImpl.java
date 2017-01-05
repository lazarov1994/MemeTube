package com.memetube.service;

import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.GetResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memetube.client.MemetubeClient;
import com.memetube.models.Category;
import com.memetube.models.VoteMeme;
import com.memetube.repository.MemeRepository;

@Transactional
@Service
public class MemeServiceImpl implements MemeService {

	@Autowired
	private MemeRepository memeRepo;

	@Override
	public Integer addMeme(String title, String image, int category, int userId) {
		return memeRepo.addMeme(title, image, category, userId);
	}

	@Override
	public List<VoteMeme> getMemesForCategory(Integer categoryId, int page, int pageSize) {
		return memeRepo.getMemesForCategory(categoryId, page, pageSize);
	}

	@Override
	public List<Category> getAllCategories() {
		return memeRepo.getAllCategories();
	}

	@Override
	public Integer getMemeCategoryOfTheDay() {
		MemetubeClient mc = new MemetubeClient();
		
		// TODO Auto-generated method stub
		return mc.getMemeCategoryOfTheDay().getStudent().getStudentId();
	}

}
