package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*
Drive LinearOpMode is used as a basis for all our wheel movement throughout the robot.
More components will be added as new robot mechanics are created.

The TeleOp has two states. Precision and Normal. Normal will allow for full control limits,
while precision uses 1/4 the power for even more precise motion.

NICKS COMMENT: (be proud)
Beam stands for the Linear Slides
Claw stands for the attachment at the end of the linear slides
*/

@TeleOp(name = "Drive and Claw Control", group = "CargoBot")
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
        boolean precisionMode = false;
        int clawWristPos = 0;
        int clawHandPos = 0;
        double beamLengthPower = 0.2;
        double beamAnglePower = 0.2;
        double clawWristPosAddable = 0.2;
        telemetry.addData("opModeIsActive", opModeIsActive());
        telemetry.update();
        // Run until the end of the match (driver presses STOP)
        waitForStart();
        while (opModeIsActive()) {
            double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            // Precision Mode Toggle
            if (gamepad1.a && precisionMode) {
                //On
                precisionMode = false;
                //One Time Changes For Precision Mode
                clawWristPosAddable = 0.1;
                beamAnglePower = 0.1;
                beamLengthPower = 0.1;
            } else if(gamepad1.a && !precisionMode) {
                //Off
                precisionMode = true;
                //One Time Changes Back to Precision Mode
                clawWristPosAddable = 0.2;
                beamAnglePower = 0.2;
                beamLengthPower = 0.2;
            }

            // Precision Mode For Motors
            if (precisionMode) {
                // Precision Mode
                telemetry.addData("Precision", "true");
                telemetry.update();
                robot.frontLeft.setPower(v1 / 4);
                robot.frontRight.setPower(v2 / 4);
                robot.backLeft.setPower(v3 / 4);
                robot.backRight.setPower(v4 / 4);

            } else {
                // Normal Mode
                robot.frontLeft.setPower(v1);
                robot.frontRight.setPower(v2);
                robot.backLeft.setPower(v3);
                robot.backRight.setPower(v4);
            }

            //GamePad 2: Claw Control
            //Changes the angle of the claw wrist
            if(gamepad2.left_stick_y > 0){
                clawWristPos -= clawWristPosAddable;
            } else if (gamepad2.left_stick_y < 0){
                clawWristPos += clawWristPosAddable;
            }

            //Changes the beam height
            if(gamepad2.dpad_up){
                robot.setBeamArmRLPower(beamAnglePower);
            } else if(gamepad2.dpad_down) {
                robot.setBeamArmRLPower(beamAnglePower);
            }

            if (gamepad2.right_trigger>.2) {
                robot.setClawHandOpenClose(180);
            }

            if(gamepad2.dpad_left){
                robot.setBeamArmLengthPower(-beamLengthPower);
            } else if(gamepad2.dpad_right) {
                robot.setBeamArmLengthPower(beamLengthPower);
            } else {
                robot.setBeamArmLengthPower(0);
            }

            robot.setClawWristPos(clawWristPos);
            }


        }
}
