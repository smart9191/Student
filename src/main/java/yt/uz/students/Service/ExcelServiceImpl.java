package yt.uz.students.Service;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import yt.uz.students.Dto.StudentDto;
import yt.uz.students.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


@Service
public class ExcelServiceImpl extends ExcelUtils implements ExcelService {
    @Override
    public void StudentAllDataListExcel(List<StudentDto> studentDtoListPage, HttpServletResponse response) throws Exception {

        String fileName = "Student";
        getHeader(response,fileName);

        InputStream in = new ClassPathResource("/templates/student.xlsx").getInputStream();
        XSSFWorkbook wb1 = new XSSFWorkbook(in);

        wb  = new SXSSFWorkbook(wb1,1000);

        Sheet sheet = wb.getSheetAt(0);

        wb.setActiveSheet(0);
        sheet.setActiveCell(CellAddress.A1);

        Row row;

        CellStyle cellStyle =getCellStyle((short) 12);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        CellStyle cellStyle1 = getCellStyle((short) 12);
        cellStyle1.setAlignment(HorizontalAlignment.LEFT);


        int currentRow = 3;

        for(StudentDto studentDto : studentDtoListPage){

            row =sheet.createRow(currentRow++);

            int j = 0;

            createCellInt(row,j++,cellStyle,currentRow-3);
            createCellString(row,j++,cellStyle1,studentDto.getFullName());
            createCellDate(row,j++,cellStyle,studentDto.getBirthDate());
            createCellString(row,j++,cellStyle1,studentDto.getAddress());
            createCellString(row,j++,cellStyle,studentDto.getPhone());
            createCellString(row,j++,cellStyle1,studentDto.getImage());

        }

        studentDtoListPage.clear();
        OutputStream stream = response.getOutputStream();
        wb.write(stream);
        wb.close();
        wb.dispose();
        stream.flush();
        stream.close();
        in.close();


    }

    protected void getHeader(HttpServletResponse res, String fileName) {
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
    }
}
