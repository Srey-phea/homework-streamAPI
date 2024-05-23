package service;

import exception.CourseNotFoundException;
import model.Course;
import repository.CourseRepository;

import java.util.List;

public class CouseService {
    private CourseRepository repository = new CourseRepository();

    public Course addCourse(String title, String[] instructorNames, String[] requirements) {
        return repository.addCourse(title, instructorNames, requirements);
    }

    public List<Course> listCourses() {
        return repository.getAllCourses();
    }

    public Course findCourseById(String id) throws CourseNotFoundException {
        return repository.getCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException("[!] Not found course with ID: " + id));
    }

    public List<Course> findCourseByTitle(String title) {
        return repository.getCourseByTitle(title);
    }

    public void removeCourseById(String id) throws CourseNotFoundException {
        if (!repository.removeCourseById(id)) {
            throw new CourseNotFoundException("[!] Not found course with ID: " + id);
        }
    }
}
