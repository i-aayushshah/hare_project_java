public class PremiumMember extends GymMember
{
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String personalTrainer)
    {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.premiumCharge = 50000;
        this.paidAmount = 0;
        this.isFullPayment = false;
        this.discountAmount = 0;
    }
    
    public double getPremiumCharge() 
    {
        return premiumCharge;
    }
    public String getPersonalTrainer()
    {
        return personalTrainer;
    }
    public boolean isFullPayment()
    {
        return isFullPayment;
    }
    public double getPaidAmount()
    {
        return paidAmount;
    }
    public double getDiscountAmount()
    {
        return discountAmount;
    }
    
    @Override
    public void markAttendance() 
    {
        this.attendance++;
        this.loyaltyPoints += 10;
    }
    
    public String payDueAmount(double paidAmount) 
    {
        if (this.isFullPayment) 
        {
            return "Payment is already complete. No due amount.";
        }
        double totalPaid = this.paidAmount + paidAmount;
        if (totalPaid > premiumCharge) 
        {
            return "Payment exceeds the premium charge. Maximum amount to pay: " + (premiumCharge - this.paidAmount);
        }
        this.paidAmount = totalPaid;
        if (this.paidAmount == premiumCharge) 
        {
            this.isFullPayment = true;
        }
        double remainingAmount = premiumCharge - this.paidAmount;
        return "Payment successful. Remaining amount to be paid: " + remainingAmount;
    }
    
    public void calculateDiscount()
    {
        if (this.isFullPayment) 
        {
            this.discountAmount = premiumCharge * 0.10; 
            System.out.println("Discount calculated successfully. Discount amount: " + discountAmount);
        }
        else 
        {
            System.out.println("No discount available. Complete the payment to get 10% discount.");
        }
    }
    
    public void revertPremiumMember()
    {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }
    
    @Override
    public void display()
    {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment: " + (isFullPayment ? "Yes" : "No"));
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount to be Paid: " + remainingAmount);
        if (isFullPayment) 
        {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
    
}