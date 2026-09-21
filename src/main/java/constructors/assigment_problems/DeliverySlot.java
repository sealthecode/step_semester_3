package constructors.assigment_problems;

public class DeliverySlot {

    private static final String DEFAULT_SLOT = "ASAP";
    private static final String[] PEAK_SLOTS = {
        "12:00-13:00", "13:00-14:00", "19:00-20:00", "20:00-21:00"
    };

    private String orderId;
    private String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, DEFAULT_SLOT);
    }

    boolean isPeakHour() {
        for (String slot : PEAK_SLOTS) {
            if (slot.equals(timeSlot)) {
                return true;
            }
        }
        return false;
    }

    String getTimeSlot() {
        return timeSlot;
    }

    public static void main(String[] args) {
        DeliverySlot scheduled = new DeliverySlot("ORD101", "13:00-14:00");
        System.out.println(scheduled.isPeakHour());

        DeliverySlot asap = new DeliverySlot("ORD102");
        System.out.println(asap.isPeakHour());
        System.out.println("ORD102 slot defaulted to: " + asap.getTimeSlot());
    }
}
