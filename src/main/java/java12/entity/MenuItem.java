package java12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "menu_gen")
    @SequenceGenerator(name = "menu_gen",sequenceName = "menu_seq",allocationSize = 1)
    private Long id;
    private String name;
    private Long price;
    private String description;
    private boolean isVegetarian;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private Subcategory subcategories ;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private Restaurant restaurant;


}
