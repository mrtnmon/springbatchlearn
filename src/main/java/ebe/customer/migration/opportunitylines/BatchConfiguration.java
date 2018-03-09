package ebe.customer.migration.opportunitylines;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import be.ebts.schemas._2014._05.qe.CreateOpportunityRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@Import({BatchScheduler.class})
public class BatchConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    private Environment env;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public OpportunityReader reader;

    @Autowired
    public OpportunityProcessor processor;

    @Autowired
    public OpportunityWriter writer;

    @Autowired
    public Logger logger;

    @Autowired
    public JobCompletionNotificationListener listener;

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void perform() throws Exception {

        System.out.println("Job Started at :" + new Date());

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(importOpportunityLineJob(), param);

        System.out.println("Job finished with status :" + execution.getStatus());
    }
    @Bean
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository getJobRepo() throws Exception {
        return new MapJobRepositoryFactoryBean(getTransactionManager()).getObject();
    }
    @Bean
    public Job importOpportunityLineJob() {
        try {
            checkIfFileExists();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jobBuilderFactory.get("importOpportunityLineJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<OpportunityRequestDTO, CreateOpportunityRequest>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    // end::jobstep[]
    private void checkIfFileExists() throws IOException {
        String fileName = env.getProperty("persistence.file");
        if (Files.notExists(Paths.get(fileName))) {
            FileUtils.touch(new File(fileName));
            LOGGER.warn("ssssss");
//			Files.createDirectories(Paths.get(fileName));
            System.out.println("did create file");
        } else {
            logger.warn("warn");
            System.out.println("did not create file");
        }
    }

}
