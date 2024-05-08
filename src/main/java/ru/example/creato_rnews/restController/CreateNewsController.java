package ru.example.creato_rnews.restController;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(name = "/news")
public class CreateNewsController {

    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private ContentNewsRepository contentNewsRepo;
    @Autowired
    private JsonService jsonService;

    @GetMapping("/all")
    public CompletableFuture<String> getAllNews() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<News> news = newsRepo.findAll();
            return jsonService.toJSON(news);
        });
    }

    @GetMapping("/{id}")
    public CompletableFuture<String> getNewsById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            return jsonService.toJSON(newsRepo.findById(id));
        });
    }

    @GetMapping("/random")
    public CompletableFuture<String> getRandomNews() {
        return CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            List<News> newsList = (List<News>) newsRepo.findAll();
            return jsonService.toJSON(newsList.get(random.nextInt(newsList.size())));
        });
    }

    @GetMapping("/newtoteame")
    public CompletableFuture<List<News>> getNewToTeame(@RequestParam String Teame) {
        return CompletableFuture.supplyAsync(() -> {
            return newsRepo.findByTeame(Teame);
        });
    }

    @PostMapping("/add")
    public CompletableFuture<String> postAddNews(@RequestBody String json) {
        return CompletableFuture.supplyAsync(() -> {
            News news = jsonService.jsonToObject(json, News.class);
            contentNewsRepo.save(news.getContentNews());
            newsRepo.save(news);
            return json;
        });
    }
}