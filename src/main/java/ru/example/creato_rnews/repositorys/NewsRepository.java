package ru.example.creato_rnews.repositorys;


import org.springframework.data.repository.CrudRepository;

import ru.example.creato_rnews.models.News;


public interface NewsRepository extends CrudRepository<News, Long>{
	
}
