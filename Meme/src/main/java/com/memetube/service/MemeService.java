package com.memetube.service;

import java.util.List;

import com.memetube.models.Meme;

public interface MemeService {
    public Meme getMeme(int id);

    public List<Meme> getMemesForCategory(Integer categoryId, int page, int pageSize);

}
