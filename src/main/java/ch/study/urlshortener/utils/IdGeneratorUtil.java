package ch.study.urlshortener.utils;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class IdGeneratorUtil {

  public static String generate(String originUrl) {
    UUID uuid = UUID.nameUUIDFromBytes((originUrl + Instant.now().toEpochMilli()).getBytes());
    ByteBuffer uuidBytes = ByteBuffer.wrap(new byte[16]);
    uuidBytes.putLong(uuid.getMostSignificantBits());
    uuidBytes.putLong(uuid.getLeastSignificantBits());
    return Base64.encodeBase64URLSafeString(uuidBytes.array());
  }
}
