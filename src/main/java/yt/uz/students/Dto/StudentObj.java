package yt.uz.students.Dto;

import java.util.ArrayList;
import java.util.List;


public class StudentObj {


    private Long id;

    private String fullName;

    private String birthDate;

    private String address;

    private String phone;

    private String image;
    List<Row> result = new ArrayList<>();

    public void addRow(String a) {
        result.add(new Row(a));
    }


   public class Row {
        private String name;

        public Row(String a) {
            name = a;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Row> getResult() {
        return result;
    }

    public void setResult(List<Row> result) {
        this.result = result;
    }
}
