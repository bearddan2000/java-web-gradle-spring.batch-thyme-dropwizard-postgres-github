package example.component;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

import example.model.api.Portal;

@Component
public class JobRunner implements CommandLineRunner, ILog {
  
  private static final Logger log = LoggerFactory.getLogger(JobRunner.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobA;

    private String getRepoResponse(String url){
      try
      {
          URL obj = new URL(url);
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();
          // optional default is GET
          con.setRequestMethod("GET");
          //add request header
          con.setRequestProperty("User-Agent", "Mozilla/5.0");
          int responseCode = con.getResponseCode();
          
          info("\nSending 'GET' request to URL : " + url);
          info("Response Code : " + responseCode);
          
          BufferedReader in = new BufferedReader(
                  new InputStreamReader(con.getInputStream()));
          String inputLine;
          StringBuffer response = new StringBuffer();
          while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
          }
          in.close();
          return response.toString();
      }
  
      catch(Exception e)
      {
        error("api response corrupted");
      }
      return null;
    }
    
    private int getRepoCount(String url)
    {
      String res = getRepoResponse(url);
      if(res != null){
        JSONObject json = new JSONObject(res);
        return (Integer)json.get("public_repos");
      }
      else
        warn("getRepoCount return 0");
      return 0;
    }

    private void apiIn(Gson gson, Portal[] data)
    {
      String TOPIC_URL = "https://api.github.com/users/bearddan2000/%s/topics";
      for(Portal obj: data)
      {
        String url = "https://api.github.com/users/bearddan2000/repos?per_page=100&page="+page;
        String res = getRepoResponse(url);
        if (res != null) {
          apiIn(res);
        }
        else {
          warn("api malformed");
        }  
      }
    }

    private void apiIn(String res){
      Gson gson = new Gson();      
      Portal[] repo = gson.fromJson(res, Portal[].class);
      return;
    }
    
    private void apiOut(){return;}
        
    public void api(int page)
    {
      String url = "https://api.github.com/users/bearddan2000/repos?per_page=100&page="+page;
      String res = getRepoResponse(url);
      if (res != null) {
        apiIn(res);
      }
      else {
        warn("api malformed");
      }
    }
    
    @Override
    public void run(String... args) throws Exception {

      JobParameters jobParameters =
            new JobParametersBuilder()
              .addLong("time", System.currentTimeMillis())
              .toJobParameters();

//      jobLauncher.run(jobA, jobParameters);
      System.out.println("JOB Execution completed!");
    }


    @Override
    public void info(String msg)
    {
      log.info(msg);
  
      System.out.println("[Sys out INFO] "+msg);
    }
  
    @Override
    public void warn(String msg)
    {
      log.warn(msg);
  
      System.out.println("[Sys out WARN] "+msg);
    }
  
    @Override
    public void error(String msg)
    {
      log.error(msg);
  
      System.err.println("[Sys out ERROR] "+msg);
    }
}
