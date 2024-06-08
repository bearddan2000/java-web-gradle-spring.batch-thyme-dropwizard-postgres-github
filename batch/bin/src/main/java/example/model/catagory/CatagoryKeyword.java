package example.model.catagory;

import lombok.*;

import javax.persistence.*;

import example.model.Filter;
import example.model.Portal;
import example.model.keywords.Keyword;

@Entity
@Data
@Table(name="tbl_catagory_keyword", catalog="db_app",
  schema="sch_catagory",
  uniqueConstraints = {@UniqueConstraint(columnNames = "fld_catagory_keyword_id") })
public class CatagoryKeyword
{
  @Id
  @GeneratedValue
  @Column(name="fld_catagory_keyword_id")
  private Long id;

  @Column(name="fld_catagory_id")
  private Long catagoryId;

  @Column(name="fld_keyword_id")
  private Long keywordId;

  @Column(name="fld_build_id")
  private Long buildId;

  @Column(name="fld_lang_id")
  private Long langId;

  @Column(name="fld_platform_id")
  private Long platformId;

  @Column(name="fld_tech_id")
  private Long techId;

  public void setPortal(Portal value){  
    buildId = value.getBuildId(); 
    langId = value.getLangId(); 
    platformId = value.getPlatformId(); 
    techId = value.getTechId(); 
  }

  public void setKeyword(Keyword value){  keywordId = value.getId(); }

  public void setCatagory(Catagory value){  catagoryId = value.getId(); }
}
