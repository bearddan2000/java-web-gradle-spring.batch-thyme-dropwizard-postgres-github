package example.model.api;

public class Filter
{
  private Long id;

  private String name;

  private String color;

  private Long filterType;

  public Filter(){}

  public Filter(Long id, String color, String name, Long typeFilter ){}

  public Long getId(){return id;}

  public String getColor() { return color; }

  public String getName(){ return name;}

  public Long getTypeFilter(){return filterType;}

  public void setId(Long value){id = value;}

  public void setColor(String value) { color = value; }

  public void setName(String value){ name = value;}

}
