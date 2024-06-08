package example;

import javax.persistence.*;

@Entity
@Table(name="vu_filter", catalog="db_app", schema="sch_filter")
@NamedQueries({
  @NamedQuery(name = "findByType",
    query = "from Filter where filterType = :filterType order by name")
})
public class Filter
{
  @Id
  @Column(name="fld_filter_id")
  private Long id;

  @Column(name="fld_name")
  private String name;

  @Column(name="fld_color")
  private String color;

  @Column(name="fld_type")
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
