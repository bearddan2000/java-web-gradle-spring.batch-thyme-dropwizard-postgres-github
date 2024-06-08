package example;

import javax.persistence.*;

@Entity
@Table(name="vu_catagory", catalog="db_app", schema="sch_catagory")
@NamedQueries({
  @NamedQuery(name = "findAll",
    query = "from Catagory order by className, family")
})
public class Catagory
{
  @Id
  @Column(name="fld_worktable_id")
  private Long id;
  
  @Column(name="fld_family")
  private String family;
  
  @Column(name="fld_class")
  private String className;

  public Catagory(){}

  public Catagory(String family, String c ){}

  public Long getId(){return id;}

  public void setId(Long value){id = value;}

  public String getFamily(){ return family;}

  public void setFamily(String value){family = value;}

  public String getClassName(){ return className;}

  public void setClassName(String value){className = value;}

}
