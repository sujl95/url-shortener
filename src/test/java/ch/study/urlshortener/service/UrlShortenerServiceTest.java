package ch.study.urlshortener.service;

import static org.junit.jupiter.api.Assertions.*;

import ch.study.urlshortener.model.UrlInfo;
import ch.study.urlshortener.repository.UrlRepository;
import ch.study.urlshortener.utils.IdGeneratorUtil;
import ch.study.urlshortener.utils.UrlConverterUtil;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class UrlShortenerServiceTest {

  @Autowired
  private UrlRepository urlRepository;


  @ParameterizedTest
  @ValueSource(strings = "DRfTj45w")
  void findByShortUrl(String shortUrl) {
    UrlInfo urlInfo = urlRepository.findByShortUrl(shortUrl).orElseThrow(RuntimeException::new);
    String originUrl = urlInfo.getOriginUrl();
    assertNotNull(originUrl);
  }

  @ParameterizedTest
  @ValueSource(strings = "https://sujl95.tistory.com/")
  void create(String originUrl) throws URISyntaxException {
    if (isUrl(originUrl)) {
      String urlId = IdGeneratorUtil.generate(originUrl);
      UrlInfo urlInfo = urlRepository.save(UrlInfo.builder()
          .shortUrl(getShortUrl(urlId))
          .originUrl(originUrl)
          .urlId(urlId)
          .build());
      URI uri = new URI("/" + urlInfo.getShortUrl());
      assertNotNull(uri);
    }
  }

  private boolean isUrl(String originUrl) {
    return originUrl.startsWith("http://") || originUrl.startsWith("https://");
  }

  private String getShortUrl(String urlId) {
    int pos = 0;
    String shortUrl;
    while (true) {
      shortUrl = UrlConverterUtil.makeShortUrl(urlId, pos);
      UrlInfo findUrlInfo = urlRepository.findByShortUrl(shortUrl).orElse(null);
      if (findUrlInfo == null) {
        break;
      }
      pos++;
    }
    return shortUrl;
  }
}