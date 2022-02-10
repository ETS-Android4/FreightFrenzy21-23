package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class CargoBot_2021 {



        public DcMotor frontLeft;
        public DcMotor frontRight;
        public DcMotor backLeft;
        public DcMotor backRight;
        public Servo clawWrist;
        public Servo clawHeadOpenClose;
        public DcMotor beamExtend;
        public DcMotor beamRL;

        public CargoBot_2021(){
        }

        HardwareMap hwMap;

        public void init(HardwareMap ahwMap) {

            hwMap = ahwMap;
            frontLeft = hwMap.get(DcMotor.class, "frontLeft");
            frontRight = hwMap.get(DcMotor.class, "frontRight");
            backLeft = hwMap.get(DcMotor.class, "backLeft");
            backRight = hwMap.get(DcMotor.class, "backRight");
            clawWrist = hwMap.get(Servo.class, "clawWrist");
            clawHeadOpenClose = hwMap.get(Servo.class, "clawHeadOpenClose");
            beamExtend = hwMap.get(DcMotor.class, "beamExtend");
            beamRL = hwMap.get(DcMotor.class,"beamRL");
            // Set motor direction
            frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
            frontRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
            backLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
            backRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
            beamExtend.setDirection(DcMotor.Direction.FORWARD);
            beamRL.setDirection(DcMotor.Direction.FORWARD);
            // Set all motors to zero power
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            beamExtend.setPower(0);
            beamRL.setPower(0);

        }

        public void setClawWristPos(double pos){clawWrist.setPosition(pos);}
        public void setBeamArmLengthPower(double power){beamExtend.setPower(power);}
        public void setBeamArmRLPower(double power){beamExtend.setPower(power);}
        public void setClawHeadOpenClose(boolean open){ //True sets to open and False sets to close
            if(open){
                //Open Claw
                clawHeadOpenClose.setPosition(.5);
            } else {
                //Close Claw
                clawHeadOpenClose.setPosition(0);
            }
        }

}
