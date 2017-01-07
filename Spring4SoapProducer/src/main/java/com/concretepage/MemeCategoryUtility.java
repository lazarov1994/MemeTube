package com.concretepage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.concretepage.soap.Category;

@Component
public class MemeCategoryUtility {
	private Map<Integer, Category> categoryMap = new HashMap<Integer, Category>();

	public MemeCategoryUtility() {
		Category c1 = new Category();
		c1.setId(0);
		c1.setName("black");
		Category c2 = new Category();
		c2.setId(2);
		c2.setName("animals");
		Category c3 = new Category();
		c3.setId(3);
		c3.setName("football");
		Category c4 = new Category();
		c4.setId(4);
		c4.setName("film");

		categoryMap.put(1, c1);
		categoryMap.put(2, c2);
		categoryMap.put(3, c3);
		categoryMap.put(4, c4);

	}

	public Category getCategoryByDay(int dayOfWeek) {
		return categoryMap.get(dayOfWeek);
	}
}
