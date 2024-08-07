package test;
//AdapterPatternTest.java
public class AdapterPatternTest {
 public static void main(String[] args) {
     PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPal());
     paypalProcessor.processPayment(100.0);

     PaymentProcessor stripeProcessor = new StripeAdapter(new Stripe());
     stripeProcessor.processPayment(200.0);
 }
}
