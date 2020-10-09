package com.chriseze.yonder.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {

    GOLD(100, 30),
    SILVER(70, 25),
    BRONZE(50, 15),
    DIAMOND(15, 5),
    GRAPHITE(6, 3),
    ROOKIE(0, 0);

    private int minNoOfRecommendations;
    private int minNoOfCompletedProjects;


    public static Level getLevel(int noOfRecommendations, int noOfCompletedProjects, Level currentLevel) {
        if ((noOfRecommendations > ROOKIE.minNoOfRecommendations && noOfRecommendations < GRAPHITE.minNoOfRecommendations)
            || (noOfCompletedProjects > ROOKIE.minNoOfCompletedProjects && noOfCompletedProjects < GRAPHITE.minNoOfCompletedProjects)) {
            return ROOKIE;
        }
        if ((noOfRecommendations > GRAPHITE.minNoOfRecommendations && noOfRecommendations < DIAMOND.minNoOfRecommendations)
            || (noOfCompletedProjects > GRAPHITE.minNoOfCompletedProjects && noOfCompletedProjects < DIAMOND.minNoOfCompletedProjects)) {
            return GRAPHITE;
        }
        if ((noOfRecommendations > DIAMOND.minNoOfRecommendations && noOfRecommendations < BRONZE.minNoOfRecommendations)
            || (noOfCompletedProjects > DIAMOND.minNoOfCompletedProjects && noOfCompletedProjects < BRONZE.minNoOfCompletedProjects)) {
            return DIAMOND;
        }
        if ((noOfRecommendations > BRONZE.minNoOfRecommendations && noOfRecommendations < SILVER.minNoOfRecommendations)
            || (noOfCompletedProjects > BRONZE.minNoOfCompletedProjects && noOfCompletedProjects < SILVER.minNoOfCompletedProjects)) {
            return BRONZE;
        }
        if ((noOfRecommendations > SILVER.minNoOfRecommendations && noOfRecommendations < GOLD.minNoOfRecommendations)
            || (noOfCompletedProjects > SILVER.minNoOfCompletedProjects && noOfCompletedProjects < GOLD.minNoOfCompletedProjects)) {
            return SILVER;
        }
        if ((noOfRecommendations >= GOLD.minNoOfRecommendations) && (noOfCompletedProjects >= GOLD.minNoOfCompletedProjects)) {
            return GOLD;
        }
        return currentLevel;
    }
}
