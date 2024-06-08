package example.model.api;

import java.util.*;
import java.util.stream.Collectors;

public class Portal
{
  private Long id;

  private String name;

  private String description;

  private String buildJson;

  private String langJson;

  private String platformJson;

  private String techJson;

  private List<String> keywords;

  private List<String> word;

  private List<String> family;

  private List<String> topic;

  private List<String> filterEmpty(List<String> value){
    return value.stream()
      .filter(e -> !e.trim().equals(""))
      .collect(Collectors.toList());
  }

  public Long getId(){return id;}

  public void setId(Long value){id = value;}

  public String getName(){ return name;}

  public void setName(String value){name = value;}

  public String getDescription(){ return description;}

  public void setDescription(String value){description = value;}

  public String getBuildJson(){ return buildJson;}

  public void setBuildJson(String value){buildJson = value;}

  public String getLangJson(){ return langJson;}

  public void setLangJson(String value){langJson = value;}

  public String getPlatformJson(){ return platformJson;}

  public void setPlatformJson(String value){platformJson = value;}

  public String getTechJson(){ return techJson;}

  public void setTechJson(String value){techJson = value;}

  public void setKeywords(List<String> value){
    keywords = filterEmpty(value);
  }

  public List<String> getKeywords(){ return keywords; }

  public void setWord(List<String> value){
    word = filterEmpty(value);
  }

  public List<String> getWord(){ return word; }

  public void setFamily(List<String> value){
    family = filterEmpty(value);
  }

  public List<String> getFamily(){ return family; }

  public void setTopic(List<String> value){
    topic = filterEmpty(value);
  }

  public List<String> getTopic(){ return topic; }

}
