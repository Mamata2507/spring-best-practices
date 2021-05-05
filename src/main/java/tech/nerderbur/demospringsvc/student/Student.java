package tech.nerderbur.demospringsvc.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "Student")
@Table(
        name = "student"
)
public class Student {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "email"
    )
    private String email;
    @Column(
            name = "date_of_birth"
    )
    private LocalDate dob;
    @Transient
    private Integer age;

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
}
