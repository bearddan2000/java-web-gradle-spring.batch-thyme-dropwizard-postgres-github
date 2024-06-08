package example.component;

import org.springframework.stereotype.Component;
//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import lombok.extern.log4j.Log4j2;

import example.repository.KeywordPortalRepository;
import example.model.keywords.KeywordPortal;
import example.model.keywords.Keyword;
import example.model.Portal;

//@Log4j2
@Component
public class ProcessorKeywordPortal implements ILog
{
	private static final Logger log = LoggerFactory.getLogger(ProcessorKeywordPortal.class);

	KeywordPortalRepository keywordPortalRepository;

	public ProcessorKeywordPortal(KeywordPortalRepository repository){
		keywordPortalRepository = repository;
	}

	public void process(Keyword keyword, Long portalId) {

		if (keyword == null) {
			error("Keyword object is null");

			return;
		}

		KeywordPortal keywordPortal = new KeywordPortal();

		info("Set Keyword id");
		keywordPortal.setKeyword(keyword);

		info("Set Portal id");
		keywordPortal.setPortalId(portalId);

		if (keywordPortalRepository != null) {

			keywordPortalRepository.save(keywordPortal);

			info("Processing KeywordPortal: "+keywordPortal);

		} else {

			warn("KeywordPortalRepository object null");
		}
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
