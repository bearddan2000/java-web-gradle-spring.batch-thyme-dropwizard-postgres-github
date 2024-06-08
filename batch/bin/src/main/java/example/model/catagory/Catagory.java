package example.model.catagory;

import lombok.*;

import javax.persistence.*;

import example.model.keywords.Keyword;

@Entity
@Data
@Table(name="tbl_catagory", catalog="db_app",
  schema="sch_catagory",
  uniqueConstraints = {@UniqueConstraint(columnNames = "fld_catagory_id") })
public class Catagory
{
  @Id
  @GeneratedValue
  @Column(name="fld_catagory_id")
  private Long id;

  @Column(name="fld_word")
  private String word;

  @Column(name="fld_keyword_id")
  private Long keywordId;

  public void setKeyword(Keyword value) {
    keywordId = value.getId();
  }
}
