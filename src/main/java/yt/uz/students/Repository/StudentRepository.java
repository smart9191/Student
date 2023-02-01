package yt.uz.students.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.uz.students.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
