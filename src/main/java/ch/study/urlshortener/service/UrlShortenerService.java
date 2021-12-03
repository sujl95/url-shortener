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

  private final IdGeneratorUtil idGenerator;
  private final UrlRepository urlRepository;
  private final UrlConverterUtil urlConverterUtil;

  public String findByShortUrl(String shortUrl) {
    urlRepository.findByShortUrl(shortUrl);

    return "";
  }

  public UrlInfo findOrCreate(String originUrl) {
    return urlRepository.findByOriginUrl(originUrl)
        .orElseGet(() -> create(originUrl));
  }

  @Transactional
  public UrlInfo create(String originUrl) {
    String urlId = idGenerator.generate(originUrl);

    String shortUrl = urlConverterUtil.makeShortUrl(urlId);

    UrlInfo urlInfo = UrlInfo.builder()
        .urlId(urlId)
        .shortUrl(shortUrl)
        .originUrl(originUrl)
        .build();

    return urlRepository.save(urlInfo);
  }
}
