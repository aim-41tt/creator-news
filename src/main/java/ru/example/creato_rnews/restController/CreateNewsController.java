package ru.example.creato_rnews.restController;


import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.example.creato_rnews.models.News;
import ru.example.creato_rnews.repositorys.ContentNewsRepository;
import ru.example.creato_rnews.repositorys.NewsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class CreateNewsController {

	@Autowired
	private NewsRepository newsRepo;
	@Autowired
	private ContentNewsRepository contentNewsRepo;

	@GetMapping("/news/all")
	public String getAllNews() {
		Iterable<News> news = newsRepo.findAll();
		
		String Json = null;
		
		try {
			ObjectMapper maper = new ObjectMapper();
			Json = maper.writeValueAsString(news);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Json;
	}

	@GetMapping("/news/{id}")
	public Optional getNewsById(@PathVariable Long id) {
		Optional newsOptional = newsRepo.findById(id);

		return newsOptional;
	}

	@GetMapping("/news/random")
	public String getRandomNews() {
		Random random = new Random();

		return new String();
	}
	
	@PostMapping("/add")
	public String postAddNews(@RequestBody String json) {
		News news = null;
		ObjectMapper maper = new ObjectMapper();
		
		try {
			news = maper.readValue(json, News.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentNewsRepo.save(news.getContentNews());
		newsRepo.save(news);
		
		return json;
	}

}
