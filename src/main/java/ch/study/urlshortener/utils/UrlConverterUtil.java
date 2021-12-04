package ch.study.urlshortener.utils;

import com.devskiller.friendly_id.FriendlyId;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UrlConverterUtil {

  public static String makeShortUrl(String urlId, int pos) {
    UUID uuid = UUID.nameUUIDFromBytes(urlId.getBytes());
    return FriendlyId.toFriendlyId(uuid).substring(pos, pos + 8);
  }
}
