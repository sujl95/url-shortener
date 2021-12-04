package ch.study.urlshortener.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IdGeneratorUtilTest {

  @ParameterizedTest
  @ValueSource(strings = "https://sujl95.tistory.com/")
  void generate(String originUrl) {
    // originUrl -> id
    UUID uuid = UUID.nameUUIDFromBytes((originUrl + Instant.now().toEpochMilli()).getBytes());
    ByteBuffer uuidBytes = ByteBuffer.wrap(new byte[16]);
    uuidBytes.putLong(uuid.getMostSignificantBits());
    uuidBytes.putLong(uuid.getLeastSignificantBits());
    String id = Base64.encodeBase64URLSafeString(uuidBytes.array());
    assertNotNull(id);
  }
}