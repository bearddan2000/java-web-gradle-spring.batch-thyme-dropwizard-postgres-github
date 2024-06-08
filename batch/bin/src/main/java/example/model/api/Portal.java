package example.model.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Portal
{
  String name;
  String description = "-";
  String clone_url;

}
