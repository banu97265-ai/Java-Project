package ui;

import exception.InvalidInputException;
import exception.StudentNotFoundException;
import model.ProgrammingPlatform;
import model.Student;
import service.StudentService;
import util.InputUtil;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentMenu {

    private final StudentService studentService;

    public StudentMenu(StudentService studentService) {
        this.studentService = studentService;
    }

    public void handleAddStudent() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("                ADD NEW STUDENT                ");
        System.out.println("-----------------------------------------------");

        try {
            String regNo = InputUtil.readString("Enter Register Number / Student ID: ");
            if (studentService.isRegisterNumberExists(regNo)) {
                System.out.println("[Error] Student with Register Number '" + regNo + "' already exists!");
                return;
            }

            String name = InputUtil.readString("Enter Student Name: ");
            String department = InputUtil.readString("Enter Department (e.g., CSBS, CSE, ECE): ");
            
            int year = InputUtil.readInt("Enter Year of Study (1-4): ");
            ValidationUtil.validateYear(year);

            double cgpa = InputUtil.readDouble("Enter CGPA (0.0 - 10.0): ");
            ValidationUtil.validateCgpa(cgpa);

            int aptitudeRank = InputUtil.readInt("Enter Aptitude Rank: ");
            ValidationUtil.validatePositiveInteger("Aptitude Rank", aptitudeRank);

            int numPlatforms = InputUtil.readInt("Enter Number of Competitive Programming Platforms Used: ");
            ValidationUtil.validatePositiveInteger("Number of Platforms", numPlatforms);

            List platformList = new ArrayList<>();
            for (int i = 1; i <= numPlatforms; i++) {
                System.out.println("\n--- Platform " + i + " Details ---");
                String pName = InputUtil.readString("Platform Name (e.g., LeetCode, CodeChef): ");
                
                int rank = InputUtil.readInt("Platform Rank: ");
                ValidationUtil.validatePositiveInteger("Platform Rank", rank);

                double score = InputUtil.readDouble("Platform Score / Rating: ");
                ValidationUtil.validateNonNegativeDouble("Platform Score", score);

                int solved = InputUtil.readInt("Problems Solved: ");
                ValidationUtil.validateNonNegativeInteger("Problems Solved", solved);

                platformList.add(new ProgrammingPlatform(pName, rank, score, solved));
            }

            Student student = new Student(regNo, name, department, year, cgpa, aptitudeRank);
            student.setPlatforms(platformList);

            studentService.addStudent(student);

            System.out.println("\n[+] Student Added Successfully!");
            displayStudentSummary(student);

        } catch (InvalidInputException e) {
            System.out.println("\n[Validation Error] " + e.getMessage());
        }
    }

    public void handleSearchStudent() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("                SEARCH STUDENT                 ");
        System.out.println("-----------------------------------------------");

        String name = InputUtil.readString("Enter Name: ");
        String regNo = InputUtil.readString("Enter Register Number: ");
        String department = InputUtil.readString("Enter Department: ");

        try {
            Student student = studentService.searchStudent(name, regNo, department);
            displayStudentSummary(student);
        } catch (StudentNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    public void handleUpdateStudent() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("                UPDATE STUDENT                 ");
        System.out.println("-----------------------------------------------");

        String name = InputUtil.readString("Enter Current Name: ");
        String regNo = InputUtil.readString("Enter Register Number: ");
        String department = InputUtil.readString("Enter Current Department: ");

        try {
            Student existing = studentService.searchStudent(name, regNo, department);
            System.out.println("\nStudent record found. Enter updated information:");

            String newName = InputUtil.readString("Enter Updated Name: ");
            String newDept = InputUtil.readString("Enter Updated Department: ");
            
            int newYear = InputUtil.readInt("Enter Updated Year (1-4): ");
            ValidationUtil.validateYear(newYear);

            double newCgpa = InputUtil.readDouble("Enter Updated CGPA (0.0 - 10.0): ");
            ValidationUtil.validateCgpa(newCgpa);

            int newAptRank = InputUtil.readInt("Enter Updated Aptitude Rank: ");
            ValidationUtil.validatePositiveInteger("Aptitude Rank", newAptRank);

            int numPlatforms = InputUtil.readInt("Enter Number of Platforms Used: ");
            ValidationUtil.validatePositiveInteger("Number of Platforms", numPlatforms);

            List platformList = new ArrayList<>();
            for (int i = 1; i <= numPlatforms; i++) {
                System.out.println("\n--- Platform " + i + " Details ---");
                String pName = InputUtil.readString("Platform Name: ");
                int rank = InputUtil.readInt("Platform Rank: ");
                ValidationUtil.validatePositiveInteger("Platform Rank", rank);

                double score = InputUtil.readDouble("Platform Score / Rating: ");
                ValidationUtil.validateNonNegativeDouble("Platform Score", score);

                int solved = InputUtil.readInt("Problems Solved: ");
                ValidationUtil.validateNonNegativeInteger("Problems Solved", solved);

                platformList.add(new ProgrammingPlatform(pName, rank, score, solved));
            }

            existing.setName(newName);
            existing.setDepartment(newDept);
            existing.setYear(newYear);
            existing.setCgpa(newCgpa);
            existing.setAptitudeRank(newAptRank);
            existing.setPlatforms(platformList);

            studentService.updateStudent(existing);

            System.out.println("\n[+] Student Record Updated Successfully!");
            displayStudentSummary(existing);

        } catch (StudentNotFoundException | InvalidInputException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    public void handleDeleteStudent() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("                DELETE STUDENT                 ");
        System.out.println("-----------------------------------------------");

        String name = InputUtil.readString("Enter Name: ");
        String regNo = InputUtil.readString("Enter Register Number: ");
        String department = InputUtil.readString("Enter Department: ");

        try {
            // Verify student exists first
            studentService.searchStudent(name, regNo, department);

            boolean confirm = InputUtil.readConfirmation("Are you sure you want to delete this student? (Y/N): ");
            if (confirm) {
                studentService.deleteStudent(name, regNo, department);
                System.out.println("[+] Student record deleted successfully.");
            } else {
                System.out.println("[*] Deletion cancelled.");
            }
        } catch (StudentNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    public void handleResetDepartment() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("               RESET DEPARTMENT                ");
        System.out.println("-----------------------------------------------");

        String dept = InputUtil.readString("Enter department to reset: ");
        int count = studentService.getStudentCountByDepartment(dept);

        if (count == 0) {
            System.out.println("\n[!] No records found for department: " + dept.toUpperCase());
            return;
        }

        System.out.println("Found " + count + " student record(s) in " + dept.toUpperCase() + ".");
        boolean confirm = InputUtil.readConfirmation("Are you sure you want to delete ALL " + dept.toUpperCase() + " student records? (Y/N): ");
        if (confirm) {
            int deleted = studentService.resetDepartment(dept);
            System.out.println("[+] Successfully deleted " + deleted + " student records from " + dept.toUpperCase() + ".");
        } else {
            System.out.println("[*] Department reset cancelled.");
        }
    }

    public void displayStudentSummary(Student s) {
        System.out.println("\n=======================================================");
        System.out.println("                   STUDENT PROFILE                     ");
        System.out.println("=======================================================");
        System.out.println("Register Number      : " + s.getRegisterNumber());
        System.out.println("Name                 : " + s.getName());
        System.out.println("Department           : " + s.getDepartment());
        System.out.println("Year                 : " + s.getYear());
        System.out.println("CGPA                 : " + s.getCgpa());
        System.out.println("Aptitude Rank        : " + s.getAptitudeRank());
        System.out.println("\n--- Competitive Programming Platforms ---");
        if (s.getPlatforms().isEmpty()) {
            System.out.println("No platforms recorded.");
        } else {
            for (ProgrammingPlatform p : s.getPlatforms()) {
                System.out.printf(" - %-12s | Rank: %-7d | Score: %-7.2f | Solved: %-4d | Metric: %.2f%n",
                        p.getPlatformName(), p.getRank(), p.getScore(), p.getProblemsSolved(), p.getCalculatedPlatformScore());
            }
        }
        System.out.println("\n--- Analytical Evaluation ---");
        System.out.printf("Calculated Programming Score  : %.2f / 100%n", s.getProgrammingScore());
        System.out.printf("Placement Eligibility Score   : %.2f%%%n", s.getPlacementEligibility());
        System.out.println("Placement Readiness Status    : " + s.getPlacementReadiness());
        System.out.println("Primary Area of Improvement   : " + s.getPrimaryFocusArea());
        System.out.println("Personalized Recommendation   : " + s.getPersonalizedSuggestion());
        System.out.println("=======================================================");
    }
}