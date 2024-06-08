package example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import example.model.api.Portal;

@RestController
@RequestMapping("/api")
public class PortalRestController {

	private final RestTemplate template = new RestTemplate();

	private final String API_URL = "http://api-portal:8080/";

	@RequestMapping(path="/portal", method=RequestMethod.GET)
	public List<Portal> getAllPortalApi() {
		return template.getForObject(API_URL + "/portal", List.class);
	}

}
