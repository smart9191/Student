package yt.uz.students.Service;

import ch.qos.logback.core.encoder.EchoEncoder;
import yt.uz.students.Dto.StudentDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExcelService {

        void StudentAllDataListExcel(List<StudentDto> studentDtoListPage, HttpServletResponse response) throws Exception;

}
