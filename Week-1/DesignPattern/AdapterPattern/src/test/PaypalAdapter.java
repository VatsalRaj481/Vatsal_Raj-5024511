package test;

//PayPalAdapter.java (Adapter)
class PayPalAdapter implements PaymentProcessor {
private PayPal paypal;

public PayPalAdapter(PayPal paypal) {
   this.paypal = paypal;
}

@Override
public void processPayment(double amount) {
   paypal.makePayment(amount);
}
}
