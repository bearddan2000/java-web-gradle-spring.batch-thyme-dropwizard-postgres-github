package example.component;

import org.springframework.stereotype.Component;

//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import lombok.extern.log4j.Log4j2;

import example.repository.FilterRepository;
import example.model.Filter;
import example.model.Portal;

//@Log4j2
@Component
public class ProcessorFilter implements ILog
{
	private static final Logger log = LoggerFactory.getLogger(ProcessorFilter.class);

	FilterRepository filterRepository;

	public ProcessorFilter(FilterRepository repository){
		filterRepository = repository;
	}

	public Filter getFilters(String word)
	{

		if (filterRepository == null) {
			warn("FilterRepository object is null");

			return null;

		} else if (word == null) {

			error("word object is null");

			return null;

		}

		info("Find filter for: " + word);

		return filterRepository.findAll()
											.stream()
											.filter(e -> e.getName().equals(word))
											.findFirst()
											.orElse(null);
	}

	public void process(Portal data, String word) {

		if (data == null) {
			error("Portal object is null");

			return;
		} else if (word == null) {

			error("word object is null");

			return;
		}

		Filter filter = this.getFilters(word);

		if (filter != null) {

			info("Filter found:" + filter);

			switch (filter.getFilter().intValue()) {
				case 1:
					info("Build filter type found");
					data.setBuild(filter);
					break;
				case 2:
					info("Language filter type found");
					data.setLang(filter);
					break;
				case 3:
					info("Platform filter type found");
					data.setPlatform(filter);
					break;
				case 4:
					info("Tech filter type found");
					data.setTech(filter);
					break;
				default:
					warn("No filter type found");
			}

			info("Processing filter : "+filter);
		} else {

			warn("Filter was null");
		}
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
