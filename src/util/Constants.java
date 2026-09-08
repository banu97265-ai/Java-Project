package util;

public final class Constants {

    private Constants() {} // Prevent instantiation

    // File Storage Path
    public static final String DATA_FILE_PATH = "data/students.dat";

    // Placement Eligibility Formula Weights
    public static final double WEIGHT_CGPA = 0.30;
    public static final double WEIGHT_PROGRAMMING = 0.40;
    public static final double WEIGHT_APTITUDE = 0.30;

    // Platform Programming Score Formula Weights
    public static final double WEIGHT_PLATFORM_RANK = 0.40;
    public static final double WEIGHT_PLATFORM_SCORE = 0.30;
    public static final double WEIGHT_PLATFORM_PROBLEMS = 0.30;

    // Normalization Benchmarks
    public static final double RANK_BENCHMARK = 100000.0;
    public static final double SCORE_BENCHMARK = 3000.0;
    public static final double PROBLEMS_BENCHMARK = 500.0;
    public static final double APTITUDE_RANK_BENCHMARK = 100.0;

    // CGPA Base Scale
    public static final double MAX_CGPA = 10.0;

    // Readiness Classification Thresholds
    public static final double THRESHOLD_EXCELLENT = 90.0;
    public static final double THRESHOLD_GOOD = 75.0;
    public static final double THRESHOLD_MODERATE = 60.0;

    // Classification Labels
    public static final String READINESS_EXCELLENT = "Excellent Placement Readiness";
    public static final String READINESS_GOOD = "Good Placement Readiness";
    public static final String READINESS_MODERATE = "Moderate Placement Readiness";
    public static final String READINESS_NEEDS_IMPROVEMENT = "Needs Improvement";

    // Improvement Suggestions
    public static final String SUGGESTION_ACADEMIC = 
        "Focus more on improving CGPA and strengthening core academic subjects.";
    public static final String SUGGESTION_PROGRAMMING = 
        "Focus more on competitive programming, problem solving, algorithms, and data structures.";
    public static final String SUGGESTION_APTITUDE = 
        "Focus more on quantitative aptitude, logical reasoning, and regular aptitude practice.";
}