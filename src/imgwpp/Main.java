package imgwpp;

import java.io.IOException;

import com.mat.imgwpp.robot.Robot;

public class Main {

	public static void main(String[] args) {
		Robot robot = new Robot();
		try {
			robot.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
