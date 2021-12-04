package ch.study.urlshortener.controller;

import ch.study.urlshortener.dto.UrlDto;
import ch.study.urlshortener.model.UrlInfo;
import ch.study.urlshortener.service.UrlShortenerService;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class UrlShortenerController {

  private final UrlShortenerService urlShortenerService;

  // short URL 이 들어오면, origin URL 로 redirect 한다.
  @GetMapping("/{shortUrl}")
  public ResponseEntity<?> redirection(@PathVariable String shortUrl) {
    String originUrl = urlShortenerService.findByShortUrl(shortUrl);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(URI.create(originUrl));
    return ResponseEntity.status(HttpStatus.SEE_OTHER)
        .headers(httpHeaders)
        .build();
  }

  // origin URL에서 short URL로 만들어준다.
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UrlDto.UrlResponse createShortUrl(@RequestBody @Valid UrlDto.UrlRequest urlDto) {
    UrlInfo urlInfo = urlShortenerService.create(urlDto.getOriginUrl());
    return UrlDto.UrlResponse.builder().shortUrl(urlInfo.getShortUrl()).build();
  }
}
