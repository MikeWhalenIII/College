public class Compute {
    private String[] datesArray;
    private double[] scoreArray;

    public Compute(String[] dates, double[] scores) {
        datesArray = dates;
        scoreArray = scores;
    }

    public void computeMeanAndSD() {
        System.out.println("\nMeans and Standard Deviations of Scores\n");
        System.out.printf("%-7s %-9s %-9s %-9s %-9s %-9s\n", "Student", datesArray[0], datesArray[1], datesArray[2], datesArray[3], datesArray[4]);

        // Display all student score data
        for (int i = 0; i < 24; i++) {
            System.out.printf("%-10d %-9.0f %-9.0f %-9.0f %-9.0f %-9.0f\n", (i + 1), scoreArray[i], scoreArray[i + 24], scoreArray[i + 48], scoreArray[i + 72], scoreArray[i + 96]);
        }

        // Compute and display the mean
        double mean1 = 0, mean2 = 0, mean3 = 0, mean4 = 0, mean5 = 0;

        for (int i = 0; i < scoreArray.length; i++) {
            if (i <= 23) {
                mean1 += scoreArray[i];
            } else if (i >= 24 && i <= 47) {
                mean2 += scoreArray[i];
            } else if (i >= 48 && i <= 71) {
                mean3 += scoreArray[i];
            } else if (i >= 72 && i <= 95) {
                mean4 += scoreArray[i];
            } else if (i >= 96 && i <= 119) {
                mean5 += scoreArray[i];
            }
        }
        mean1 = mean1 / 24;
        mean2 = mean2 / 24;
        mean3 = mean3 / 24;
        mean4 = mean4 / 24;
        mean5 = mean5 / 24;

        System.out.println("************************************************************");
        System.out.printf("%-10s %-9.2f %-9.2f %-9.2f %-9.2f %-9.2f\n", "Mean", mean1, mean2, mean3, mean4, mean5);

        // Compute and display the Standard Deviation
        double sd1 = 0, sd2 = 0, sd3 = 0, sd4 = 0, sd5 = 0;

        for (int i = 0; i < scoreArray.length; i++) {
            if (i <= 23) {
                sd1 += Math.pow((scoreArray[i] - mean1), 2);
            } else if (i >= 24 && i <= 47) {
                sd2 += Math.pow((scoreArray[i] - mean2), 2);
            } else if (i >= 48 && i <= 71) {
                sd3 += Math.pow((scoreArray[i] - mean3), 2);
            } else if (i >= 72 && i <= 95) {
                sd4 += Math.pow((scoreArray[i] - mean4), 2);
            } else if (i >= 96 && i <= 119) {
                sd5 += Math.pow((scoreArray[i] - mean5), 2);
            }
        }
        sd1 = Math.sqrt(sd1 / 24);
        sd2 = Math.sqrt(sd2 / 24);
        sd3 = Math.sqrt(sd3 / 24);
        sd4 = Math.sqrt(sd4 / 24);
        sd5 = Math.sqrt(sd5 / 24);

        System.out.printf("%-10s %-9.2f %-9.2f %-9.2f %-9.2f %-9.2f\n", "Std Dev", sd1, sd2, sd3, sd4, sd5);
    }

    public void computeDifferences() {
        System.out.println("\nSignificant Differences in Mean Scores\n");
        System.out.printf("%20s %-9s %-9s %-9s\n", datesArray[1], datesArray[2], datesArray[3], datesArray[4]);


    }
}
