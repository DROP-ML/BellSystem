# Java Swing Automation Bell Application for Windows

![FlatLaf](https://img.shields.io/badge/FlatLaf-v3.1.1-blue)  
![Jackson Databind](https://img.shields.io/badge/Jackson--Databind-2.0.0-orange)  
![JLayer](https://img.shields.io/badge/JLayer-v1.0.1-brightgreen)  
![JSON Simple](https://img.shields.io/badge/JSON--Simple-1.1.1-yellow)

## Overview
The **Java Swing Automation Bell Application** is a standalone desktop application for Windows, developed in **NetBeans** using **JDK 11**. This project automates bell or alarm systems, making it ideal for environments that require scheduled alerts like schools, offices, or events. The application uses **FlatLaf** for a modern UI and **JLayer** for managing and saving schedule configurations in JSON format.

### Key Features:
- **Automated Bell Schedules**: Easily set and manage multiple bell or alarm timings.
- **Modern User Interface**: Built using **FlatLaf** for an appealing and responsive design.
- **Efficient JSON Handling**: Uses **Jackson Databind** and **JSON Simple** libraries for lightweight JSON file handling.
- **Cross-Platform Compatibility**: Designed for **Windows**, but the Java runtime allows execution on other platforms.
- **Lightweight & Secure**: Minimal memory usage and no unnecessary permissions.

---

## Libraries Used

1. **FlatLaf 3.1.1** - Modern Look and Feel for Swing applications, providing a flat, dark/light UI theme.
2. **Jackson Databind 2.0.0** - Used for reading and writing JSON, enabling complex data handling.
3. **JLayer 1.0.1** - Java library used to apply advanced filtering to Java Swing components.
4. **JSON Simple 1.1.1** - Lightweight library to process JSON data, providing easy methods for JSON creation and parsing.

---

## Installation

### Prerequisites
- **JDK 11**: The application is developed and tested on **Java Development Kit (JDK) 11**. Ensure you have it installed from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **NetBeans IDE**: The project is developed in **NetBeans**. You can download NetBeans from [here](https://netbeans.apache.org/download/).

### Step 1: Download and Build
- Clone or download this repository to your local machine.
- Open the project in **NetBeans**.

### Step 2: Configure Libraries
Ensure the following `.jar` files are included in your project's **Libraries** folder in NetBeans:
- `flatlaf-3.1.1.jar`
- `jackson-databind-2.0.0.jar`
- `jl1.0.1.jar` (for JLayer)
- `json-simple-1.1.1.jar`

To add these libraries:
1. Right-click your project in NetBeans.
2. Select **Properties > Libraries**.
3. Click **Add JAR/Folder** and navigate to where you've saved the `.jar` files.

### Step 3: Run the Application
After setting up the libraries, run the application:
1. Select **Run > Run Project** in NetBeans.
2. Follow on-screen instructions to set up bell schedules.

---

## How to Use

1. **Launch the Application**: Once the application is running, you can start setting alarms for different times of the day.
2. **JSON Configuration**: The application saves all bell schedules in JSON format for easy access and editing.
3. **Responsive Interface**: The modern, responsive interface ensures ease of use for all user types.

---

## Safe and Secure
The application is 100% secure, open-source, and free from malware. You can verify the source code in this repository.

---

## How to Contribute
1. Fork this repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

---

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

If you have any issues or need assistance, feel free to open an issue or contact us!
