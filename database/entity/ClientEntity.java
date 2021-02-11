package org.orgname.app.database.entity;

import org.orgname.app.Application;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

public class ClientEntity
{
    private int id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date birthday;
    private Date regDate;
    private String email;
    private String phone;
    private char genderCode;
    private Boolean testBoolean;
    private String photoPath;
    private ImageIcon icon;

    public ClientEntity(int id, String firstname, String lastname, String patronymic, Date birthday, Date regDate, String email, String phone, char genderCode, String photoPath) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.regDate = regDate;
        this.email = email;
        this.phone = phone;
        this.genderCode = genderCode;

        Random rand = new Random();
        this.testBoolean = rand.nextBoolean();
        this.photoPath = photoPath;
        String buffer;
        if(photoPath == null){
            buffer = "icon.jpg";
        }else{
           buffer = photoPath;
        }

        this.icon = new ImageIcon(Application.class.getClassLoader().getResource(buffer));
    }

    public ClientEntity(String firstname, String lastname, String patronymic, Date birthday, Date regDate, String email, String phone, char genderCode, String photoPath) {
        this(-1,firstname,lastname,patronymic,birthday,regDate,email,phone,genderCode,photoPath);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", regDate=" + regDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", genderCode=" + genderCode +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(char genderCode) {
        this.genderCode = genderCode;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Boolean getTestBoolean() {
        return testBoolean;
    }

    public void setTestBoolean(Boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
