package com.campus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;
    private String name;
    private String headOfDepartment;
    private String contactEmail;
    private String building;

    public Department() {
    }

    public Department(String code, String name, String headOfDepartment, String contactEmail, String building) {
        this.code = code;
        this.name = name;
        this.headOfDepartment = headOfDepartment;
        this.contactEmail = contactEmail;
        this.building = building;
    }

    public Department(int id, String code, String name, String headOfDepartment, String contactEmail, String building) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.headOfDepartment = headOfDepartment;
        this.contactEmail = contactEmail;
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
