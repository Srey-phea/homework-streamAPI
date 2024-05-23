package repository;

import model.Course;

import java.util.*;
import java.util.stream.Collectors;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public Course addCourse(String title, String[] instructorNames, String[] requirements) {
        Course course = new Course(UUID.randomUUID(), title, instructorNames, requirements, new Date());
        courses.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> getCourseById(String id) {
        return courses.stream().filter(course -> course.getId().toString().equals(id)).findFirst();
    }

    public List<Course> getCourseByTitle(String title) {
        return courses.stream()
                .filter(course -> course.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean removeCourseById(String id) {
        return courses.removeIf(course -> course.getId().toString().equals(id));
    }
}
