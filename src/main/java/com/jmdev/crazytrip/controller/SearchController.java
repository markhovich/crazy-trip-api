package com.jmdev.crazytrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmdev.crazytrip.service.ISearchService;

@RestController
@RequestMapping(value="/search")
public class SearchController {

	@Autowired
	private ISearchService ss;
	
	@GetMapping("")
	public List<Object> globalSearch(@RequestParam("search") String search){
		return this.ss.globalSearch(search);
	}
}
