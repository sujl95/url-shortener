package ch.study.urlshortener.dto;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

public class UrlDto {

  @AllArgsConstructor
  @Builder
  @Getter
  @NoArgsConstructor
  public static class UrlRequest {

    @NotBlank(message = "originUrl 을 입력해주세요")
    @URL(message = "정확한 url 형식을 입력해주세요. http://, https://로 시작해야합니다")
    private String originUrl;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  @NoArgsConstructor
  public static class UrlResponse {

    @NotBlank(message = "shortUrl 을 입력해주세요")
    private String shortUrl;
  }
}
