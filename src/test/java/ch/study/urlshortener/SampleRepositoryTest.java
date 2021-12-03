package ch.study.urlshortener;

import ch.study.urlshortener.model.Sample;
import ch.study.urlshortener.repository.SampleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class SampleRepositoryTest {

  @Autowired
  private SampleRepository sampleRepository;


  @Test
  void testMethod() {
    Sample sample = Sample.builder()
        .name("test")
        .build();
    Sample save = sampleRepository.save(sample);
    save.setName("test2");
    System.out.println("save = " + save);

  }

}
