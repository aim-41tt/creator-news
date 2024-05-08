package ru.example.creato_rnews.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonService {

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Async
	public <T> String toJSON(T Object) {
		String json = null;

		try {
			json = mapper.writeValueAsString(Object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@Async
	public <T> T jsonToObject(String json, Class<T> classObject) {
		T Object = null;

		try {
			Object = mapper.readValue(json, classObject);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return Object;
	}

}
