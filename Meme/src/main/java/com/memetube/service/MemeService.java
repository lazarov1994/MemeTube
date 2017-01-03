package com.memetube.service;

import java.util.List;

import com.memetube.models.Category;
import com.memetube.models.VoteMeme;

public interface MemeService {
    public List<Category> getAllCategories();

    public Integer addMeme(String title, String image, int category, int userId);

    public List<VoteMeme> getMemesForCategory(Integer categoryId, int page, int pageSize);

}
