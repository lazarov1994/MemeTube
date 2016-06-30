package com.memetube.repository;

import java.util.List;

import com.memetube.models.Meme;

public interface MemeRepository {
    public Meme getMeme(int id);

    public List<Meme> getMemesForCategory(Integer categoryId, int page, int pageSize);
}
