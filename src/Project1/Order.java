package Project1;


/**
 * Class:         Order
 * Function/Duty: This class is your Order "bean" for Project 1.  It composes of
 *                setters and getters to handle the incoming order info from the
 *                input file from ProcessOrder.  This class also has the display
 *                method, and two methods to calculate handling charges and extended
 *                prices
 * 
 * @author Bryan Lor
 */
public class Order {


    private String customerName;
    private int customerNumber;
    private double extendedPrice;
    private String item;
    private int quantity;
    private double unitPrice;
    private double handlingCharge;

    private int[] hChargeQuantity;
    private double[] hChargeAmount;
    
    /**
     * Order constructor
     */
    public Order() {
        
    }


    /**
     * Setter: customerName
     * @param s
     */
    public void setCustomerName(String s) {
        this.customerName = s;
    }


    /**
     * Getter: customerName
     * @return
     */
    public String getCustomerName() {
        return this.customerName;
    }


    /**
     * Setter: customerNumber
     * @param i
     */
    public void setCustomerNumber(int i) {
        this.customerNumber = i;
    }


    /**
     * Getter: customerNumber
     * @return
     */
    public int getCustomerNumber() {
        return this.customerNumber;
    }


    /**
     * Setter: extendedPrice
     * @param d
     */
    public void setExtendedPrice(double d) {
        this.extendedPrice = d;
    }


    /**
     * Getter: extendedPrice
     * @return
     */
    public double getExtendedPrice() {
        calExtendedPrice();
        return this.extendedPrice;
    }


    /**
     * Setter: item
     * @param s
     */
    public void setItem(String s) {
        this.item = s;
    }


    /**
     * Getter: item
     * @return
     */
    public String getItem() {
        return this.item;
    }


    /**
     * Setter: quantity
     * @param i
     */
    public void setQuantity(int i) {
        this.quantity = i;
    }


    /**
     * Getter: quantity
     * @return
     */
    public int getQuantity() {
        return this.quantity;
    }


    /**
     * Setter: unitPrice
     * @param d
     */
    public void setUnitPrice(double d) {
        this.unitPrice = d;
    }


    /**
     * Getter: unitPrice
     * @return
     */
    public double getUnitPrice() {
        return this.unitPrice;
    }


    /**
     * Setter: handlingCharge
     * @param d
     */
    public void setHandlingCharge(double d) {
        this.handlingCharge = d;
    }


    /**
     * Getter: handlingCharge
     * @return
     */
    public double getHandlingCharge() {
        calHandlingCharge();
        return this.handlingCharge;
    }


    /**
     * Setter: hChargeQuantity
     * @param i
     */
    public void setHChargeQuantity(int[] i) {
        this.hChargeQuantity = i;
    }


    /**
     * Getter: hChargeQuantity
     * @return
     */
    public int[] getHChargeQuantity() {
        return this.hChargeQuantity;
    }


    /**
     * Setter: hChargeAmount
     * @param d
     */
    public void setHChargeAmount(double[] d) {
        this.hChargeAmount = d;
    }


    /**
     * Getter: hChargeAmount
     * @return
     */
    public double[] getHChargeAmount() {
        return this.hChargeAmount;
    }


    /**
     * Method: calExtendedPrice
     * Purpose: This method will call the extended price setter method and pass to that method the
     *          product value of quantity times unit price.
     * Input: n/a
     * Output: extended price
     */
    public void calExtendedPrice() {
        setExtendedPrice(this.quantity * this.unitPrice);
    }


    /**
     * Method: calHandlingCharge
     * Purpose: This method determine the charge amount for the handling charge
     *          by looping through the charge quantity and then determining within
     *          the loop if the quantity meets a certain range found in array
     * Input: n/a
     * Output: handling charge
     */
    public void calHandlingCharge() {
        double  d  = 0;
        for (int i = 0; i < this.hChargeQuantity.length; i++) {
            if (this.quantity < this.hChargeQuantity[i]) {
                if (this.quantity>0)    setHandlingCharge(this.hChargeAmount[i - 1]);
                else setHandlingCharge(this.hChargeAmount[0]);
                return;
            }
            d = this.hChargeAmount[i];
        }
        setHandlingCharge(d);
    }

    /**
     * Method: display
     * Purpose: This method will format the output that will be used to write to
     *          the output file in the Process Order class
     * Input: customer name, item, unit price, extended price, handling charge,
     *        grand total
     * Output: customer name, item, unit price, extended price, handling charge,
     *        grand total
     * @return
     */
    public String display() {
        String display = "\n" + "\nCustomer :" + getCustomerName() +
                "\nItem Ordered: " + getItem() +
                "\nUnit Price: $" + getUnitPrice() +
                "\nTotal: $" + getExtendedPrice() +
                "\n" +
                "\nPlus a $" + getHandlingCharge() + " processing charge" +
                "\nGrand Total: " + (getExtendedPrice() + getHandlingCharge());
        
        return display;
    }
    
    
    /**
     * Method: toString
     * Purpose: This toString incorporates mostly the instance variables
     * Input: customer name, item, unit price, quantity, extended price
     * Output: customer name, item, unit price, quantity, extended price
     * @return
     */
    public String toString() {
        return "\nCustomer: " + getCustomerName() +
                "\nItem Ordered: " + getItem() +
                "\nUnit Price: $" + getUnitPrice() +
                "\nQuantity Ordered: " + getQuantity() +
                "\nTotal: $" + getExtendedPrice();
    }

 }