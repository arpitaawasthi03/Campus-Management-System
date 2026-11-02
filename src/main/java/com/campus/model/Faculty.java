package com.campus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String facultyId;
    private String name;
    private String email;
    private String department;
    private String designation;
    private String officeRoom;

    public Faculty() {
    }

    public Faculty(String facultyId, String name, String email, String department, String designation, String officeRoom) {
        this.facultyId = facultyId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.officeRoom = officeRoom;
    }

    public Faculty(int id, String facultyId, String name, String email, String department, String designation, String officeRoom) {
        this.id = id;
        this.facultyId = facultyId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.officeRoom = officeRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(String officeRoom) {
        this.officeRoom = officeRoom;
    }
}
