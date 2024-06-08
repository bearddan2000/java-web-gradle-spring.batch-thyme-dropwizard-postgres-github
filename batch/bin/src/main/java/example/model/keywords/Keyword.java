package example.model.keywords;

import lombok.*;

import javax.persistence.*;

import example.model.catagory.Catagory;

@Entity
@Data
@Table(name="tbl_keyword", catalog="db_app",
  schema="sch_keyword",
  uniqueConstraints = {@UniqueConstraint(columnNames = "fld_keyword_id") })
public class Keyword
{
  @Id
  @GeneratedValue
  @Column(name="fld_keyword_id")
  private Long id;

  @Column(name="fld_word")
  private String word;

  @Column(name="fld_catagory_id")
  private Long catagoryId = 1L;

  public void setCatagory(Catagory value){  catagoryId = value.getId(); }
}
