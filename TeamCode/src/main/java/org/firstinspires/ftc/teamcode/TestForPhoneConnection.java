package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
TestForPhoneConnection TelOp is used to make sure that the android phones
are able to make a connection through the FTC apps. Connection will be
notified with Status: Initialized. When the run button is pressed the
notification will change to Status: Running.
*/

@TeleOp
public class TestForPhoneConnection extends LinearOpMode{

    private static DcMotor frontLeft;
    private static DcMotor backLeft;
    private static DcMotor frontRight;
    private static DcMotor backRight;

    //HardwareMap ahwMap = null;

    @Override
    public void runOpMode(){
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();
        }

    }

}