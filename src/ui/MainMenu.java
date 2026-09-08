package ui;

import service.ReportService;
import service.StudentService;
import util.InputUtil;

public class MainMenu {

    private final StudentMenu studentMenu;
    private final ReportService reportService;

    public MainMenu(StudentService studentService, ReportService reportService) {
        this.studentMenu = new StudentMenu(studentService);
        this.reportService = reportService;
    }

    public void display() {
        boolean running = true;

        while (running) {
            System.out.println("\n===============================================");
            System.out.println("          STUDENT PLACEMENT ANALYSIS           ");
            System.out.println("===============================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students / Generate Department Report");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Reset Department");
            System.out.println("7. Save and Exit");
            System.out.println("===============================================");

            int choice = InputUtil.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    studentMenu.handleAddStudent();
                    break;
                case 2:
                    handleReportMenu();
                    break;
                case 3:
                    studentMenu.handleSearchStudent();
                    break;
                case 4:
                    studentMenu.handleUpdateStudent();
                    break;
                case 5:
                    studentMenu.handleDeleteStudent();
                    break;
                case 6:
                    studentMenu.handleResetDepartment();
                    break;
                case 7:
                    System.out.println("\n[+] All records are synchronized. Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("[Error] Invalid option. Please select a number between 1 and 7.");
            }
        }
    }

    private void handleReportMenu() {
        System.out.println("\n-----------------------------------------------");
        System.out.println("     VIEW STUDENTS / GENERATE REPORT           ");
        System.out.println("-----------------------------------------------");
        System.out.println("1. View Specific Department(s)");
        System.out.println("2. View All Departments");
        System.out.println("-----------------------------------------------");

        int subChoice = InputUtil.readInt("Enter choice (1-2): ");
        if (subChoice == 1) {
            String input = InputUtil.readString("Enter department(s) separated by commas (e.g. CSBS,CSE,ECE): ");
            String[] depts = input.split(",");
            reportService.generateReportForDepartments(depts);
        } else if (subChoice == 2) {
            reportService.generateAllDepartmentsReport();
        } else {
            System.out.println("[Error] Invalid report option.");
        }
    }
}