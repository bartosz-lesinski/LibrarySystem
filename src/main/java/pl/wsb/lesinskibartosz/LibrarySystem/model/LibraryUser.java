package pl.wsb.lesinskibartosz.LibrarySystem.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name = "LibraryUser")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column (name = "address", nullable = false, length = 255)
    private String address;

    @Column (name = "phone_number", nullable = false, length = 20, unique = true)
    private int phoneNumber;

    @Column (name = "email", nullable = false, length = 100, unique = true)
    private String email;
}
