public class Compute {
    private final double scoresForEachDay = 24;
    double[] mean = new double[5];
    double[] stdDev = new double[5];
    private final String[] datesArray;
    private final double[] scoreArray;

    public Compute(String[] dates, double[] scores) {
        datesArray = dates;
        scoreArray = scores;
    }

    public void computeMeanAndSD() {
        System.out.println("\nMeans and Standard Deviations of Scores\n");
        System.out.printf("%-7s %-9s %-9s %-9s %-9s %-9s\n", "Student", datesArray[0], datesArray[1], datesArray[2], datesArray[3], datesArray[4]);

        // Display all student score data
        for (int i = 0; i < scoresForEachDay; i++) {
            System.out.printf("%-10d %-9.0f %-9.0f %-9.0f %-9.0f %-9.0f\n", (i + 1), scoreArray[i], scoreArray[i + 24], scoreArray[i + 48], scoreArray[i + 72], scoreArray[i + 96]);
        }

        // Compute and display the mean
        for (int i = 0; i < scoreArray.length; i++) {
            if (i <= 23) {
                mean[0] += scoreArray[i];
            } else if (i >= 24 && i <= 47) {
                mean[1] += scoreArray[i];
            } else if (i >= 48 && i <= 71) {
                mean[2] += scoreArray[i];
            } else if (i >= 72 && i <= 95) {
                mean[3] += scoreArray[i];
            } else if (i >= 96 && i <= 119) {
                mean[4] += scoreArray[i];
            }
        }
        mean[0] = mean[0] / scoresForEachDay;
        mean[1] = mean[1] / scoresForEachDay;
        mean[2] = mean[2] / scoresForEachDay;
        mean[3] = mean[3] / scoresForEachDay;
        mean[4] = mean[4] / scoresForEachDay;

        System.out.println("************************************************************");
        System.out.printf("%-10s %-9.2f %-9.2f %-9.2f %-9.2f %-9.2f\n", "Mean", mean[0], mean[1], mean[2], mean[3], mean[4]);

        // Compute and display the Standard Deviation
        for (int i = 0; i < scoreArray.length; i++) {
            if (i <= 23) {
                stdDev[0] += Math.pow((scoreArray[i] - mean[0]), 2);
            } else if (i >= 24 && i <= 47) {
                stdDev[1] += Math.pow((scoreArray[i] - mean[1]), 2);
            } else if (i >= 48 && i <= 71) {
                stdDev[2] += Math.pow((scoreArray[i] - mean[2]), 2);
            } else if (i >= 72 && i <= 95) {
                stdDev[3] += Math.pow((scoreArray[i] - mean[3]), 2);
            } else if (i >= 96 && i <= 119) {
                stdDev[4] += Math.pow((scoreArray[i] - mean[4]), 2);
            }
        }
        stdDev[0] = Math.sqrt(stdDev[0] / scoresForEachDay);
        stdDev[1] = Math.sqrt(stdDev[1] / scoresForEachDay);
        stdDev[2] = Math.sqrt(stdDev[2] / scoresForEachDay);
        stdDev[3] = Math.sqrt(stdDev[3] / scoresForEachDay);
        stdDev[4] = Math.sqrt(stdDev[4] / scoresForEachDay);

        System.out.printf("%-10s %-9.2f %-9.2f %-9.2f %-9.2f %-9.2f\n", "Std Dev", stdDev[0], stdDev[1], stdDev[2], stdDev[3], stdDev[4]);
    }

    public void computeDifferences() {
        double tValue = 0, sp = 0, sp2 = 0, degOfFreedom = 46, criticalTValue = 2.25;

        System.out.println("\nSignificant Differences in Mean Scores\n");
        System.out.printf("%20s %-9s %-9s %-9s\n", datesArray[1], datesArray[2], datesArray[3], datesArray[4]);


        for (int i = 0; i < datesArray.length - 1; i++) {
            System.out.printf("%s", datesArray[i]);
            String[] output = new String[]{" ", " ", " ", " "};

            for (int j = i + 1; j <= datesArray.length - 1; j++) {
                sp = Math.sqrt((((scoresForEachDay - 1) * Math.pow(stdDev[i], 2)) + ((scoresForEachDay - 1) * Math.pow(stdDev[j], 2))) / degOfFreedom);
                tValue = (mean[i] - mean[j]) / (sp * Math.sqrt((1 / scoresForEachDay + 1 / scoresForEachDay)));

                if (tValue <= criticalTValue) {
                    output[j - 1] = "N";
                } else {
                    output[j - 1] = "Y";
                }
            }
            System.out.printf("%6s %9s %9s %9s\n", output[0], output[1], output[2], output[3]);
        }
    }
}
