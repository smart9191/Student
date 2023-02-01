package yt.uz.students.Repository;

import org.springframework.stereotype.Repository;
import yt.uz.students.Dto.StudentDto;
import yt.uz.students.Dto.StudentDtoJoin;
import yt.uz.students.Dto.StudentObj;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentDto> findAllStudentData() {

        List<StudentDto> studentDto = new ArrayList<>();

        String sql = "select * from student";

        try {

            Query query = entityManager.createNativeQuery(sql,StudentDto.class);
            studentDto = query.getResultList();



        }catch (Exception e){
            e.printStackTrace();
        }


        return studentDto;
    }

    @Override
    public List<StudentObj> findAllStudentJoinInfo(Long id) {

        List<StudentObj> dtoJoinList = new ArrayList<>();


        List<StudentObj> studentObj = new ArrayList<>();

        String sql = "select sys_guid() as pkey,\n" +
                "       t.id,\n" +
                "       t.full_name,\n" +
                "       to_char(t.birth_date, 'dd.MM.yyyy') as birth_date,\n" +
                "       t.address,\n" +
                "       t.phone,\n" +
                "       t.image,\n" +
                "       t2.name as subject_name\n" +
                "  from STUDENT t\n" +
                " inner join STUDENT_SUBJECT t1\n" +
                "    on t.id = t1.student_id\n" +
                " inner join subject t2\n" +
                "    on t2.id = t1.subject_id\n" +
                " where t.id = :id\n" +
                " order by t.id\n";


        HashMap<Long, StudentObj> result = new HashMap<>();

             try {

                 Query query = entityManager.createNativeQuery(sql,StudentDtoJoin.class);
                 query.setParameter("id",id);

                for(Object row : query.getResultList()){
                    StudentDtoJoin obj = (StudentDtoJoin) row;
                    if(!result.containsKey(obj.getId())){

                        StudentObj stdObj  = new StudentObj();
                        stdObj.setId(obj.getId());
                        stdObj.setAddress(obj.getAddress());
                        stdObj.setBirthDate(obj.getBirthDate());
                        stdObj.setFullName(obj.getFullName());
                        stdObj.setImage(obj.getImage());
                        stdObj.setPhone(obj.getPhone());

                        result.put(obj.getId(),stdObj);
                    }
                        result.get(obj.getId()).addRow(obj.getSubjectName());

                        dtoJoinList = new ArrayList(result.values());

                   }


             }catch (Exception e){
                 e.printStackTrace();
             }


        return dtoJoinList;
    }


}
