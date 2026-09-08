package service;

import data.StudentDataManager;
import exception.InvalidInputException;
import exception.StudentNotFoundException;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private final List<Student> students;
    private final StudentDataManager dataManager;
    private final AnalysisService analysisService;

    public StudentService(StudentDataManager dataManager,
                          AnalysisService analysisService) {

        this.dataManager = dataManager;
        this.analysisService = analysisService;

        this.students = new ArrayList<>(dataManager.loadStudents());
    }

    public void addStudent(Student student) throws InvalidInputException {

        if (isRegisterNumberExists(student.getRegisterNumber())) {

            throw new InvalidInputException(
                    "A student with Register Number '"
                    + student.getRegisterNumber()
                    + "' already exists."
            );
        }

        analysisService.performFullAnalysis(student);

        students.add(student);

        dataManager.saveStudents(students);
    }

    public Student searchStudent(String name,
                                 String regNo,
                                 String department)
            throws StudentNotFoundException {

        return students.stream()
                .filter(s ->
                        s.getRegisterNumber().equalsIgnoreCase(regNo.trim())
                        && s.getName().equalsIgnoreCase(name.trim())
                        && s.getDepartment().equalsIgnoreCase(department.trim())
                )
                .findFirst()
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "No student found matching Name: '"
                                + name
                                + "', Reg No: '"
                                + regNo
                                + "', Dept: '"
                                + department
                                + "'."
                        )
                );
    }

    public void updateStudent(Student updatedStudent)
            throws StudentNotFoundException {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i)
                    .getRegisterNumber()
                    .equalsIgnoreCase(updatedStudent.getRegisterNumber())) {

                analysisService.performFullAnalysis(updatedStudent);

                students.set(i, updatedStudent);

                dataManager.saveStudents(students);

                return;
            }
        }

        throw new StudentNotFoundException(
                "Cannot update. Student not found with Register Number: "
                + updatedStudent.getRegisterNumber()
        );
    }

    public void deleteStudent(String name,
                              String regNo,
                              String department)
            throws StudentNotFoundException {

        Student student = searchStudent(name, regNo, department);

        students.remove(student);

        dataManager.saveStudents(students);
    }

    public int resetDepartment(String department) {

        List<Student> toRemove = students.stream()
                .filter(s ->
                        s.getDepartment()
                                .equalsIgnoreCase(department.trim())
                )
                .collect(Collectors.toList());

        if (toRemove.isEmpty()) {
            return 0;
        }

        students.removeAll(toRemove);

        dataManager.saveStudents(students);

        return toRemove.size();
    }

    public int getStudentCountByDepartment(String department) {

        return (int) students.stream()
                .filter(s ->
                        s.getDepartment()
                                .equalsIgnoreCase(department.trim())
                )
                .count();
    }

    public boolean isRegisterNumberExists(String regNo) {

        return students.stream()
                .anyMatch(s ->
                        s.getRegisterNumber()
                                .equalsIgnoreCase(regNo.trim())
                );
    }

    public List<Student> getAllStudents() {

        return new ArrayList<>(students);
    }

    public List<Student> getStudentsByDepartment(String department) {

        return students.stream()
                .filter(s ->
                        s.getDepartment()
                                .equalsIgnoreCase(department.trim())
                )
                .collect(Collectors.toList());
    }

    public List<String> getUniqueDepartments() {

        return students.stream()
                .map(Student::getDepartment)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}