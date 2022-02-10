package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.exception.TargetPositionNotSetException;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

public class CargoBot_2021_Auto extends LinearOpMode {
    CargoBot_2021 robot = new CargoBot_2021();
    //The line above solves the robot. error, but it creates new error with the drive.set lines
    private ElapsedTime runtime = new ElapsedTime();
    // eg: AndyMark Orbital 20 Motor Encoder
    static final double COUNTS_PER_MOTOR_REV = 537.6;    // eg: AndyMark Orbital 20 Motor Encoder from Video
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP AndyMark Orbital 20
    static final double WHEEL_DIAMETER_INCHES = 3.75;     // For figuring circumference
    static final double WHEEL_CIRCUMFERENCE_INCHES = (WHEEL_DIAMETER_INCHES * Math.PI);
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415) / 1.01; //1.01 is a manual adjustment

    float[] hsvValues = {0F, 0F, 0F};

    @Override
    public void runOpMode (){
        //I have no clue what robot. is in this context, needs to be figured out as soon as possible
        //taken from previous year code
        robot.init(hardwareMap);

        //Resets encoders, needs to be changed to our variables
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

    }

    public void encoderDrive(double speed,
                             double dist, // in inches
                             char dir ) {
        int TIMEOUT = 10;

        int target;
        // Ensure that the opmode is still active
        try {
            if (opModeIsActive()) {
                // Determine new target position, and pass to motor controller

                target = (int) (dist * COUNTS_PER_INCH);
                // Decide which direction each motor should go
                if (dir == 'F') {
                    robot.frontLeft.setTargetPosition(target);
                    robot.frontRight.setTargetPosition(target);
                    robot.backLeft.setTargetPosition(target);
                    robot.backRight.setTargetPosition(target);
                } else if (dir == 'B') {
                    robot.frontLeft.setTargetPosition(-target);
                    robot.frontRight.setTargetPosition(-target);
                    robot.backLeft.setTargetPosition(-target);
                    robot.backRight.setTargetPosition(-target);
                } else if (dir == 'L') {
                    robot.frontLeft.setTargetPosition(-target);
                    robot.frontRight.setTargetPosition(target);
                    robot.backLeft.setTargetPosition(target);
                    robot.backRight.setTargetPosition(-target);
                } else if (dir == 'R') {
                    robot.frontLeft.setTargetPosition(target);
                    robot.frontRight.setTargetPosition(-target);
                    robot.backLeft.setTargetPosition(-target);
                    robot.backRight.setTargetPosition(target);
                }
                // Turn On RUN_TO_POSITION
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                // reset the timeout time and start motion.
                runtime.reset();
                robot.frontLeft.setPower(Math.abs(speed));
                robot.frontRight.setPower(Math.abs(speed));
                robot.backLeft.setPower(Math.abs(speed));
                robot.backRight.setPower(Math.abs(speed));
                while (opModeIsActive() &&
                        (runtime.seconds() < TIMEOUT) &&
                        (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy() && robot.backLeft.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d", target, target);
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            robot.frontLeft.getCurrentPosition(),
                            robot.frontRight.getCurrentPosition());
                    telemetry.addData("Back wheels", target + "" + target);
                    telemetry.update();
                }

                // Stop all motion
                robot.frontLeft.setPower(0);
                robot.frontRight.setPower(0);
                robot.backLeft.setPower(0);
                robot.backRight.setPower(0);
                // Turn off RUN_TO_POSITION
                robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                //  sleep(250);   // optional pause after each move
            }
        } catch (TargetPositionNotSetException e) {
            telemetry.addData("Mission Failed", "We'll get 'em next time: ");
            telemetry.update();
        }
    }

}
