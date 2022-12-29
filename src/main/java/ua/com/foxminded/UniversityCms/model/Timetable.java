package ua.com.foxminded.UniversityCms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dateofday")
    private Date dateOfDay;

    @Column(name="pairsnumber")
    private int pairsNumber;

    @Column(name="auditory")
    private String auditory;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable=false)
    private Subject subjectId;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=false)
    private Group groupId;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable=false)
    private Teacher teacherId;

    @Override
    public String toString() {
        return '\n' + "Timetable{" +
                "id=" + id +
                ", dateOfDay=" + dateOfDay +
                ", pairsNumber=" + pairsNumber +
                ", auditory='" + auditory + '\'' +
                ", subjectId=" + subjectId +
                ", groupId=" + groupId +
                ", teacherId=" + teacherId +
                '}' + '\n';
    }
}
