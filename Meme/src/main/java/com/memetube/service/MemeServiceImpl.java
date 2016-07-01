package com.memetube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.memetube.models.Category;
import com.memetube.models.VoteMeme;
import com.memetube.repository.MemeRepository;

@Transactional
@Service
public class MemeServiceImpl implements MemeService {

    @Autowired
    private MemeRepository memeRepo;

    @Override
    public VoteMeme addMeme(String title, String image, int category, int userId) {
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

}
