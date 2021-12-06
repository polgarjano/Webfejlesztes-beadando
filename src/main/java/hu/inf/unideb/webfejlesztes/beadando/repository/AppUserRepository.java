package hu.inf.unideb.webfejlesztes.beadando.repository;

import hu.inf.unideb.webfejlesztes.beadando.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUserName(String userName);

    String getPasswordOnlyById(Long id);

    List<AppUser> findAll();
    boolean existsByUserName(String username);

}
