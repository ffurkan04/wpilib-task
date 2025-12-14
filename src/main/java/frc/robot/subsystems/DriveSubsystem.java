package frc.robot.subsystems;



import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkBase.PersistMode;
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
        
        SparkMaxConfig config = new SparkMaxConfig(); //Motorlar için bir config oluşturuyoruz.
        //Batarya sürüş esnasında tükenecek ve ilk baştaki aynı voltaj değerini veremeyecek 
        //Bu da robotun sürüşünden bir süre sonra yavaşlamaya başlamasına neden olacak
        //Bunun engellemek için voltageCompensation değerine gerekli olan değeri yazıyoruz. 
        config.voltageCompensation(Constants.DriveConstants.DRIVE_MOTOR_VOLTAGE_COMP); 

        //Eğer motorlar çok fazla akım çekmek isterlerse bu durum motorlara,kablolara ve roboRIO ya hasar verebilir 
        //Bu yüzden bunu kısıtlayıp fazla olursa güc kesmek için ayarlıyoruz. 
        config.smartCurrentLimit(Constants.DriveConstants.DRIVE_MOTOR_CURRENT_LIMIT);

        config.follow(leftFront); //Motorların uyumlu olması için öndeki motor ile aynı özelleştirmeleri takip edilmesi söyleniyor
        leftBack.configure(config, SparkBase.ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        config.follow(rightFront);
        rightBack.configure(config, SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        config.disableFollowerMode();
        rightFront.configure(config, SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        config.inverted(true); //sol taraf için config'i tersine uyguluyoruz
        leftFront.configure(config, SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    }
    @Override
    public void periodic() {
        
    }
    
    
    
    //Robotun ileri geri hareketini sağlar.
    public void driveArcade(double xSpeed, double zRotation){
        drive.arcadeDrive(xSpeed, zRotation); //squared ayarı joyistik ile alakalı olduğu için eklemedim.
    }

}
