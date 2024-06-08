package example.component;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import lombok.extern.log4j.Log4j2;

import example.model.Portal;
import example.repository.*;

//@Log4j2
@Component
public class ProcessorPortal implements ItemProcessor<Portal, Portal>, ILog
{
	private static final Logger log = LoggerFactory.getLogger(ProcessorPortal.class);

	int portalObjectCount = 1;

	ProcessorFilter processorFilter;

	ProcessorKeyword processorKeyword;

	PortalRepository portalRepository;

	public ProcessorPortal(
		FilterRepository filterRepository,
		KeywordRepository keywordRepository,
		KeywordPortalRepository keywordPortalRepository,
		CatagoryRepository catagoryRepository,
		CatagoryKeywordRepository catagoryKeywordRepository
	){
		
		processorFilter = new ProcessorFilter(filterRepository);

		processorKeyword = new ProcessorKeyword(
			keywordRepository,
			keywordPortalRepository,
			catagoryRepository,
			catagoryKeywordRepository
		);

	}

	@Override
	public Portal process(Portal data) throws Exception {

		if (data == null) {

			error("Portal object is null");

			return null;
		}

		String[] words = data.getName().split("-");

		if (words.length > 1) {

			info("Name parced");

			if (processorFilter == null) {

				warn("ProcessorFilter object is null");

				return data;

			} else if (processorKeyword == null) {

				warn("ProcessorKeyword object is null");

				return data;

			}

			Long portalId = new Long(portalObjectCount++);

			for (String token : words) {

				processorFilter.process(data, token);

				processorKeyword.process(processorFilter, data, portalId, token);
			}

			info("Processed data : "+data);

		} else {

			warn("Data not processed: " +data.getName());
		}

		return data;

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
