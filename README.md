# 🏨 Hotel Management System – Java Console Application

## 📌 Project Overview

This **Java-based Hotel Management System** is a console application that simulates real-world hotel operations such as room booking, vacancy management, user authentication, and admin control. It features role-based access (Admin/User), room filtering, and persistent user storage using CSV files.

> ✅ Built for educational purposes and showcases Object-Oriented Programming concepts like Abstraction, Inheritance, Encapsulation, File I/O, and Collections.

---

## 🔧 Features

### ✅ User Features

* Login with username and password
* View available rooms by type (Single, Double, Suite, Any)
* Book a room based on availability
* Vacate a room after usage

### 🔐 Admin Features

* Login with **username**: `Admin` and **password**: `Andrewstark89#`
* Add new users and their credentials
* View all registered users
* Perform all room-related operations like booking, viewing, and vacating

---

## 💡 Technologies Used

| Technology   | Purpose                                                 |
| ------------ | ------------------------------------------------------- |
| Java         | Core programming language                               |
| OOP Concepts | Abstraction, Inheritance, Polymorphism                  |
| Java IO      | Read/write CSV file for user database                   |
| Collections  | `List<>` for room management, `HashMap<>` for user auth |
| Scanner      | Terminal input for interactive CLI                      |

---

## 🗂️ File Structure

```
day_21/
├── Hotel_management2.java        # Main driver and logic
├── users.csv                     # Stores registered users (username, password)
└── (Output screenshots directory optional)
```

---

## 📄 How It Works

### 🧱 Class Structure

| Class                                   | Responsibility                                   |
| --------------------------------------- | ------------------------------------------------ |
| `Room (abstract)`                       | Base for all room types, defines shared behavior |
| `SingleRoom`, `DoubleRoom`, `SuiteRoom` | Extend `Room` with specific prices and types     |
| `Customer`                              | Represents customer name and phone details       |
| `Hotel`                                 | Manages a list of rooms and booking logic        |
| `Hotel_management2`                     | Contains `main()` and admin/user menus           |

---

## 🔐 User Authentication

* Credentials are stored in a **CSV file** (`users.csv`) located at:

  ```
  D:/placement/day_21/users.csv
  ```
* Admin credentials are hardcoded:

  * **Username**: `Admin`
  * **Password**: `Andrewstark89#`

---

## 💾 Example `users.csv` Format

```csv
Username,Password
Admin,Andrewstark89#
john,pass123
alice,alice@99
```

---

## 💻 Sample Terminal Output

> 📌 Paste screenshots or terminal copies below:

```
--- Welcome to Hotel Management System ---
1. Login
2. Exit
Select option: 1
Enter username: john
Enter password: pass123

--- User Menu ---
1. Show Available Rooms
2. Book Room
3. Vacate Room
4. Logout
Enter your choice: 1
Enter room type (Single, Double, Suite, Any): Suite
Suite Room [#1, ₹3000.0, Booked: false]
Suite Room [#2, ₹3000.0, Booked: false]
...
```

📸 *(Add screenshots here)*

---

## 📚 Core Java Concepts Used

| Concept             | Usage Example                                    |
| ------------------- | ------------------------------------------------ |
| `abstract class`    | `Room` acts as a base for room types             |
| `inheritance`       | `SingleRoom` extends `Room`                      |
| `encapsulation`     | Private fields in `Customer`, access via getters |
| `HashMap`           | Stores user login credentials                    |
| `List<Room>`        | Manages all room objects dynamically             |
| `FileReader/Writer` | Reads & writes user data from/to CSV             |

---

## ⚙️ How to Run

### ✅ Prerequisites

* JDK installed (Java 8+)
* Any Java IDE (like IntelliJ, Eclipse) or terminal/command prompt

### 🚀 Steps

1. Place `Hotel_management2.java` in the folder `D:/placement/day_21`
2. Ensure a file named `users.csv` exists at the same location (create manually or allow the program to create on first add)
3. Compile and run the code:

   ```bash
   javac Hotel_management2.java
   java day_21.Hotel_management2
   ```

---

---

## ✍️ Author

**Andrew Jebaselvan P.**
*Java Developer & Artificial Intelligence And Data Science Student*
📁 Project Folder: `D:/placement/day_21`
📧 [andrew.jebaselvan@example.com](mailto:andrew.jebaselvan@example.com)

---
---

## 🧠 OOPs Concepts Summary

| OOP Concept     | Used In                                         | Benefit                                |
|----------------|--------------------------------------------------|----------------------------------------|
| **Abstraction**     | `Room` class (abstract methods)                 | Hides complexity, defines a template   |
| **Inheritance**     | Room types inherit from `Room`                  | Code reuse and specialization          |
| **Encapsulation**   | `Customer` class with private fields            | Data protection and controlled access  |
| **Polymorphism**    | Method overriding (`getRoomType()`), base refs  | Flexible method behavior               |
| **Classes/Objects** | All major components built as objects           | Modular, maintainable code structure   |

---


## 📌 License

Jeba Selvan Andrew P For academic purposes only 
