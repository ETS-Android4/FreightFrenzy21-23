package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class CargoBot_2021 {



        public DcMotor frontLeft;
        public DcMotor frontRight;
        public DcMotor backLeft;
        public DcMotor backRight;
        public Servo clawHead;
        public Servo clawArm;

        public CargoBot_2021(){
        }

        HardwareMap hwMap;

        public void init(HardwareMap ahwMap) {

            hwMap = ahwMap;
            frontLeft = hwMap.get(DcMotor.class, "frontLeft");
            frontRight = hwMap.get(DcMotor.class, "frontRight");
            backLeft = hwMap.get(DcMotor.class, "backLeft");
            backRight = hwMap.get(DcMotor.class, "backRight");
            clawHead = hwMap.get(Servo.class, "clawHead");
            clawArm = hwMap.get(Servo.class, "clawArm");
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

        public void setClawHeadPos(double pos){
            clawHead.setPosition(pos);
        }
        public void setClawArmPos(double pos){
            clawArm.setPosition(pos);
        }

}
