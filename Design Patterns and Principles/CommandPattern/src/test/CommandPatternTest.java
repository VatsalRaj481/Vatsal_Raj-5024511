package test;
//Command.java
interface Command {
 void execute();
}

//Light.java (Receiver)
class Light {
 private boolean on;

 public void switchOn() {
     on = true;
     System.out.println("Light is ON");
 }

 public void switchOff() {
     on = false;
     System.out.println("Light is OFF");
 }
}

//LightOnCommand.java (Concrete Command)
class LightOnCommand implements Command {
 private Light light;

 public LightOnCommand(Light light) {
     this.light = light;
 }

 @Override
 public void execute() {
     light.switchOn();
 }
}

//LightOffCommand.java (Concrete Command)
class LightOffCommand implements Command {
 private Light light;

 public LightOffCommand(Light light) {
     this.light = light;
 }

 @Override
 public void execute() {
     light.switchOff();
 }
}

//RemoteControl.java (Invoker)
class RemoteControl {
 private Command command;

 public void setCommand(Command command) {
     this.command = command;
 }

 public void pressButton() {
     command.execute();
 }
}

//CommandPatternTest.java
public class CommandPatternTest {
 public static void main(String[] args) {
     Light light = new Light();
     Command lightOn = new LightOnCommand(light);
     Command lightOff = new LightOffCommand(light);

     RemoteControl remote = new RemoteControl();

     // Turn the light on
     remote.setCommand(lightOn);
     remote.pressButton();

     // Turn the light off
     remote.setCommand(lightOff);
     remote.pressButton();
 }
}
