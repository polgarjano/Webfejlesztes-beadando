package hu.inf.unideb.webfejlesztes.beadando.DTO;

import hu.inf.unideb.webfejlesztes.beadando.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String name;

    @OneToMany
    private List<Job> jobList;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleNames = new ArrayList<>();

    @Override
    public String toString() {
        return this.userName ;
    }

    public void setIsAdmin(Boolean isAdmin) {

        String admin = "ROLE_ADMIN";
        if (isAdmin) {

            if (roleNames.contains(admin)) {
                return;
            }
            roleNames.add(admin);
        } else {
            roleNames.remove(admin);
        }
    }

    public boolean getIsAdmin() {
        String admin = "ROLE_ADMIN";
        return roleNames.contains(admin);
    }


    public void setIsUser(Boolean isAdmin) {

        String user = "ROLE_USER";
        if (isAdmin) {

            if (roleNames.contains(user)) {
                return;
            }
            roleNames.add(user);
        } else {
            roleNames.remove(user);
        }
    }

    public boolean getIsUser() {
        String user = "ROLE_USER";
        return roleNames.contains(user);
    }
}