package test;

//PaymentStrategy.java
interface PaymentStrategy {
 void pay(double amount);
}

//CreditCardPayment.java (Concrete Strategy)
class CreditCardPayment implements PaymentStrategy {
 private String cardNumber;
 private String cardHolderName;
 private String cvv;
 private String expirationDate;

 public CreditCardPayment(String cardNumber, String cardHolderName, String cvv, String expirationDate) {
     this.cardNumber = cardNumber;
     this.cardHolderName = cardHolderName;
     this.cvv = cvv;
     this.expirationDate = expirationDate;
 }

 @Override
 public void pay(double amount) {
     System.out.println("Paid " + amount + " using Credit Card.");
 }
}

//PayPalPayment.java (Concrete Strategy)
class PayPalPayment implements PaymentStrategy {
 private String email;

 public PayPalPayment(String email) {
     this.email = email;
 }

 @Override
 public void pay(double amount) {
     System.out.println("Paid " + amount + " using PayPal.");
 }
}

//PaymentContext.java (Context)
class PaymentContext {
 private PaymentStrategy paymentStrategy;

 public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
     this.paymentStrategy = paymentStrategy;
 }

 public void pay(double amount) {
     paymentStrategy.pay(amount);
 }
}

//StrategyPatternTest.java
public class StrategyPatternTest {
 public static void main(String[] args) {
     PaymentContext context = new PaymentContext();

     // Pay using Credit Card
     context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "John Doe", "123", "12/24"));
     context.pay(250.75);

     // Pay using PayPal
     context.setPaymentStrategy(new PayPalPayment("johndoe@example.com"));
     context.pay(150.50);
 }
}
