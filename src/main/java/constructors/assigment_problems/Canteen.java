package constructors.assigment_problems;

public class Canteen {

    private static final int DEFAULT_TRUST_SCORE = 3;

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, DEFAULT_TRUST_SCORE);
    }

    String getCanteenCode() {
        return canteenCode;
    }

    String getCanteenName() {
        return canteenName;
    }

    int getTrustScore() {
        return trustScore;
    }

    int compareTo(Canteen other) {
        if (this.trustScore != other.trustScore) {
            return Integer.compare(other.trustScore, this.trustScore);
        }
        int byCode = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (byCode != 0) {
            return byCode;
        }
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] ranked = new Canteen[canteens.length];
        for (int i = 0; i < canteens.length; i++) {
            ranked[i] = canteens[i];
        }

        for (int i = 0; i < ranked.length - 1; i++) {
            int best = i;
            for (int j = i + 1; j < ranked.length; j++) {
                if (ranked[j].compareTo(ranked[best]) < 0) {
                    best = j;
                }
            }
            Canteen temp = ranked[i];
            ranked[i] = ranked[best];
            ranked[best] = temp;
        }
        return ranked;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        StringBuilder line = new StringBuilder("[");
        for (int i = 0; i < ranked.length; i++) {
            line.append("\"").append(ranked[i].getCanteenCode()).append("\"");
            if (i < ranked.length - 1) {
                line.append(", ");
            }
        }
        line.append("]");
        System.out.println(line);
    }
}