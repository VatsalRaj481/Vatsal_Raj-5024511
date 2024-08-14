package test;
//StripeAdapter.java (Adapter)
class StripeAdapter implements PaymentProcessor {
private Stripe stripe;

public StripeAdapter(Stripe stripe) {
   this.stripe = stripe;
}

@Override
public void processPayment(double amount) {
   stripe.makePayment(amount);
}
}