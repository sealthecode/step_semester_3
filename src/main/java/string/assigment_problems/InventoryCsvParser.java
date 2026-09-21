package string.assigment_problems;

/**
 * Week 2 - Problem 3: Product Inventory CSV Parser
 *
 * Takes one CSV line in the form "ProductName,SKU,Quantity", splits it
 * into fields, checks there are exactly three, and prints a formatted
 * record. Anything else is rejected as an invalid record.
 */
public class InventoryCsvParser {

    void parseInventoryRecord(String csvLine) {

        // split(",") cuts the line at every comma.
        // "Wireless Mouse,WM-2201,150" -> ["Wireless Mouse", "WM-2201", "150"]
        String[] fields = csvLine.split(",");

        // ALWAYS check the length before touching fields[1] or fields[2].
        // Skipping this check is what causes ArrayIndexOutOfBoundsException.
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;   // stop here - nothing more to do for a bad line
        }

        // Safe now: we know all three positions exist.
        String productName = fields[0];
        String sku = fields[1];
        String quantity = fields[2];

        System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + quantity);
    }

    public static void main(String[] args) {
        InventoryCsvParser parser = new InventoryCsvParser();

        parser.parseInventoryRecord("Wireless Mouse,WM-2201,150");   // 3 fields -> valid
        parser.parseInventoryRecord("Wireless Mouse,150");           // 2 fields -> invalid
        parser.parseInventoryRecord("Keyboard,KB-100,40,extra");     // 4 fields -> invalid
    }
}