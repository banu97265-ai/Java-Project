package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String registerNumber;
    private String name;
    private String department;
    private int year;
    private double cgpa;
    private int aptitudeRank;

    // Typed list - important
    private List<ProgrammingPlatform> platforms;

    // Analytical Fields
    private double programmingScore;
    private double cgpaPerformance;
    private double aptitudePerformance;
    private double placementEligibility;
    private String placementReadiness;
    private String primaryFocusArea;
    private String personalizedSuggestion;

    public Student(String registerNumber, String name, String department,
                   int year, double cgpa, int aptitudeRank) {

        this.registerNumber = registerNumber;
        this.name = name;
        this.department = department;
        this.year = year;
        this.cgpa = cgpa;
        this.aptitudeRank = aptitudeRank;
        this.platforms = new ArrayList<>();
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public int getAptitudeRank() {
        return aptitudeRank;
    }

    public void setAptitudeRank(int aptitudeRank) {
        this.aptitudeRank = aptitudeRank;
    }

    // Typed getter
    public List<ProgrammingPlatform> getPlatforms() {
        return platforms;
    }

    // Typed setter
    public void setPlatforms(List<ProgrammingPlatform> platforms) {
        this.platforms = platforms;
    }

    public double getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(double programmingScore) {
        this.programmingScore = programmingScore;
    }

    public double getCgpaPerformance() {
        return cgpaPerformance;
    }

    public void setCgpaPerformance(double cgpaPerformance) {
        this.cgpaPerformance = cgpaPerformance;
    }

    public double getAptitudePerformance() {
        return aptitudePerformance;
    }

    public void setAptitudePerformance(double aptitudePerformance) {
        this.aptitudePerformance = aptitudePerformance;
    }

    public double getPlacementEligibility() {
        return placementEligibility;
    }

    public void setPlacementEligibility(double placementEligibility) {
        this.placementEligibility = placementEligibility;
    }

    public String getPlacementReadiness() {
        return placementReadiness;
    }

    public void setPlacementReadiness(String placementReadiness) {
        this.placementReadiness = placementReadiness;
    }

    public String getPrimaryFocusArea() {
        return primaryFocusArea;
    }

    public void setPrimaryFocusArea(String primaryFocusArea) {
        this.primaryFocusArea = primaryFocusArea;
    }

    public String getPersonalizedSuggestion() {
        return personalizedSuggestion;
    }

    public void setPersonalizedSuggestion(String personalizedSuggestion) {
        this.personalizedSuggestion = personalizedSuggestion;
    }
}