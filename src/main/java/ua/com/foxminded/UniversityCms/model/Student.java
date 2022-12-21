package ua.com.foxminded.UniversityCms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "studticketnumber")
    private long studTicketNumber;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=false)
    private Group group;
}
