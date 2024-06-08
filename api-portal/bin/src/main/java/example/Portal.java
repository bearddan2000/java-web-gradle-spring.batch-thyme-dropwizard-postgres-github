package example;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Table(name="vu_portal", catalog="db_app", schema="sch_portal")
@NamedQueries({
  @NamedQuery(name = "findAll",
    query = "from Portal order by word")
})
public class Portal
{
  @Id
  private Long id;

  private String name;

  private String description;

  @Column(name="build_json")
  private String buildJson;

  @Column(name="lang_json")
  private String langJson;

  @Column(name="platform_json")
  private String platformJson;

  @Column(name="tech_json")
  private String techJson;

  @Column(name="keyword_lst")
  private String[] keywords;

  @Column(name="class_lst")
  private String[] word;

  @Column(name="family_lst")
  private String[] family;

  @Column(name="topic_lst")
  private String[] topic;

  private String[] filterEmpty(String[] value){
    return Arrays.stream(value)
      .filter(e -> !e.trim().equals(""))
      .toArray(String[]::new);
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

  public void setKeywords(String[] value){
    keywords = value;
  }

  public String[] getKeywords(){ return keywords; }

  public void setWord(String[] value){
    word = filterEmpty(value);
  }

  public String[] getWord(){ return word; }

  public void setFamily(String[] value){
    family = filterEmpty(value);
  }

  public String[] getFamily(){ return family; }

  public void setTopic(String[] value){
    topic = filterEmpty(value);
  }

  public String[] getTopic(){ return topic; }

}
