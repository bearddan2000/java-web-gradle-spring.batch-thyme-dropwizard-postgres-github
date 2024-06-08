package example.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name="vu_filter", catalog="db_app",
  schema="sch_filter",
  uniqueConstraints = {@UniqueConstraint(columnNames = "fld_filter_id") })
public class Filter
{
  @Id
  @GeneratedValue
  @Column(name="fld_filter_id")
  private Long id;

  @Column(name="fld_name")
  private String name;

  @Column(name="fld_color")
  private String color;

  @Column(name="fld_type")
  private Long filter;
}
