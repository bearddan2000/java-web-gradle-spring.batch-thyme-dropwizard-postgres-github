package example.model.keywords;

import lombok.*;

import javax.persistence.*;

import example.model.Portal;

@Entity
@Data
@Table(name="tbl_keyword_portal", catalog="db_app",
  schema="sch_keyword",
  uniqueConstraints = {@UniqueConstraint(columnNames = "fld_keyword_portal_id") })
public class KeywordPortal
{
  @Id
  @GeneratedValue
  @Column(name="fld_keyword_portal_id")
  private Long id;

  @Column(name="fld_keyword_id")
  private Long keywordId;

  @Column(name="fld_portal_id")
  private Long portalId;

  public void setKeyword(Keyword value){  keywordId = value.getId(); }

  public void setPortal(Portal value){  portalId = value.getId(); }
}
