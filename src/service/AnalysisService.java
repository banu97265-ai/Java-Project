package service;

import model.ProgrammingPlatform;
import model.Student;
import util.CalculationUtil;
import util.Constants;

import java.util.List;

public class AnalysisService {

    public void performFullAnalysis(Student student) {
        calculateProgrammingScore(student);
        calculateCgpaPerformance(student);
        calculateAptitudePerformance(student);
        calculatePlacementEligibility(student);
        classifyPlacementReadiness(student);
        identifyWeaknessAndSuggestions(student);
    }

    public void calculateProgrammingScore(Student student) {

    List<ProgrammingPlatform> platforms = student.getPlatforms();

    if (platforms == null || platforms.isEmpty()) {
        student.setProgrammingScore(0.0);
        return;
    }

    double totalPlatformScore = 0.0;

    for (ProgrammingPlatform platform : platforms) {
            // Rank performance: lower rank is better
            double rankPerf = 100.0 * (1.0 - ((double) (platform.getRank() - 1) / Constants.RANK_BENCHMARK));
            rankPerf = CalculationUtil.clamp(rankPerf, 0.0, 100.0);

            // Score performance
            double scorePerf = 100.0 * (platform.getScore() / Constants.SCORE_BENCHMARK);
            scorePerf = CalculationUtil.clamp(scorePerf, 0.0, 100.0);

            // Problem-solving performance
            double probPerf = 100.0 * ((double) platform.getProblemsSolved() / Constants.PROBLEMS_BENCHMARK);
            probPerf = CalculationUtil.clamp(probPerf, 0.0, 100.0);

            double individualScore = (Constants.WEIGHT_PLATFORM_RANK * rankPerf)
                    + (Constants.WEIGHT_PLATFORM_SCORE * scorePerf)
                    + (Constants.WEIGHT_PLATFORM_PROBLEMS * probPerf);

            individualScore = CalculationUtil.clamp(individualScore, 0.0, 100.0);
            platform.setCalculatedPlatformScore(CalculationUtil.roundToTwoDecimals(individualScore));

            totalPlatformScore += individualScore;
        }

        double averageScore = totalPlatformScore / platforms.size();
        student.setProgrammingScore(CalculationUtil.roundToTwoDecimals(averageScore));
    }

    public void calculateCgpaPerformance(Student student) {
        double perf = (student.getCgpa() / Constants.MAX_CGPA) * 100.0;
        student.setCgpaPerformance(CalculationUtil.roundToTwoDecimals(CalculationUtil.clamp(perf, 0.0, 100.0)));
    }

    public void calculateAptitudePerformance(Student student) {
        double perf = 100.0 * (1.0 - ((double) (student.getAptitudeRank() - 1) / Constants.APTITUDE_RANK_BENCHMARK));
        student.setAptitudePerformance(CalculationUtil.roundToTwoDecimals(CalculationUtil.clamp(perf, 0.0, 100.0)));
    }

    public void calculatePlacementEligibility(Student student) {
        double eligibility = (Constants.WEIGHT_CGPA * student.getCgpaPerformance())
                + (Constants.WEIGHT_PROGRAMMING * student.getProgrammingScore())
                + (Constants.WEIGHT_APTITUDE * student.getAptitudePerformance());

        eligibility = CalculationUtil.clamp(eligibility, 0.0, 100.0);
        student.setPlacementEligibility(CalculationUtil.roundToTwoDecimals(eligibility));
    }

    public void classifyPlacementReadiness(Student student) {
        double eligibility = student.getPlacementEligibility();
        if (eligibility >= Constants.THRESHOLD_EXCELLENT) {
            student.setPlacementReadiness(Constants.READINESS_EXCELLENT);
        } else if (eligibility >= Constants.THRESHOLD_GOOD) {
            student.setPlacementReadiness(Constants.READINESS_GOOD);
        } else if (eligibility >= Constants.THRESHOLD_MODERATE) {
            student.setPlacementReadiness(Constants.READINESS_MODERATE);
        } else {
            student.setPlacementReadiness(Constants.READINESS_NEEDS_IMPROVEMENT);
        }
    }

    public void identifyWeaknessAndSuggestions(Student student) {
        double cgpaPerf = student.getCgpaPerformance();
        double progPerf = student.getProgrammingScore();
        double aptPerf = student.getAptitudePerformance();

        double lowest = Math.min(cgpaPerf, Math.min(progPerf, aptPerf));

        if (lowest == cgpaPerf) {
            student.setPrimaryFocusArea("Academic Performance (CGPA)");
            student.setPersonalizedSuggestion(Constants.SUGGESTION_ACADEMIC);
        } else if (lowest == progPerf) {
            student.setPrimaryFocusArea("Programming Performance");
            student.setPersonalizedSuggestion(Constants.SUGGESTION_PROGRAMMING);
        } else {
            student.setPrimaryFocusArea("Aptitude Performance");
            student.setPersonalizedSuggestion(Constants.SUGGESTION_APTITUDE);
        }
    }
}