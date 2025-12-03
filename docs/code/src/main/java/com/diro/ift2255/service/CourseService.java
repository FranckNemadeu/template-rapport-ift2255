package com.diro.ift2255.service;

import com.diro.ift2255.model.Course;
import com.diro.ift2255.model.CourseDetails;
import com.diro.ift2255.util.HttpClientApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private static final String BASE_URL = "https://planifium-api.onrender.com/api/v1";

    private final HttpClientApi http;
    private final ObjectMapper mapper;

    public CourseService() {
        this.http = new HttpClientApi();
        this.mapper = new ObjectMapper();
    }

    /**
     * Recherche de cours.
     * - si "sigles" non null → utilise courses_sigle=...
     * - sinon si "name" non null → utilise name=...
     */
    public List<Course> searchCourses(String sigles, String name) {
        if ((sigles == null || sigles.isBlank()) &&
                (name == null || name.isBlank())) {
            throw new IllegalArgumentException("Vous devez fournir 'sigles' ou 'name'.");
        }

        try {
            StringBuilder url = new StringBuilder(BASE_URL + "/courses?response_level=min");

            if (sigles != null && !sigles.isBlank()) {
                String encoded = URLEncoder.encode(sigles.trim(), StandardCharsets.UTF_8);
                url.append("&courses_sigle=").append(encoded);
            } else if (name != null && !name.isBlank()) {
                String encoded = URLEncoder.encode(name.trim(), StandardCharsets.UTF_8);
                url.append("&name=").append(encoded);
            }

            String json = http.get(url.toString());
            JsonNode root = mapper.readTree(json);

            List<Course> courses = new ArrayList<>();

            // root est un tableau JSON : [ {...}, {...}, ... ]
            for (JsonNode node : root) {
                // Champs principaux (d'après l'API Planifium)
                String id = node.hasNonNull("id") ? node.get("id").asText() : "";
                String courseName = node.hasNonNull("name") ? node.get("name").asText() : "";
                int credits = node.hasNonNull("credits") ? node.get("credits").asInt() : 0;

                courses.add(new Course(id, courseName, credits));
            }

            return courses;

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'appel à l'API Planifium", e);
        }
    }

    /**
     * Détails d'un cours : GET /courses/{course_id}
     */
    public CourseDetails getCourseDetails(String courseId) {
        if (courseId == null || courseId.isBlank()) {
            throw new IllegalArgumentException("courseId ne peut pas être vide.");
        }

        try {
            String url = BASE_URL + "/courses/" + courseId;

            String json = http.get(url);
            JsonNode node = mapper.readTree(json);

            // Champs principaux
            String id = node.hasNonNull("id") ? node.get("id").asText() : "";
            String name = node.hasNonNull("name") ? node.get("name").asText() : "";
            int credits = node.hasNonNull("credits") ? node.get("credits").asInt() : 0;
            String description = node.hasNonNull("description") ? node.get("description").asText() : "";

            // Prérequis (liste de sigles)
            List<String> prereqCourses = new ArrayList<>();
            if (node.hasNonNull("prerequisite_courses")) {
                for (JsonNode p : node.get("prerequisite_courses")) {
                    prereqCourses.add(p.asText());
                }
            }

            String requirementText = node.hasNonNull("requirement_text")
                    ? node.get("requirement_text").asText()
                    : "";

            // Dispo par trimestre (autumn / winter / summer)
            boolean autumn = false, winter = false, summer = false;
            if (node.hasNonNull("available_terms")) {
                JsonNode terms = node.get("available_terms");
                autumn = terms.hasNonNull("autumn") && terms.get("autumn").asBoolean();
                winter = terms.hasNonNull("winter") && terms.get("winter").asBoolean();
                summer = terms.hasNonNull("summer") && terms.get("summer").asBoolean();
            }

            // Dispo jour / soir
            boolean day = false, night = false;
            if (node.hasNonNull("available_periods")) {
                JsonNode periods = node.get("available_periods");
                day = periods.hasNonNull("day") && periods.get("day").asBoolean();
                night = periods.hasNonNull("night") && periods.get("night").asBoolean();
            }

            String udemWebsite = node.hasNonNull("udem_website")
                    ? node.get("udem_website").asText()
                    : "";

            return new CourseDetails(
                    id,
                    name,
                    credits,
                    description,
                    prereqCourses,
                    requirementText,
                    autumn,
                    winter,
                    summer,
                    day,
                    night,
                    udemWebsite
            );

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des détails du cours " + courseId, e);
        }
    }
    public List<CourseDetails> compareCourses(String sigles) {
        if (sigles == null || sigles.isBlank()) {
            throw new IllegalArgumentException("Liste de sigles vide");
        }

        List<CourseDetails> results = new ArrayList<>();
        String[] codes = sigles.split(",");

        for (String code : codes) {
            String trimmed = code.trim();
            if (!trimmed.isEmpty()) {
                try {
                    CourseDetails details = getCourseDetails(trimmed);
                    results.add(details);
                } catch (Exception e) {
                    System.err.println("⚠️ Erreur pour le cours " + trimmed + ": " + e.getMessage());
                }
            }
        }

        return results;
    }

}
