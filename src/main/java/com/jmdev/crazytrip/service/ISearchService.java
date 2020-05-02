package com.jmdev.crazytrip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISearchService {
	
	@Autowired
	private ArticleService as;
	@Autowired
	private CommentService cs;
	@Autowired
	private UserService us;
	
	public List<Object> globalSearch(String search) {
		List<Object> list = new ArrayList<Object>();
		list.add(this.as.findByString(search));
		list.add(this.cs.findByString(search));
		list.add(this.us.findByString(search));
		return list;
	}
}
