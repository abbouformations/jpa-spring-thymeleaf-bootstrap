package ma.formations.jpa.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
  @Id
  @GeneratedValue
  private Long id;
  private String firstname;
  private String lastname;
  private Integer age;
  private boolean married;
}
