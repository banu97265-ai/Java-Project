package data;

import model.Student;
import util.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDataManager {

    public StudentDataManager() {
        ensureDataDirectoryExists();
    }

    private void ensureDataDirectoryExists() {
        File file = new File(Constants.DATA_FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public synchronized void saveStudents(List students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.DATA_FILE_PATH))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized List loadStudents() {
        File file = new File(Constants.DATA_FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Warning: Could not read existing data. Initializing clean storage: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}