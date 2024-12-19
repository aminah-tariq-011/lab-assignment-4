package com.example.bloodbankjava;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Userservice {

    private static final String USER_FILE = "C:\\Users\\amina\\Documents\\OOPS!!!\\PRACTICE\\bloodbankjava\\src\\main\\java\\com\\example\\bloodbankjava\\user.txt";
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String role = data[2];
                    String email = data[3];
                    String password = data[4];
                    users.add(new User(id, name, role, email, password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User login(String email, String password) {
        return null;   }
    public static void addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(user.getUserId() + "," + user.getName() + "," + user.getRole() + "," + user.getEmail() + "," + user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateUser(User user) {
        List<User> users = getAllUsers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User u : users) {
                if (u.getUserId() == user.getUserId()) {
                    writer.write(user.getUserId() + "," + user.getName() + "," + user.getRole() + "," + user.getEmail() + "," + user.getPassword());
                } else {
                    writer.write(u.getUserId() + "," + u.getName() + "," + u.getRole() + "," + u.getEmail() + "," + u.getPassword());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removeUser(User user) {
        List<User> users = getAllUsers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User u : users) {
                if (u.getUserId() != user.getUserId()) {
                    writer.write(u.getUserId() + "," + u.getName() + "," + u.getRole() + "," + u.getEmail() + "," + u.getPassword());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
