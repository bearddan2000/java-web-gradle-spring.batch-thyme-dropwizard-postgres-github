package example.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.vladmihalcea.hibernate.type.array.IntArrayType;

import org.hibernate.annotations.*;
import java.util.*;

import example.model.keywords.Keyword;

@TypeDefs({
    @TypeDef(
        name = "integer-array",
        typeClass = IntArrayType.class
    )
})
@Entity
@Data
@Table(name="tbl_portal", catalog="db_app",
  schema="sch_portal",
  uniqueConstraints = {@UniqueConstraint(columnNames = "fld_portal_id") })
public class Portal
{

  @Id
  @GeneratedValue
  @Column(name="fld_portal_id")
  private Long id;

  @Column(name="fld_name")
  private String name;

  @Column(name="fld_description")
  private String description = "-";

  @Column(name="fld_build_id")
  private Long buildId = 1L;

  @Column(name="fld_lang_id")
  private Long langId = 15L;

  @Column(name="fld_platform_id")
  private Long platformId = 45L;

  @Column(name="fld_tech_id")
  private Long techId = 49L;

  @Type(type = "integer-array")
  @Column(
      name = "fld_keyword_id",
      columnDefinition = "int[]"
  )
  private Long[] keywordId = new Long[]{};

  public void setBuild(Filter value){  buildId = value.getId(); }

  public void setLang(Filter value){  langId = value.getId(); }

  public void setPlatform(Filter value){  platformId = value.getId(); }

  public void setTech(Filter value){  techId = value.getId(); }

  public void setWord(Keyword value){
    List<Long> lst = new ArrayList<Long>(Arrays.asList(keywordId));

    lst.add( value.getId()); 

    keywordId = lst.toArray(keywordId);
  }
}
