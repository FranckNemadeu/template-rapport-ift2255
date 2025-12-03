package com.diro.ift2255.controller;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.CourseDetails;
import com.diro.ift2255.service.CourseService;
import com.diro.ift2255.util.HttpClientApi;
import io.javalin.http.Context;

import java.util.List;

public class CourseController {

    // Service partagé
    private static final CourseService courseService = new CourseService();

    // GET /courses-fake → pour test
    public static void searchCoursesFake(Context ctx) {
        List<Course> courses = List.of(
                new Course("IFT2255", "Génie logiciel", 3),
                new Course("IFT2015", "Structures de données", 3)
        );
        ctx.json(courses);
    }

    // ✅ GET /courses → vraie recherche avec Planifium
    // Exemples :
    //   /courses?sigles=IFT2255,IFT2015
    //   /courses?name=logiciel
    public static void searchCourses(Context ctx) {
        String sigles = ctx.queryParam("sigles");  // ex: "IFT2255,IFT2015"
        String name = ctx.queryParam("name");      // ex: "logiciel"

        try {
            List<Course> courses = courseService.searchCourses(sigles, name);
            ctx.json(courses);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (RuntimeException e) {
            ctx.status(500).result("Erreur interne lors de la recherche de cours.");
        }
    }

    // GET /courses-api-test → debug, JSON brut
    public static void coursesFromApiRaw(Context ctx) {
        HttpClientApi http = new HttpClientApi();

        String url = "https://planifium-api.onrender.com/api/v1/courses"
                + "?courses_sigle=IFT2255,IFT2015"
                + "&response_level=min";

        String body = http.get(url);

        ctx.result(body).contentType("application/json");
    }
    public static void getCourseDetails(Context ctx) {
        String id = ctx.pathParam("id");  // récupère :id dans /courses/:id

        try {
            CourseDetails details = courseService.getCourseDetails(id);
            ctx.json(details);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (RuntimeException e) {
            ctx.status(500).result("Erreur interne lors de la récupération du cours.");
        }
    }
    public static void compareCourses(Context ctx) {
        String sigles = ctx.queryParam("sigles");

        if (sigles == null || sigles.isBlank()) {
            ctx.status(400).result("Paramètre 'sigles' requis (ex: ?sigles=IFT2255,IFT2015)");
            return;
        }

        try {
            List<CourseDetails> compared = courseService.compareCourses(sigles);
            ctx.json(compared);
        } catch (Exception e) {
            ctx.status(500).result("Erreur lors de la comparaison des cours : " + e.getMessage());
        }
    }

}
