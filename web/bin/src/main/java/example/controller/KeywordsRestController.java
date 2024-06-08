package example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import example.model.api.Keywords;

@RestController
@RequestMapping("/api")
public class KeywordsRestController {

	private final RestTemplate template = new RestTemplate();

	private final String API_URL = "http://api-keywords:8080/";

	@RequestMapping(path="/keywords", method=RequestMethod.GET)
	public List<Keywords> getAllKeywordsApi() {
		return template.getForObject(API_URL + "/keywords", List.class);
	}

}
