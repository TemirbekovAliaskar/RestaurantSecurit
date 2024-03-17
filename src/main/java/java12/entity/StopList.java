package java12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "stop_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stopList_gen")
    @SequenceGenerator(name = "stopList_gen",sequenceName = "stopList_seq",allocationSize = 1)
    private Long id;
    private String reason;
    private LocalDateTime date;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST})
    private MenuItem menuItem;

    @PrePersist
    private void prePersist(){
        this.date = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.date = LocalDateTime.now();
    }

}
