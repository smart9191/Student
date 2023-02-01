package yt.uz.students.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "student_subject")
public class StudentSubject implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STD_SUBJ_SEQ"
    )
    @SequenceGenerator(
            name = "STD_SUBJ_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "subject_id")
    Subjects subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
