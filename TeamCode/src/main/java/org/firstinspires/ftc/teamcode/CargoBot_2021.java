package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotor;

/*
CargoBot_2021 is a class to instantiate all motors, sensors and servos used
throughout the robot. All settings for the robot are given when self.init()
is run. All actions are made when self."action"("args") is run.
*/

public class CargoBot_2021 {

        public DcMotor frontLeft;
        public DcMotor frontRight;
        public DcMotor backLeft;
        public DcMotor backRight;

        public CargoBot_2021(){
        }

        HardwareMap hwMap;

        public void init(HardwareMap ahwMap) {

            hwMap = ahwMap;
            frontLeft = hwMap.get(DcMotor.class, "frontLeft"); // Used in motor port 2
            frontRight = hwMap.get(DcMotor.class, "frontRight"); // Used in motor port 0
            backLeft = hwMap.get(DcMotor.class, "backLeft"); // Used in motor port 3
            backRight = hwMap.get(DcMotor.class, "backRight"); // Used in motor port 1
            // Set motor direction
            frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
            frontRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
            backLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
            backRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
            // Set all motors to zero power
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

        }

}
