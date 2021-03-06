package magical.robot.ioio;

/**
 * this class contains all of the robot relevant constants
 * pin numbers, arm movement limitations,  
 * @author �����
 *
 */
public class RobotSettings {
	
	public static final double movmentSpeed = 42.73; // cm/sec rover speed with arm on top
	public static final double sholderMov = 0.0034753;
	public static final double elbowMov = 0.004297683;
	public static final double turnaroundTime = 0;
	public static final int cubeSize = 3;
	public static final double clawTime = 1.05;

	//movment limitations
	public static final float SHOLDER_LIM_UP = (float) 0.79;
	public static final float SHOLDER_LIM_DOWN = (float) 0.63;
	public static final float ELBOW_LIM_UP = (float) 0.11;
	public static final float ELBOW_LIM_DOWN = (float) 0.10;
	public static final float WRIST_LIM_UP = 36;
	public static final float WRIST_LIM_DOWN = 0;
	
	//potentiometer pins
	public static final int DISTANCE_PIN = 35;
	public static final int SHOLDER_POT_PIN = 36;
	public static final int ELBOW_POT_PIN = 37;
	public static final int WRIST_POT_PIN = 38;

	//arm pins
	public static final int ARM_STBY = 31;
	public static final int ARM_PWM = 28;

	//turn and led
	public static final int TURN_A02_PIN = 19;
	public static final int TURN_A01_PIN = 20;
	public static final int LED_B01_PIN = 21;
	public static final int LED_B02_PIN = 22;
	//sholder and elbow

	public static final int SHOLDER_A02_PIN = 26;  
	public static final int SHOLDER_A01_PIN = 25;
	public static final int ELBOW_B02_PIN = 23;
	public static final int ELBOW_B01_PIN = 24;
	
	//wrist and grasp
	public static final int WRIST_A01_PIN = 29;
	public static final int WRIST_A02_PIN = 30;  
	public static final int GRASP_B01_PIN = 32;
	public static final int GRASP_B02_PIN = 33;
	
	//front side motors on chassis
	public static final int FRONT_CHASSIS_E1_PIN = 39;
	public static final int FRONT_CHASSIS_E2_PIN = 40;	
	public static final int FRONT_CHASSIS_M1_PIN = 41;
	public static final int FRONT_CHASSIS_M2_PIN = 42;
	
	//front side motors on chassi
	public static final int BACK_CHASSIS_M1_PIN = 43;
	public static final int BACK_CHASSIS_M2_PIN = 44;
	public static final int BACK_CHASSIS_E1_PIN = 45;
	public static final int BACK_CHASSIS_E2_PIN = 46;
	
	//distance sensor measurements
	public static final float TEN_CENTIMETERS = 0;
	
}
