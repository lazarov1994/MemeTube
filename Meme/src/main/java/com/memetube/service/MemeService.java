package com.memetube.service;

import java.util.List;

import com.memetube.models.Meme;
import com.memetube.models.VoteMeme;

public interface MemeService {
    public Meme getMeme(int id);

    public List<VoteMeme> getMemesForCategory(Integer categoryId, int page, int pageSize);

}
