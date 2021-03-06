package ioio.examples.hello;


import ioio.examples.hello.R;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;
import android.hardware.SensorManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.ToggleButton;

//holds all the buttons that needs listening to
public class MainActivity extends IOIOActivity {
	
	//movment limitations
	private static final float SHOLDER_LIM_UP = (float) 0.79;
	private static final float SHOLDER_LIM_DOWN = (float) 0.63;
	private static final float ELBOW_LIM_UP = (float) 0.11;
	private static final float ELBOW_LIM_DOWN = (float) 0.10;
	private static final float WRIST_LIM_UP = 36;
	private static final float WRIST_LIM_DOWN = 0;
	
	//potentiometer pins
	private static final int DISTANCE_PIN = 35;
	private static final int SHOLDER_POT_PIN = 36;
	private static final int ELBOW_POT_PIN = 37;
	private static final int WRIST_POT_PIN = 38;

	//arm pins
	private static final int ARM_STBY = 31;
	private static final int ARM_PWM = 28;
	

	//turn and led
	private static final int TURN_A02_PIN = 19;
	private static final int TURN_A01_PIN = 20;
	private static final int LED_B01_PIN = 21;
	private static final int LED_B02_PIN = 22;
	//sholder and elbow

	private static final int SHOLDER_A02_PIN = 26;  
	private static final int SHOLDER_A01_PIN = 25;
	private static final int ELBOW_B02_PIN = 23;
	private static final int ELBOW_B01_PIN = 24;
	
	//wrist and grasp
	private static final int WRIST_A01_PIN = 29;
	private static final int WRIST_A02_PIN = 30;  
	private static final int GRASP_B01_PIN = 32;
	private static final int GRASP_B02_PIN = 33;
	
	//front side motors on chassi
	private static final int FRONT_CHASSIS_E1_PIN = 39;
	private static final int FRONT_CHASSIS_E2_PIN = 40;	
	private static final int FRONT_CHASSIS_M1_PIN = 41;
	private static final int FRONT_CHASSIS_M2_PIN = 42;
	
	//front side motors on chassi
	private static final int BACK_CHASSIS_M1_PIN = 43;
	private static final int BACK_CHASSIS_M2_PIN = 44;
	private static final int BACK_CHASSIS_E1_PIN = 45;
	private static final int BACK_CHASSIS_E2_PIN = 46;
	
	//sides
	private static final boolean TURN_LEFT = false;
	private static final boolean TURN_RIGHT = true;
	
	//buttons pressed
	private static final int PRESSED_UP = 1;
	private static final int PRESSED_DOWN = 2;
	private static final int PRESSED_LEFT = 4;
	private static final int PRESSED_RIGHT = 8;

	//GUI fields

	private ToggleButton _startStop;

	
	private ChassisFrame _chasiss;
	private RoboticArmEdge _arm;
	private MovmentSystem _movmentModule;
	private SensorManager mSensorManager;
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		_startStop = (ToggleButton) findViewById(R.id.toggleButton1);
		
	}
	
	
	public void onToggleClicked(View view) throws ConnectionLostException, InterruptedException{
		boolean on = ((ToggleButton) view).isChecked();
		if (on){
			_movmentModule.setRoverSpeed(100);
			_movmentModule.moveArm(15);
		} else{
			_movmentModule.stop();
		}
	}

	// holds all the components connected to the IOIO and responsible for their update
	class Looper extends BaseIOIOLooper {
		protected void setup() throws ConnectionLostException {
			BigMotorDriver chassiFront = new BigMotorDriver(ioio_, FRONT_CHASSIS_M1_PIN, FRONT_CHASSIS_E1_PIN, FRONT_CHASSIS_M2_PIN, FRONT_CHASSIS_E2_PIN);
			BigMotorDriver chassiBack = new BigMotorDriver(ioio_, BACK_CHASSIS_M1_PIN, BACK_CHASSIS_E1_PIN, BACK_CHASSIS_M2_PIN, BACK_CHASSIS_E2_PIN);
			_chasiss = new ChassisFrame(chassiFront, chassiBack);
			
			SmallMotorDriver turn_and_led = new SmallMotorDriver(ioio_, TURN_A01_PIN, TURN_A02_PIN, LED_B01_PIN, LED_B02_PIN);
			SmallMotorDriver sholder_and_elbow = new SmallMotorDriver(ioio_, SHOLDER_A01_PIN, SHOLDER_A02_PIN, ELBOW_B01_PIN, ELBOW_B02_PIN);
			SmallMotorDriver wrist_and_grasp = new SmallMotorDriver(ioio_, WRIST_A01_PIN, WRIST_A02_PIN, GRASP_B01_PIN, GRASP_B02_PIN);
			_arm = new RoboticArmEdge(ioio_, wrist_and_grasp, sholder_and_elbow, turn_and_led, ARM_STBY, ARM_PWM);
			_movmentModule = new MovmentSystem(ioio_, _chasiss, _arm, WRIST_POT_PIN, SHOLDER_POT_PIN, ELBOW_POT_PIN, DISTANCE_PIN);
			System.out.println();
		}
		
		public void loop() throws ConnectionLostException {
			
		}
		
		public void startAlgo() throws ConnectionLostException {
			
		}
		
		
		

	}

	@Override
	protected IOIOLooper createIOIOLooper() {
		return new Looper();
	}
}