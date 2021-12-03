package ch.study.urlshortener.controller;

import ch.study.urlshortener.dto.UrlDto;
import ch.study.urlshortener.service.UrlShortenerService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public String redirection(@PathVariable String shortUrl) {




    return "";
  }

  // origin URL에서 short URL로 만들어준다.
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createShortUrl(@RequestBody @Valid UrlDto urlDto) {



  }
}
