package view;

import exception.CourseNotFoundException;
import model.Course;
import service.CouseService;

import java.util.List;
import java.util.Scanner;

public class CouseView {
    private CouseService couseService = new CouseService();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("==================================");
            System.out.println("1. Add new Course");
            System.out.println("2. List Courses");
            System.out.println("3. Find Course By ID");
            System.out.println("4. Find Course By Title");
            System.out.println("5. Remove Course By ID");
            System.out.println("0/99. Exit");
            System.out.println("==================================");
            System.out.print("[+] Insert option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    listCourses();
                    break;
                case "3":
                    findCourseById();
                    break;
                case "4":
                    findCourseByTitle();
                    break;
                case "5":
                    removeCourseById();
                    break;
                case "0":
                case "99":
                    System.out.println("[+] System closed.");
                    return;
                default:
                    System.out.println("[!] Invalid option. Please try again.");
            }
        }
    }

    private void addCourse() {
        System.out.print("[+] Insert course title: ");
        String title = scanner.nextLine();
        System.out.print("[+] Insert instructor names: ");
        String[] instructors = scanner.nextLine().split(",");
        System.out.print("[+] Insert course requirements: ");
        String[] requirements = scanner.nextLine().split(",");
        Course course = couseService.addCourse(title, instructors, requirements);
        printCourseTable(course);
    }

    private void listCourses() {
        List<Course> courses = couseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("[!] No courses available.");
        } else {
            printCourseTableHeader();
            courses.forEach(course -> {
                printCourseTableRow(course);
                System.out.println();
            });
            printCourseTableFooter();
        }
    }

    private void findCourseById() {
        System.out.print("[+] Insert course ID: ");
        String id = scanner.nextLine();
        try {
            Course course = couseService.findCourseById(id);
            printCourseTable(course);
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findCourseByTitle() {
        System.out.print("[+] Insert Course Title: ");
        String title = scanner.nextLine();
        List<Course> courses = couseService.findCourseByTitle(title);
        if (courses.isEmpty()) {
            System.out.println("[!] No courses found with title containing: " + title);
        } else {
            printCourseTableHeader();
            courses.forEach(course -> {
                printCourseTableRow(course);
                System.out.println();
            });
            printCourseTableFooter();
        }
    }

    private void removeCourseById() {
        System.out.print("[+] Insert course ID to remove: ");
        String id = scanner.nextLine();
        try {
            couseService.removeCourseById(id);
            System.out.println("[+] Course has been removed successfully.");
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printCourseTableHeader() {
        System.out.println("==========================================================================================================");
        System.out.printf("| %-36s | %-20s | %-30s | %-30s | %-20s |\n", "ID", "TITLE", "INSTRUCTORS", "REQUIREMENTS", "START DATE");
        System.out.println("==========================================================================================================");
    }

    private void printCourseTableRow(Course course) {
        System.out.printf("| %-5s | %-20s | %-30s | %-30s | %-20s |\n",
                course.getId().toString(),
                course.getTitle(),
                String.join(", ", course.getInstructorNames()),
                String.join(", ", course.getRequirements()),
                course.getStartDate().toString());
    }

    private void printCourseTableFooter() {
        System.out.println("==========================================================================================================");
    }

    private void printCourseTable(Course course) {
        printCourseTableHeader();
        printCourseTableRow(course);
        printCourseTableFooter();
    }

}
