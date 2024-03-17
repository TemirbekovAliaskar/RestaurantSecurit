package java12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cheque_gen")
    @SequenceGenerator(name = "cheque_gen",sequenceName = "cheque_seq",allocationSize = 1)
    private Long id;
    private int priceTotal;
    private  double service ;
    private ZonedDateTime createdAt;

    public double procient = 0.2 ;

    @ManyToOne
    private User user;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST})
    private List<MenuItem> menuItems = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        this.createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.createdAt = ZonedDateTime.now();
    }

}
