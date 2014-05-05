package ioio.examples.hello;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;

public class RoboticArmEdge {
	private SmallMotorDriver wrist_and_grasp;
	private SmallMotorDriver sholder_and_elbow;
	private SmallMotorDriver turn_and_led;
	private DigitalOutput stby;
	
	public RoboticArmEdge(IOIO ioio,SmallMotorDriver wrist_and_grasp, SmallMotorDriver sholder_and_elbow, SmallMotorDriver turn_and_led, int stby) {
		this.sholder_and_elbow = sholder_and_elbow;
		this.wrist_and_grasp = wrist_and_grasp;
		this.turn_and_led = turn_and_led;
		try {
			this.stby = ioio.openDigitalOutput(stby,true);
		} catch (ConnectionLostException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void turnOnLed() throws ConnectionLostException{
		turn_and_led.turnMotorB(true, false);
	}
	
	public void turnOffLed() throws ConnectionLostException{
		turn_and_led.turnMotorB(false, false);
	}
	
	public void turnSholder(boolean a0,boolean a1) throws ConnectionLostException{
		sholder_and_elbow.turnMotorA(a0,a1);
	}
	
	public void turnElbow(boolean a0,boolean a1) throws ConnectionLostException{
		sholder_and_elbow.turnMotorB(a0,a1);
	}

	public void turnWrist(boolean a0,boolean a1) throws ConnectionLostException{
		wrist_and_grasp.turnMotorA(a0,a1);
	}
	
	public void turnGrasp(boolean a0,boolean a1) throws ConnectionLostException{
		wrist_and_grasp.turnMotorB(a0,a1);
	}
	
	public void turnTurning(boolean a0,boolean a1) throws ConnectionLostException{
		turn_and_led.turnMotorA(a0,a1);
	}
	
	public void changeLedStatus(boolean a0,boolean a1) throws ConnectionLostException{
		turn_and_led.turnMotorB(a0, false);
	}
}