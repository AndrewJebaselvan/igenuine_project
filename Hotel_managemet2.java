import java.io.*;
import java.util.*;

abstract class Room {
    protected int roomNumber;
    protected boolean isBooked;
    protected double price;
    protected Customer customer;

    public Room(int roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.isBooked = false;
        this.customer = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom(Customer customer) {
        isBooked = true;
        this.customer = customer;
    }

    public void vacateRoom() {
        isBooked = false;
        customer = null;
    }

    public abstract String getRoomType();

    public String toString() {
        return getRoomType() + " Room [#" + roomNumber + ", ₹" + price + ", Booked: " + isBooked + "]" +
                (customer != null ? ", Customer: " + customer.getName() : "");
    }
}

class SingleRoom extends Room {
    public SingleRoom(int roomNumber) {
        super(roomNumber, 1000);
    }
    public String getRoomType() {
        return "Single";
    }
}

class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber) {
        super(roomNumber, 1800);
    }
    public String getRoomType() {
        return "Double";
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber) {
        super(roomNumber, 3000);
    }
    public String getRoomType() {
        return "Suite";
    }
}

class Customer {
    private String name;
    private String phone;
    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
}

class Hotel {
    private List<Room> rooms = new ArrayList<>();
    public Hotel() {
        for (int i = 1; i <= 15; i++) rooms.add(new SuiteRoom(i));
        for (int i = 16; i <= 25; i++) rooms.add(new DoubleRoom(i));
        for (int i = 26; i <= 30; i++) rooms.add(new SingleRoom(i));
    }

    public void showAvailableRooms(String roomType) {
        boolean found = false;
        for (Room r : rooms) {
            boolean match = roomType.equalsIgnoreCase("Any") || r.getRoomType().equalsIgnoreCase(roomType);
            if (!r.isBooked() && match) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms of type: " + roomType);
        }
    }

    public boolean bookRoomByType(String type, Customer customer) {
        for (Room r : rooms) {
            if (!r.isBooked() && r.getRoomType().equalsIgnoreCase(type)) {
                r.bookRoom(customer);
                System.out.println("Room booked successfully: " + r);
                return true;
            }
        }
        System.out.println("No available rooms of type: " + type);
        return false;
    }

    public boolean vacateRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber && r.isBooked()) {
                r.vacateRoom();
                System.out.println("Room vacated successfully: " + r);
                return true;
            }
        }
        System.out.println("Invalid room number or room not booked.");
        return false;
    }
}

public class Hotel_managemet2 {
    private static final String FILE_PATH = "/workspaces/igenuine_project/users.csv";
    private static Map<String, String> userDatabase = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) {
        loadUsersFromFile();

        while (true) {
            System.out.println("\n--- Welcome to Hotel Management System ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Select option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1: login(); break;
                case 2: System.out.println("Thank you for using the system."); return;
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void loadUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userDatabase.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("User file not found or unreadable.");
        }
    }

    private static void saveUserToFile(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.newLine();
            bw.write(username + "," + password);
        } catch (IOException e) {
            System.out.println("Failed to save user to file.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            if (username.equals("Admin")) {
                adminMenu();
            } else {
                roomMenu(username);
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Room Management");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new username: ");
                    String newUser = sc.nextLine();
                    if (userDatabase.containsKey(newUser)) {
                        System.out.println("User already exists.");
                    } else {
                        System.out.print("Enter password: ");
                        String newPass = sc.nextLine();
                        userDatabase.put(newUser, newPass);
                        saveUserToFile(newUser, newPass);
                        System.out.println("User added.");
                    }
                    break;
                case 2:
                    System.out.println("Registered Users:");
                    for (String user : userDatabase.keySet()) {
                        System.out.println("- " + user);
                    }
                    break;
                case 3:
                    roomMenu("Admin");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void roomMenu(String username) {
        while (true) {
            System.out.println("\n--- " + (username.equals("Admin") ? "Admin Room Management" : "User Menu") + " ---");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Vacate Room");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (Single, Double, Suite, Any): ");
                    String viewType = sc.nextLine();
                    hotel.showAvailableRooms(viewType);
                    break;
                case 2:
                    System.out.print("Enter room type to book (Single, Double, Suite): ");
                    String bookType = sc.nextLine();
                    System.out.print("Enter your phone number: ");
                    String phone = sc.nextLine();
                    hotel.bookRoomByType(bookType, new Customer(username, phone));
                    break;
                case 3:
                    System.out.print("Enter room number to vacate: ");
                    int roomNum = sc.nextInt();
                    sc.nextLine();
                    hotel.vacateRoom(roomNum);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
