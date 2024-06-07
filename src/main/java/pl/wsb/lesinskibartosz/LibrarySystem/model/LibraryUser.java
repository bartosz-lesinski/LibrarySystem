package pl.wsb.lesinskibartosz.LibrarySystem.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "Library_User")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotBlank
    @Column(name = "street_name", nullable = false, length = 255)
    private String streetName;

    @NotNull
    @Positive
    @Column(name = "house_number", nullable = false, length = 3)
    private int houseNumber;

    @NotBlank
    @Column(name = "zip_Code", nullable = false, length = 7)
    private String zipCode;

    @NotBlank
    @Column(name = "town", nullable = false, length = 100)
    private String town;

    @NotBlank
    @Column(name = "phone_number", nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @NotBlank
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

}
