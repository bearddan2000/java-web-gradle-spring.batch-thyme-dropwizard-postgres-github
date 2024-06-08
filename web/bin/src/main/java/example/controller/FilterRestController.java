package example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import example.model.api.Filter;

@RestController
@RequestMapping("/api")
public class FilterRestController {

	private final RestTemplate template = new RestTemplate();

	private final String API_URL = "http://api-filter:8080/";

	@RequestMapping(path="/filter/build", method=RequestMethod.GET)
	public List<Filter> getAllBuildApi() {
		return template.getForObject(API_URL + "filter/build", List.class);
	}

	@RequestMapping(path="/filter/lang", method=RequestMethod.GET)
	public List<Filter> getAllLangApi(){
		return template.getForObject(API_URL + "filter/lang", List.class);
	}

	@RequestMapping(path="/filter/platform", method=RequestMethod.GET)
	public List<Filter> getAllPlatformApi(){
		return template.getForObject(API_URL + "filter/platform", List.class);
	}

	@RequestMapping(path="/filter/tech", method=RequestMethod.GET)
	public List<Filter> getAllTechApi(){
		return template.getForObject(API_URL + "filter/tech", List.class);
	}

}
