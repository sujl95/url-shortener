package ch.study.urlshortener.repository;


import ch.study.urlshortener.model.Sample;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SampleRepository extends MongoRepository<Sample, String> {

}
