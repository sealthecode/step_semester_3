package constructors.assigment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("orderValue cannot be negative");
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("delayMinutes cannot be negative");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        int firstBand = Math.min(delayMinutes, 5);
        int secondBand = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int thirdBand = Math.max(delayMinutes - 15, 0);

        double percent = (firstBand * 0.5) + (secondBand * 1.0) + (thirdBand * 2.0);
        double tieredFee = orderValue * percent / 100.0;
        double floorFee = orderValue * minimumSurgePercent / 100.0;

        return Math.max(tieredFee, floorFee);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);

        System.out.println("Rs " + calculator.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calculator.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calculator.calculateSurgeFee(500, 16));

        try {
            calculator.calculateSurgeFee(-500, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        try {
            calculator.calculateSurgeFee(500, -3);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}