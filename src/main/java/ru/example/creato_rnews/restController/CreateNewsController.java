package ru.example.creato_rnews.restController;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ru.example.creato_rnews.Service.JsonService;
import ru.example.creato_rnews.models.News;
import ru.example.creato_rnews.repositorys.ContentNewsRepository;
import ru.example.creato_rnews.repositorys.NewsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController()
public class CreateNewsController {

    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private ContentNewsRepository contentNewsRepo;
    @Autowired
    private JsonService jsonService;

    @GetMapping("/all")
    public CompletableFuture<Object> getAllNews() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<News> news = newsRepo.findAll();
            if (news.iterator().hasNext()) {
                return jsonService.toJSON(news);
            } else {
                return jsonService.toJSON("No news available");
            }
        });
    }



    @GetMapping("/{id}")
    public CompletableFuture<Object> getNewsById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<News> optionalNews = newsRepo.findById(id);
            if (optionalNews.isPresent()) {
                News news = optionalNews.get();
                return jsonService.toJSON(news);
            } else {
                return jsonService.toJSON("News not found for id: " + id);
            }
        });
    }

    @GetMapping("/random")
    public CompletableFuture<Object> getRandomNews() {
        return CompletableFuture.supplyAsync(() -> {
            List<News> newsList = (List<News>) newsRepo.findAll();
            if (newsList.isEmpty()) {
                return jsonService.toJSON("No news available");
            } else {
                Random random = new Random();
                News randomNews = newsList.get(random.nextInt(newsList.size()));
                return jsonService.toJSON(randomNews);
            }
        });
    }


    @GetMapping("/newtoteame")
    public CompletableFuture<Object> getNewToTeame(@RequestParam String topic) {
        return CompletableFuture.supplyAsync(() -> {
            List<News> newsList = newsRepo.findByTopic(topic);
            if (newsList.isEmpty()) {
                return jsonService.toJSON("No news found for team: " + topic);
            } else {
                return jsonService.toJSON(newsList);
            }
        });
    }


    @PostMapping("/add")
    public CompletableFuture<Object> postAddNews(@RequestBody String json) {
        return CompletableFuture.supplyAsync(() -> {
            if (json == null || json.isEmpty()) {
                return jsonService.toJSON("Request body is empty");
            }
            
            try {
                News news = jsonService.jsonToObject(json, News.class).join();
                if (news == null) {
                    return jsonService.toJSON("Invalid JSON data");
                }
                contentNewsRepo.save(news.getContentNews());
                newsRepo.save(news);
                return jsonService.toJSON("News added successfully");
            } catch (Exception e) {
                e.printStackTrace();
                return jsonService.toJSON("Failed to add news: " + e.getMessage());
            }
        });
    }



}