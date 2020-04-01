package com.lakehead.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Hibernate will create a table from this class
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String degreeType;

    private String department;

    private boolean feeStatus;

    private Float feesPendingAmount;

    private Float percentageAchieved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(boolean feeStatus) {
        this.feeStatus = feeStatus;
    }

    public Float getFeesPendingAmount() {
        return feesPendingAmount;
    }

    public void setFeesPendingAmount(Float feesPendingAmount) {
        this.feesPendingAmount = feesPendingAmount;
    }

    public Float getPercentageAchieved() {
        return percentageAchieved;
    }

    public void setPercentageAchieved(Float percentageAchieved) {
        this.percentageAchieved = percentageAchieved;
    }


}
