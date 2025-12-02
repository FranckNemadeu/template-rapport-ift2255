package com.diro.ift2255.tests;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.CourseDetails;
import com.diro.ift2255.service.CourseService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseServiceTest {

    private final CourseService service = new CourseService();

    // 1
    @Test
    public void searchCoursesBySigles_returnsCourses() {
        String sigles = "IFT2255,IFT2015";
        List<Course> courses = service.searchCourses(sigles, null);

        assertNotNull(courses);
        assertFalse(courses.isEmpty());

        List<String> ids = courses.stream().map(Course::getId).toList();
        assertTrue(ids.contains("IFT2255"));
        assertTrue(ids.contains("IFT2015"));
    }

    // 2
    @Test(expected = IllegalArgumentException.class)
    public void searchCoursesWithoutParams_throwsException() {
        service.searchCourses(null, null);
    }

    // 3
    @Test
    public void getCourseDetails_validId_returnsDetails() {
        CourseDetails details = service.getCourseDetails("IFT2255");

        assertNotNull(details);
        assertEquals("IFT2255", details.getId());
        assertNotNull(details.getName());
        assertFalse(details.getName().isBlank());
    }

    // 4
    @Test(expected = IllegalArgumentException.class)
    public void compareCourses_emptySigles_throwsException() {
        service.compareCourses("   ");
    }

    // 5
    @Test
    public void compareCourses_validCourses_returnsList() {
        List<CourseDetails> compared = service.compareCourses("IFT2255,IFT2015");

        assertNotNull(compared);
        assertEquals(2, compared.size());
    }

    // 6 (NOUVEAU) : getCourseDetails avec ID vide
    @Test(expected = IllegalArgumentException.class)
    public void getCourseDetails_blankId_throwsException() {
        service.getCourseDetails("   ");
    }

    // 7 (NOUVEAU) : recherche par nom
    @Test
    public void searchCoursesByName_returnsCourses() {
        // on cherche "logiciel" dans les noms de cours
        List<Course> courses = service.searchCourses(null, "logiciel");

        assertNotNull(courses);
        assertFalse("La recherche par nom ne doit pas être vide", courses.isEmpty());

        boolean containsLogiciel = courses.stream()
                .anyMatch(c -> c.getName() != null &&
                        c.getName().toLowerCase().contains("logiciel"));

        assertTrue("Au moins un cours doit contenir 'logiciel' dans le nom", containsLogiciel);
    }
}
