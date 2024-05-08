package ru.example.creato_rnews.repositorys;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.example.creato_rnews.models.News;


public interface NewsRepository extends CrudRepository<News, Long>{
	List<News> findByTitle (String title);
	List<News> findByTeame (String teame);

}
