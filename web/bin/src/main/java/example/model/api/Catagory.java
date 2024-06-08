package example.model.api;

public class Catagory
{
  private Long id;

  private String word, family, topic;

  public Catagory(){}

  public Catagory(String word, String family, String topic ){}

  public Long getId(){return id;}

  public void setId(Long value){id = value;}

  public String getWord(){ return word;}

  public void setWord(String value){word = value;}

  public String getFamily(){ return family;}

  public void setFamily(String value){family = value;}

  public String getTopic(){ return topic;}

  public void setTopic(String value){topic = value;}

}
