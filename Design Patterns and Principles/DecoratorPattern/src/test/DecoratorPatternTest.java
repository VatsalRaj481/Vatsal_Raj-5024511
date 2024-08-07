package test;
//Notifier.java
interface Notifier {
 void send(String message);
}

//EmailNotifier.java (Concrete Component)
class EmailNotifier implements Notifier {
 @Override
 public void send(String message) {
     System.out.println("Sending email notification: " + message);
 }
}

//NotifierDecorator.java (Abstract Decorator)
abstract class NotifierDecorator implements Notifier {
 protected Notifier notifier;

 public NotifierDecorator(Notifier notifier) {
     this.notifier = notifier;
 }

 @Override
 public void send(String message) {
     notifier.send(message);
 }
}

//SMSNotifierDecorator.java (Concrete Decorator)
class SMSNotifierDecorator extends NotifierDecorator {
 public SMSNotifierDecorator(Notifier notifier) {
     super(notifier);
 }

 @Override
 public void send(String message) {
     super.send(message);
     System.out.println("Sending SMS notification: " + message);
 }
}

//SlackNotifierDecorator.java (Concrete Decorator)
class SlackNotifierDecorator extends NotifierDecorator {
 public SlackNotifierDecorator(Notifier notifier) {
     super(notifier);
 }

 @Override
 public void send(String message) {
     super.send(message);
     System.out.println("Sending Slack notification: " + message);
 }
}

//DecoratorPatternTest.java
public class DecoratorPatternTest {
 public static void main(String[] args) {
     Notifier notifier = new EmailNotifier();
     Notifier smsNotifier = new SMSNotifierDecorator(notifier);
     Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

     slackNotifier.send("This is a test message.");
 }
}
