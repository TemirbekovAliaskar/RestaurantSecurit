package java12.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen",sequenceName = "restaurant_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private int servicePercent;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
    List<User> users = new ArrayList<>();


}
