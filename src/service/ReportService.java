package service;

import model.Student;

import java.util.List;

public class ReportService {

    private final StudentService studentService;

    public ReportService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void generateReportForDepartments(String[] departments) {

        for (String dept : departments) {

            String cleanDept = dept.trim();

            if (cleanDept.isEmpty()) {
                continue;
            }

            List<Student> students =
                    studentService.getStudentsByDepartment(cleanDept);

            if (students.isEmpty()) {

                System.out.println(
                        "\n[!] No records found for department: "
                        + cleanDept.toUpperCase()
                );

            } else {

                renderDepartmentTable(
                        cleanDept.toUpperCase(),
                        students
                );
            }
        }
    }

    public void generateAllDepartmentsReport() {

        List<String> departments =
                studentService.getUniqueDepartments();

        if (departments.isEmpty()) {

            System.out.println(
                    "\n[!] No student records currently exist in the system."
            );

            return;
        }

        for (String dept : departments) {

            List<Student> students =
                    studentService.getStudentsByDepartment(dept);

            renderDepartmentTable(dept, students);
        }
    }

    private void renderDepartmentTable(
            String department,
            List<Student> students) {

        System.out.println(
                "\n=========================================================================================================="
        );

        System.out.println(
                "                                  DEPARTMENT REPORT: "
                        + department
        );

        System.out.println(
                "=========================================================================================================="
        );

        System.out.printf(
                "%-12s | %-18s | %-4s | %-5s | %-12s | %-8s | %-12s | %-24s%n",
                "Register No",
                "Name",
                "Year",
                "CGPA",
                "Programming",
                "Aptitude",
                "Eligibility",
                "Focus Area"
        );

        System.out.println(
                "----------------------------------------------------------------------------------------------------------"
        );

        for (Student s : students) {

            System.out.printf(
                    "%-12s | %-18s | %-4d | %-5.2f | %-12.2f | %-8d | %-11.2f%% | %-24s%n",

                    s.getRegisterNumber(),
                    truncate(s.getName(), 18),
                    s.getYear(),
                    s.getCgpa(),
                    s.getProgrammingScore(),
                    s.getAptitudeRank(),
                    s.getPlacementEligibility(),
                    truncate(s.getPrimaryFocusArea(), 24)
            );
        }

        System.out.println(
                "=========================================================================================================="
        );

        System.out.println(
                "Total Students in "
                        + department
                        + ": "
                        + students.size()
        );
    }

    private String truncate(String text, int length) {

        if (text == null) {
            return "";
        }

        return text.length() > length
                ? text.substring(0, length - 3) + "..."
                : text;
    }
}