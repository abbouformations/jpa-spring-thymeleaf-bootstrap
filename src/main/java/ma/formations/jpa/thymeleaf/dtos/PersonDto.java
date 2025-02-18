package ma.formations.jpa.thymeleaf.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
  private Long id;
  private String firstname;
  private String lastname;
  private Integer age;
  private boolean married;
}
