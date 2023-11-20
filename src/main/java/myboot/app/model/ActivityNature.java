package myboot.app.model;

/**
 * Enumerates the different natures or types of activities that can be associated with a CV.
 * This enum can categorize activities into various types such as professional experience,
 * education, leisure activities, and projects.
 */
public enum ActivityNature {

    /**
     * Represents a professional work experience. This can include jobs, internships,
     * or other professional roles.
     */
    PROFESSIONAL_EXPERIENCE,

    /**
     * Represents formal educational experiences. This includes schooling, academic degrees,
     * certifications, or any formal training.
     */
    EDUCATION,

    /**
     * Represents leisure activities. These are activities done for enjoyment, relaxation,
     * or hobbies that are not necessarily tied to professional or educational pursuits.
     */
    LEISURE,

    /**
     * Represents projects. This can include both professional and personal projects.
     */
    PROJECT
}
