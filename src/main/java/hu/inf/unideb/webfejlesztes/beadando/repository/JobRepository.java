package hu.inf.unideb.webfejlesztes.beadando.repository;

import hu.inf.unideb.webfejlesztes.beadando.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job,Long> {

    List<Job> findAll();
}
