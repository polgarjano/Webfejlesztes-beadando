package hu.inf.unideb.webfejlesztes.beadando.service;

import hu.inf.unideb.webfejlesztes.beadando.model.AppUser;
import hu.inf.unideb.webfejlesztes.beadando.model.Job;
import hu.inf.unideb.webfejlesztes.beadando.repository.AppUserRepository;
import hu.inf.unideb.webfejlesztes.beadando.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;


    public List<Job> listAll() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
       return jobRepository.findById(id).get();
    }

    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }
}
