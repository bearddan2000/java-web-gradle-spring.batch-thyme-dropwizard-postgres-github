package example.component;

import org.springframework.stereotype.Component;

//import lombok.extern.log4j.Log4j2;

//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.repository.CatagoryKeywordRepository;
import example.model.catagory.CatagoryKeyword;
import example.model.keywords.Keyword;
import example.model.catagory.Catagory;
import example.model.Portal;

//@Log4j2
@Component
public class ProcessorCatagoryKeyword implements ILog
{
	private static final Logger log = LoggerFactory.getLogger(ProcessorCatagoryKeyword.class);

	CatagoryKeywordRepository catagoryKeywordRepository;

	CatagoryKeyword catagoryKeyword = new CatagoryKeyword();

	public ProcessorCatagoryKeyword(CatagoryKeywordRepository repository){
		catagoryKeywordRepository = repository;
	}

  public void process(Keyword keyword, Catagory catagory, Portal data)
  {
	if (keyword == null) {

		warn("Keyword object is null");

		return ;

	} else if (catagory == null) {
		warn("Catagory object is null");

		return;

	} else if (catagoryKeywordRepository == null) {
		warn("CatagoryKeywordRepository object is null");

		return;

	} 

	CatagoryKeyword catagoryKeyword = new CatagoryKeyword();

	info("Set Keyword id");
	catagoryKeyword.setCatagory(catagory);

	info("Set Keyword id");
	catagoryKeyword.setKeyword(keyword);

	catagoryKeyword.setPortal(data);

	catagoryKeywordRepository.save(catagoryKeyword);

  }
	@Override
	public void info(String msg)
	{
		log.info(msg);

//		System.out.println(msg);
	}

	@Override
	public void warn(String msg)
	{
		log.warn(msg);

//		System.out.println(msg);
	}

	@Override
	public void error(String msg)
	{
		log.error(msg);

//		System.err.println(msg);
	}
}
