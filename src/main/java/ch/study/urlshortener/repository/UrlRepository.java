package ch.study.urlshortener.repository;

import ch.study.urlshortener.model.UrlInfo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlInfo, String> {

  Optional<UrlInfo> findByShortUrl(String shortUrl);

}
