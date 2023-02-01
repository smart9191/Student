package yt.uz.students.Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yt.uz.students.Dto.StudentObj;
import yt.uz.students.Properties.FileStorageProperties;
import yt.uz.students.Repository.StudentRepository;
import yt.uz.students.Service.StudentDtoService;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Api("PDF fayl yaratish")
@RestController
public class PdfController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private StudentDtoService studentDtoService;

    @Autowired
    FileStorageProperties fileStorageProperties;

    @ApiOperation(value = "Student haqidagi ma'lumotni pdfga chiqarish apisi")
    @GetMapping("api/pdf/{id}")

    public ResponseEntity CreatePdf(@PathVariable Long id) throws Exception {

        List<StudentObj> list = studentDtoService.findAllStudentJoinInfo(id);

        StudentObj dto = list.get(0);

        StringBuilder stringBuilder = new StringBuilder();

        int j = 0;
        for (int i = 0; i < dto.getResult().size(); i++) {
            j++;
            if (j == dto.getResult().size()) {
                stringBuilder.append(dto.getResult().get(i).getName());
            } else {
                stringBuilder.append(dto.getResult().get(i).getName() + ",");
            }

            stringBuilder.toString();
        }


        ByteArrayOutputStream data = new ByteArrayOutputStream();
        Document document = new Document();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.pdf", dto.getFullName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

       try {

           PdfWriter.getInstance(document, data);
           document.open();

           Path path = Paths.get(fileStorageProperties.getUploadDir() + "/" + dto.getImage());
           Image img = Image.getInstance(path.toAbsolutePath().toString());

           Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
           Font newFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);

           Chunk fullNameStd = new Chunk(dto.getFullName(), font);
           Chunk addressStd = new Chunk(dto.getAddress(), font);
           Chunk phonesStd = new Chunk(String.valueOf(dto.getPhone()), font);
           Chunk birtDateStd = new Chunk(dto.getBirthDate(), font);
           Chunk imageStd = new Chunk(dto.getImage());
           Chunk subjectStd = new Chunk(String.valueOf(stringBuilder));

           PdfPCell r3 = new PdfPCell(new Paragraph(" "));
           PdfPCell fullName = new PdfPCell(new Paragraph("Familyasi va ismi", font));
           PdfPCell address = new PdfPCell(new Paragraph("Yashah manzili", font));
           PdfPCell phone = new PdfPCell(new Paragraph("Telefon raqami", font));
           PdfPCell birthDates = new PdfPCell(new Paragraph("Tug'ilgan kuni", font));
           PdfPCell image = new PdfPCell(new Paragraph("Rasm manzili", font));
           PdfPCell subjects = new PdfPCell(new Paragraph("Qiziqadigan fanlari", font));
           PdfPCell ass = new PdfPCell(new Paragraph("MA`LUMOTNOMA", newFont));
           ass.setHorizontalAlignment(Element.ALIGN_CENTER);

           PdfPTable table = new PdfPTable(5);
           PdfPTable table1 = new PdfPTable(2);
           PdfPTable table2 = new PdfPTable(1);

           PdfPCell cellFullName = new PdfPCell(new Phrase(fullNameStd));
           PdfPCell cellAddress = new PdfPCell(new Phrase(addressStd));
           PdfPCell cellPhone = new PdfPCell(new Phrase(phonesStd));
           PdfPCell cellBirtDate = new PdfPCell(new Phrase(birtDateStd));
           PdfPCell cellImage = new PdfPCell(new Phrase(imageStd));
           PdfPCell cellSubject = new PdfPCell(new Phrase(subjectStd));
           PdfPCell cellImg = new PdfPCell(img);
           img.scalePercent(25);

           cellImg.setBorder(Rectangle.NO_BORDER);
           r3.setBorder(Rectangle.NO_BORDER);
           ass.setBorder(Rectangle.NO_BORDER);

           table.addCell(r3);
           table.addCell(r3);
           table.addCell(r3);
           table.addCell(r3);
           table.addCell(cellImg);

           table2.addCell(ass);

           table1.addCell(fullName);
           table1.addCell(cellFullName);
           table1.addCell(address);
           table1.addCell(cellAddress);
           table1.addCell(phone);
           table1.addCell(cellPhone);
           table1.addCell(birthDates);
           table1.addCell(cellBirtDate);
           table1.addCell(image);
           table1.addCell(cellImage);
           table1.addCell(subjects);
           table1.addCell(cellSubject);

           document.add(table);
           document.add(new Phrase("\n"));
           document.add(table2);
           document.add(new Phrase("\n"));
           document.add(table1);

           document.close();


       }catch (Exception e){
           e.printStackTrace();
       }

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(data.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(data.toByteArray()));
    }
}