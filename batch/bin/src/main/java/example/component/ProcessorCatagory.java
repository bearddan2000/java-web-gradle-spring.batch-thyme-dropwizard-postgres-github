package example.component;

import org.springframework.stereotype.Component;

//import lombok.extern.log4j.Log4j2;

//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import example.repository.CatagoryKeywordRepository;
import example.repository.CatagoryRepository;
import example.model.catagory.Catagory;
import example.model.keywords.Keyword;
import example.model.Portal;

//@Log4j2
@Component
public class ProcessorCatagory implements ILog
{
	private static final Logger log = LoggerFactory.getLogger(ProcessorCatagory.class);

	CatagoryRepository catagoryRepository;

	ProcessorCatagoryKeyword processorCatagoryKeyword;
	
	HashMap<String, Catagory> mapHashCatagory = new HashMap<String, Catagory>();

	public ProcessorCatagory(
		CatagoryRepository a,
		CatagoryKeywordRepository b
	){
		catagoryRepository = a;
		processorCatagoryKeyword = new ProcessorCatagoryKeyword(b);
	}

  public void process(Keyword keyword, Portal data, String word)
  {
	if (keyword == null) {

		warn("Keyword object is null");

		return ;

	} else if (catagoryRepository == null) {
		warn("CatagoryRepository object is null");

		return;

	} else if (data == null) {
		warn("Portal object is null");

		return;

	} else if (word == null) {
		warn("word is null");

		return;

	}

	Catagory catagory;

	if(mapHashCatagory.containsKey(word))
	{
		catagory = mapHashCatagory.get(word);
	}
	else
	{		
		catagory = catagoryRepository.findAll()
											.stream()
											.filter(e -> e.getWord().equals(word))
											.findFirst()
											.orElse(null);
		if (catagory == null) {
			warn("catagory object is null");

			return;

		}
		
		mapHashCatagory.put(word, catagory);
	}

	keyword.setCatagory(catagory);

	catagory.setKeyword(keyword);

	info("Processing catagory : "+catagory);

	processorCatagoryKeyword.process(keyword, catagory, data);
  }

	@Override
	public void info(String msg)
	{
		log.info(msg);

//		System.out.println("[Sys out INFO] "+msg);
	}

	@Override
	public void warn(String msg)
	{
		log.warn(msg);

//		System.out.println("[Sys out WARN] "+msg);
	}

	@Override
	public void error(String msg)
	{
		log.error(msg);

//		System.err.println("[Sys out ERROR] "+msg);
	}
}
