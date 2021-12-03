package ch.study.urlshortener.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urlInfo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlInfo {

  @Id
  private String id;

  @NotBlank
  private String urlId;

  @NotBlank
  private String shortUrl;

  @NotBlank
  private String originUrl;
}
