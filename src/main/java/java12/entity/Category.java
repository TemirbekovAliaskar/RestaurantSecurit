package java12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_gen")
    @SequenceGenerator(name = "category_gen",sequenceName = "category_seq",allocationSize = 1)
    private Long id;
    private String name;
}
