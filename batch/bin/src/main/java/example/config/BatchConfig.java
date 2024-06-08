package example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import example.component.ProcessorPortal;
import example.listener.JobCompletionListener;

import example.model.Portal;
import example.repository.*;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

@Autowired
public JobBuilderFactory jobBuilderFactory;

@Autowired
public StepBuilderFactory stepBuilderFactory;

@Autowired
PortalRepository repository;

@Autowired
FilterRepository filterRepository;

@Autowired
KeywordRepository keywordRepository;

@Autowired
KeywordPortalRepository keywordPortalRepository;

@Autowired
CatagoryRepository catagoryRepository;

@Autowired
CatagoryKeywordRepository catagoryKeywordRepository;

@Bean
public ItemWriter<Portal> writer(JpaRepository r){
	if(r == null){
		return records -> { System.out.println("PortalRepo is null"); };	
	}

	 return records -> { 
		r.saveAll(records);
	};
}

//Reader class Object
@Bean
public FlatFileItemReader<Portal> reader() {

	 FlatFileItemReader<Portal> reader= new FlatFileItemReader<>();
	 reader.setResource(new ClassPathResource("/portal.csv"));
	 reader.setLineMapper(new DefaultLineMapper<>() {{
			 setLineTokenizer(new DelimitedLineTokenizer() {{
					setDelimiter(DELIMITER_COMMA);
					setNames("name");
			 }});

			 setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
					setTargetType(Portal.class);
			 }});
	 }});
	 return reader;
}
	//Step Object
	@Bean
	public Step stepA() {
		ProcessorPortal processorPortal = new ProcessorPortal(
			filterRepository,
			keywordRepository,
			keywordPortalRepository,
			catagoryRepository,
			catagoryKeywordRepository
		);
		 return stepBuilderFactory.get("stepA")
						 .<Portal,Portal>chunk(50)
						 .reader(reader())
						 .processor(processorPortal)
						 .writer(writer(repository))
						 .build();
	}

	//Job Object
	@Bean
	public Job jobA(){
		 return jobBuilderFactory.get("jobA")
						.incrementer(new RunIdIncrementer())
						.listener(listener())
						.start(stepA())
						.build();
	}
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
}
