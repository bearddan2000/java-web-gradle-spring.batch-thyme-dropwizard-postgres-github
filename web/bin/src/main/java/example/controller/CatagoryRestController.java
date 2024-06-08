package example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import example.model.api.Catagory;

@RestController
@RequestMapping("/api")
public class CatagoryRestController {

	private final RestTemplate template = new RestTemplate();

	private final String API_URL = "http://api-catagory:8080/";

	@RequestMapping(path="/catagory", method=RequestMethod.GET)
	public List<Catagory> getAllCatagoryApi() {
		return template.getForObject(API_URL + "/catagory", List.class);
	}

}
