package frc.robot.subsystems;


import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    //Motorları tanımlıyoruz.
    private final SparkMax leftFront;
    private final SparkMax leftBack;
    private final SparkMax rightFront;
    private final SparkMax rightBack;

    private final DifferentialDrive drive;

    public DriveSubsystem(){
        //Motorlarımızı tanımlıyoruz. Motorların ID'leri constants klasöründe
        leftFront = new SparkMax(Constants.DriveConstants.LEFT_FRONT_ID, MotorType.kBrushed);
        leftBack = new SparkMax(Constants.DriveConstants.LEFT_BACK_ID, MotorType.kBrushed);
        rightFront = new SparkMax(Constants.DriveConstants.RIGHT_FRONT_ID, MotorType.kBrushed);
        rightBack= new SparkMax(Constants.DriveConstants.RIGHT_BACK_ID, MotorType.kBrushed);

        drive = new DifferentialDrive(leftFront,rightFront); //sürüş esnasında öndeki motorları sol ve sağ olarak tanımlıyoruz.


        //Motorların ayarlanması sırasında robota 25 saniye beklemesini eğer bu işlem 25 ten uzun sürerse hata vermesini söylüyoruz.
        //Bu sürüş yapılmıyorken yapılan ayarlamalar için geçerlidir.
        leftFront.setCANTimeout(250);
        leftBack.setCANTimeout(250);
        rightFront.setCANTimeout(250);
        rightBack.setCANTimeout(250);
        
        


    }
    @Override
    public void periodic() {
        
    }
    
    
    
    //Robotun ileri geri hareketini sağlar.
    public void driveArcade(double xSpeed, double zRotation){
        drive.arcadeDrive(xSpeed, zRotation); //squared ayarı joyistik ile alakalı olduğu için eklemedim.
    }

}
