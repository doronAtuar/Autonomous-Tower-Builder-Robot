package ioio.examples.hello;

import java.util.Timer;
import java.util.TimerTask;

import ioio.lib.api.AnalogInput;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

public class MovmentSystem {
	private static final float SHOLDER_LIM_UP = (float) 0.79;
	private static final float SHOLDER_LIM_DOWN = (float) 0.63;
	private static final float ELBOW_LIM_UP = (float) 0.11;
	private static final float ELBOW_LIM_DOWN = (float) 0.10;
	private static final float WRIST_LIM_UP = 36;
	private static final float WRIST_LIM_DOWN = 0;
	

	private ChassisFrame _chassis;
	private RoboticArmEdge _arm;
	private AnalogInput _wristPosition;
	private AnalogInput _sholderPosition;
	private AnalogInput _elbowPosition;
	private AnalogInput _distance;
	private Timer _stopTimer = new Timer("Stop Timer");
		
	


	public MovmentSystem(IOIO ioio, ChassisFrame chassis, RoboticArmEdge arm, int wristPositionPin, int sholderPositionPin, int elbowPositionPin, int distancePin) {
		_chassis = chassis;
		_arm = arm;
		
		try {
			_wristPosition = ioio.openAnalogInput(wristPositionPin);
			_sholderPosition = ioio.openAnalogInput(sholderPositionPin);
			_elbowPosition = ioio.openAnalogInput(elbowPositionPin);
			_distance = ioio.openAnalogInput(distancePin);
		} catch (ConnectionLostException e) {
			e.printStackTrace();
		}
	}
	
	public RoboticArmEdge get_arm() {
		return _arm;
	}
	
	public ChassisFrame get_chassis() {
		return _chassis;
	}
	
	public float get_elbowPosition() throws InterruptedException, ConnectionLostException {
		return _elbowPosition.read();
	}
	
	public float get_sholderPosition() throws InterruptedException, ConnectionLostException {
		return _sholderPosition.read();
	}
	
	public float get_wristPosition() throws InterruptedException, ConnectionLostException {
		return _wristPosition.read();
	}
	
	public float get_distance() throws InterruptedException, ConnectionLostException {
		return _distance.read();
	}
	
	
	public void close() {
		_elbowPosition.close();
		_sholderPosition.close();
		_sholderPosition.close();
		_distance.close();
		_chassis.close();
		_arm.close();
		
	}
	
	
	public void moveForward(double centimeters) throws ConnectionLostException{
		long driveTime = (long) (RobotSettings.movmentSpeed * centimeters);
		_chassis.driveForward();
		
		_stopTimer.schedule(new TimerTask() {
			
			public void run() {
				try {
					_chassis.stop();
				} catch (ConnectionLostException e) {
					e.printStackTrace();
				}
			}
		}, driveTime);
		
	}
	
	
}
