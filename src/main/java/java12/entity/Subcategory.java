package java12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subcategories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "subcategory_gen")
    @SequenceGenerator(name = "subcategory_gen",sequenceName = "subcategory_seq",allocationSize = 1)
    private Long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private Category category;
}
