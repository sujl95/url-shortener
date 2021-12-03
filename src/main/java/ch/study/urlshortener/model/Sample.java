package ch.study.urlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sample")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sample {

  @Id
  private String id;
  private String name;

}
