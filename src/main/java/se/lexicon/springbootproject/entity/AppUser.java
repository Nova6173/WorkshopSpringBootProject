package se.lexicon.springbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString


@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private String password;

    @Column
    @Setter
    private LocalDate regDate;

    @Setter
    @OneToOne
    @JoinColumn (name = "details_id")
    private Details userDetails;

    public AppUser(String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.regDate = LocalDate.now();
    }
}
