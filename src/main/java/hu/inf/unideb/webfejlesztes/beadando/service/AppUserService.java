package hu.inf.unideb.webfejlesztes.beadando.service;

import hu.inf.unideb.webfejlesztes.beadando.model.AppUser;
import hu.inf.unideb.webfejlesztes.beadando.repository.AppUserRepository;
import hu.inf.unideb.webfejlesztes.beadando.repository.JobRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepository;
    private final JobRepository jobRepository;

    public AppUser getAppUserById(Long id) throws NotFoundException {

        Optional<AppUser> appUser = userRepository.findById(id);

        if (appUser.isEmpty()) {
            throw new NotFoundException("User does not exist with this ID");
        } else {
            return appUser.get();
        }

    }

    public List<AppUser> appUserList(){
        return userRepository.findAll();
    }

    public void saveUser(AppUser user) {

        if(user.getId() !=null){
            if(user.getEncryptedPassword() == null){
                user.setEncryptedPassword(userRepository.getPasswordOnlyById(user.getId()));
            }else {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setEncryptedPassword(encoder.encode(user.getEncryptedPassword()));
            }
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setEncryptedPassword(encoder.encode(user.getEncryptedPassword()));
        }

        userRepository.save(user);
    }

    public void deleteById(Long id) {

        var user = userRepository.findById(id);

        user.get().getJobList().stream().forEach(a-> a.setUser(null));
        user.get().getJobList().stream().forEach(a -> jobRepository.save(a));


        userRepository.deleteById(id);
    }

    public void addJobToUser(Long userId, Long jobId) {
        AppUser user = userRepository.findById(userId).get();

        var jobList =user.getJobList();
        jobList.add(jobRepository.findById(jobId).get());

        var job = jobRepository.findById(jobId).get();
        job.setUser(user);
        user.setJobList(jobList);
        saveUser(user);
        jobRepository.save(job);
    }
}
