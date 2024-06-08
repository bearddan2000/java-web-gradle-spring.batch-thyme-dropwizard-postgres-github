package example.component;

import org.springframework.stereotype.Component;

//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import lombok.extern.log4j.Log4j2;

import example.repository.CatagoryKeywordRepository;
import example.repository.CatagoryRepository;
import example.repository.KeywordPortalRepository;
import example.repository.KeywordRepository;
import example.model.catagory.*;
import example.model.keywords.Keyword;
import example.model.Filter;
import example.model.Portal;

//@Log4j2
@Component
public class ProcessorKeyword implements ILog
{
	private static final Logger log = LoggerFactory.getLogger(ProcessorKeyword.class);

	KeywordRepository keywordRepository;

	CatagoryRepository catagoryRepository;

	CatagoryKeywordRepository catagoryKeywordRepository;

	ProcessorKeywordPortal processorKeywordPortal;

	ProcessorCatagory processorCatagory;
	
	public ProcessorKeyword(
		KeywordRepository a,
		KeywordPortalRepository b,
		CatagoryRepository c,
		CatagoryKeywordRepository d
	){
		keywordRepository = a;
		processorKeywordPortal = new ProcessorKeywordPortal(b);
		processorCatagory = new ProcessorCatagory(c, d);
		catagoryRepository = c;
		catagoryKeywordRepository = d;
	}

	public void process(ProcessorFilter processorFilter, Portal data, Long portalId, String word) {

		if (processorFilter == null) {

			error("ProcessorFilter object is null");

			return;

		} else if (data == null) {
			error("Portal object is null");

			return;

		} else if (word == null) {

			error("word object is null");

			return;

		}


		Filter filter = processorFilter.getFilters(word);

		if (filter != null) {

			warn("Filter object is null");

			return ;

		} else if (keywordRepository == null) {
			warn("KeywordRepository object is null");

			return;

		} else if (catagoryRepository == null) {
			warn("CatagoryRepository object is null");

			return;

		}

		Keyword keyword = keywordRepository.findAll()
											.stream()
											.filter(e -> e.getWord().equals(word))
											.findFirst()
											.orElse(null);

		
		if (keyword != null) {

			info("Processing Keyword : "+keyword);

		} else {

			warn("Keyword object is null");

			keyword = new Keyword();

			info("Set word in Keyword object");

			keyword.setWord(word);

			info("Save Keyword object");

			keywordRepository.save(keyword);

			info("Processing Keyword : "+keyword);
		}

		if (processorKeywordPortal == null) {

			warn("ProcessorKeywordPortal object is null");

			return;

		}

		info("Set word in Portal object");

		data.setWord(keyword);

		info("Save Keyword object after catagory process");

		keywordRepository.save(keyword);

		processorCatagory.process(keyword, data, word);
		
		processorKeywordPortal.process(keyword, portalId);

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
