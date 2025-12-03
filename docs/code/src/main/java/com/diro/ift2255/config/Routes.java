package com.diro.ift2255.config;

import com.diro.ift2255.controller.CourseController;
import io.javalin.Javalin;

public class Routes {

    public static void configure(Javalin app) {
        app.get("/ping", ctx -> ctx.result("pong"));

        app.get("/courses-fake", CourseController::searchCoursesFake);
        app.get("/courses", CourseController::searchCourses);
        app.get("/courses-api-test", CourseController::coursesFromApiRaw);

        // ✅ Syntaxe correcte en Javalin 6
        app.get("/courses/{id}", CourseController::getCourseDetails);
        app.get("/compare", CourseController::compareCourses);
    }
}
