package se.lexicon.springbootproject.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    @Setter
    private String name;

    @Column (nullable = false, length = 100, unique = true)
    @Setter
    private String email;

    @Column
    @Setter
    private LocalDate birthDate;

    public Details(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
}
