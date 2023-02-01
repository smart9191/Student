package yt.uz.students.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.uz.students.Entity.Subjects;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects,Long> {

    Subjects findByName(String name);

}
