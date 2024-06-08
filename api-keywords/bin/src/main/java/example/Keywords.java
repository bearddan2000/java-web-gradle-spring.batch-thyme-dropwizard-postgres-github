package example;

import javax.persistence.*;

@Entity
@Table(name="vu_keywords", catalog="db_app", schema="sch_keywords")
@NamedQueries({
  @NamedQuery(name = "findAll",
    query = "from Keywords order by word")
})
public class Keywords
{
  @Id
  private Long id;

  private String word;

  private Long count;

  public Keywords(){}

  public Keywords(String word, Long count ){}

  public Long getId(){return id;}

  public void setId(Long value){id = value;}

  public String getWord(){ return word;}

  public Long getCount(){return count;}

  public void setWord(String value){word = value;}

  public void setCount(Long value){count = value;}

}
