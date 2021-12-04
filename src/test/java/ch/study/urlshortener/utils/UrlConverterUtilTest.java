package ch.study.urlshortener.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import ch.study.urlshortener.model.UrlInfo;
import ch.study.urlshortener.repository.UrlRepository;
import com.devskiller.friendly_id.FriendlyId;
import java.util.UUID;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class UrlConverterUtilTest {

  @Autowired
  private UrlRepository urlRepository;

  @ParameterizedTest
  @ValueSource(strings = {"4SgJnJiqMPK42Q72GcXnzQ", "https://sujl95.tistory.com/"}) //
  void makeShortUrl(String urlId, String originUrl) {
    UUID uuid = UUID.nameUUIDFromBytes(urlId.getBytes());
    String friendlyId = FriendlyId.toFriendlyId(uuid);

    // shortUrl 생성 후 DB에 있는지 확인 없으면 생성 있으면 pos + 1
    int pos = 0;
    String shortUrl;
    while (true) {
      shortUrl = UrlConverterUtil.makeShortUrl(friendlyId, pos);;
      UrlInfo urlInfo = urlRepository.findByShortUrl(shortUrl).orElse(null);
      if (urlInfo == null) {
        // create
        break;
      }
      pos++;
    }
    assertNotNull(shortUrl);
  }
}