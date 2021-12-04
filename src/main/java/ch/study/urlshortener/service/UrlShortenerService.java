package ch.study.urlshortener.service;

import ch.study.urlshortener.model.UrlInfo;
import ch.study.urlshortener.repository.UrlRepository;
import ch.study.urlshortener.utils.IdGeneratorUtil;
import ch.study.urlshortener.utils.UrlConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

  private final UrlRepository urlRepository;

  @Transactional(readOnly = true)
  public String findByShortUrl(String shortUrl) {
    UrlInfo urlInfo = urlRepository.findByShortUrl(shortUrl).orElseThrow(RuntimeException::new);
    return urlInfo.getOriginUrl();
  }

  @Transactional
  public UrlInfo create(String originUrl) {
    String urlId = IdGeneratorUtil.generate(originUrl);
    return urlRepository.save(UrlInfo.builder()
        .shortUrl(getShortUrl(urlId))
        .originUrl(originUrl)
        .urlId(urlId)
        .build());
  }

  @Transactional(readOnly = true)
  public String getShortUrl(String urlId) {
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
