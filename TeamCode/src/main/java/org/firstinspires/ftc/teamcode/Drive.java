package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "CargoBot: Teleop POV", group = "CargoBot")
public class Drive extends LinearOpMode {
    // Declare OpMode members.
    CargoBot_2021 robot = new CargoBot_2021();

    @Override
    public void runOpMode() {
        // Save reference to Hardware map
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);
        // Wait for the game to start (driver presses PLAY)
        //waitForStart();
        boolean precisionMode = false;
        telemetry.addData("opModeIsActive", opModeIsActive());
        telemetry.update();
        // Run until the end of the match (driver presses STOP)
        waitForStart();
        while (opModeIsActive()) {
            telemetry.update();
            double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            // Precision Mode
            if (precisionMode) {
                telemetry.addData("Precision", "true");
                telemetry.update();
                robot.frontLeft.setPower(v1 / 4);
                robot.frontRight.setPower(v2 / 4);
                robot.backLeft.setPower(v3 / 4);
                robot.backRight.setPower(v4 / 4);
            } else {
                // Normal Mode
                //telemetry.addData("Driving", "true");
                //telemetry.update()
                robot.frontLeft.setPower(v1);
                robot.frontRight.setPower(v2);
                robot.backLeft.setPower(v3);
                robot.backRight.setPower(v4);
            }

            // Precision Mode Toggle
            if (gamepad1.b) {
                precisionMode = false;
            } else if (gamepad1.x) {
                precisionMode = true;
            }
        }
    }
}