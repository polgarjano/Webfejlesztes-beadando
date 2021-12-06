package hu.inf.unideb.webfejlesztes.beadando.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Job {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String licensePlate;
    private String content;

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean finished) {
        isFinished = finished;
    }

    private boolean isFinished;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;
    @ManyToOne
    private AppUser user;



}
