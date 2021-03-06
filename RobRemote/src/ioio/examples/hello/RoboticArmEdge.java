package ioio.examples.hello;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;

public class RoboticArmEdge {
	private SmallMotorDriver _wrist_and_grasp;
	private SmallMotorDriver _sholder_and_elbow;
	private SmallMotorDriver _turn_and_led;
	private DigitalOutput _stby;
	private PwmOutput _pwm;
	
	public RoboticArmEdge(IOIO ioio,SmallMotorDriver wrist_and_grasp, SmallMotorDriver sholder_and_elbow, SmallMotorDriver turn_and_led, int stby, int pwm) {
		this._sholder_and_elbow = sholder_and_elbow;
		this._wrist_and_grasp = wrist_and_grasp;
		this._turn_and_led = turn_and_led;
		try {
			_stby = ioio.openDigitalOutput(stby,true);
			_pwm  = ioio.openPwmOutput(pwm, 100);
			_pwm.setDutyCycle((float)100);
			
		} catch (ConnectionLostException e){
			e.printStackTrace();
		}
	}

	
	public void toggleLed(boolean a0) throws ConnectionLostException{
		_turn_and_led.turnMotorB(a0, false);
	}
	
	public void turnSholder(boolean a0,boolean a1) throws ConnectionLostException{
		_sholder_and_elbow.turnMotorA(a0,a1);
	}
	
	public void turnElbow(boolean a0,boolean a1) throws ConnectionLostException{
		_sholder_and_elbow.turnMotorB(a0,a1);
	}

	public void turnWrist(boolean a0,boolean a1) throws ConnectionLostException{
		_wrist_and_grasp.turnMotorA(a0,a1);
	}
	
	public void turnGrasp(boolean a0,boolean a1) throws ConnectionLostException{
		_wrist_and_grasp.turnMotorB(a0,a1);
	}
	
	public void turnTurning(boolean a0,boolean a1) throws ConnectionLostException{
		_turn_and_led.turnMotorA(a0,a1);
	}

	public void TurnLeft() throws ConnectionLostException{
		this.turnTurning(true, false);
	}

	public void TurnRight() throws ConnectionLostException{
		this.turnTurning(false, true);
	}


	public void sholderUp() throws ConnectionLostException{
		this.turnSholder(true, false);
	}

	public void sholderDown() throws ConnectionLostException{
		this.turnSholder(false, true);
	}


	public void elbowUp() throws ConnectionLostException{
		this.turnElbow(true, false);
	}

	public void elbowDown() throws ConnectionLostException{
		this.turnElbow(false, true);
	}


	public void wristUp() throws ConnectionLostException{
		this.turnWrist(true, false);
	}

	public void wristDown() throws ConnectionLostException{
		this.turnWrist(false, true);
	}


	public void closeHand() throws ConnectionLostException{
		this.turnGrasp(false, true);
	}

	public void openHand() throws ConnectionLostException{
		this.turnGrasp(true, false);
	}

	public void turnOnLed() throws ConnectionLostException{
		_turn_and_led.turnMotorB(true, false);
	}
	
	public void turnOffLed() throws ConnectionLostException{
		_turn_and_led.turnMotorB(false, false);
	}

	public void stop() throws ConnectionLostException{
		_turn_and_led.stop();
		_wrist_and_grasp.stop();
		_sholder_and_elbow.stop();
	}
	
	public void close(){
		_sholder_and_elbow.close();
		_turn_and_led.close();
		_wrist_and_grasp.close();
		_pwm.close();
		_stby.close();
	}
}
