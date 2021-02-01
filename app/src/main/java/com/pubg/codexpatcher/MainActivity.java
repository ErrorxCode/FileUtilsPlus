package com.pubg.codexpatcher;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    RadioButton global;
    RadioButton korean;
    Switch ipad;
    Switch grass;
    Switch gravity;
    Switch color;
    Switch power;
    Switch fpp_glitch;
    SeekBar bullet;
    SeekBar aimbot;
    SeekBar headshot;
    SeekBar recoil;
    Button apply;

    protected final String noGrass =
            "\n" +
            "NO GRASS\n" +
            "[/Script/Engine.RendererOverrideSettings]\n" +
            "grass.heightScale=0\n" +
            "water.heightScale=0" +
            "\n" +
            "[SystemSettings]\n" +
            "r.Fog=0" +
            "\n" ;

    protected  final String Aimbot =
            "\n" +
                    "AIMBOT \n" +
            "[/Script.Engine.WeaponHitPartCoff] \n" +
            "Head=Max \n" +
            "Body=99\n" +
            "\n" +
            "PHYSICS SETTINGS\n" +
            "[/Script/Engine.PhysicsSettings] \n" +
            "DefaultRecoil=0 \n" +
            "DefaultSRecoilInfo=0 \n" +
            "DefaultRecoilKickADS=0 \n" +
            "DefaultRecoilADSRotation_CP=0 \n" +
            "DefaultOpenParachute=0 \n" +
            "DefaultHitBoxLeanTransR=0 \n" +
            "DefaultSeekAndLockTarget=5.0 \n" +
            "DefaultDamageMagnifierIdx=AspectRatio_MaintainsYFOV \n" +
            "DefaultDamageWeapons=AspectRatio_MaintainsYFOV \n" +
            "DefaultMovingSpeedZ=55 \n" +
            "DefaultHitEnemy=AspectRatio_MaintainsYFOV \n" +
            "EnableDamageInfo=AspectRatio_MaintainsYFOV\n" +
            "\n" +
            "[SystemAim]\n" +
            "WeaponAimYawRate=AspectRatio_MaintainsYFOV\n" +
            "CrossHairBurstIncreaseSpeed=3.0\n" +
            "BulletFireSpeed=AspectRatio_MaintainsYFOV\n" +
            "CrossHairInitialSize=1\n" +
            "ReloadTime=AspectRatio_MaintainsYFOV\n" +
            "ShootInterval=3.0\n" +
            "BulletRange=3.0\n" +
            "BurstShootBulletsNum=AspectRatio_MaintainsYFOV\n" +
            "WeaponAimFOV=2.0\n" +
            "AspectRatioAxisConstraint=AspectRatio_MaintainYFOV\n" +
            "SkeletalMeshLODBias=10\n" +
            "ParticleLODBias=-5\n" +
            "[ShootWeaponEntity]\n" +
            "GetBurstShootBullets=AspectRatio_MaintainsYFOV\n" +
            "GetBurstShootBulletsNum=AspectRatio_MaintainsYFOV\n" +
            "BurstShootBullets=AspectRatio_MaintainsYFOV\n" +
            "BurstShootBulletsNum=AspectRatio_MaintainsYFOV\n" +
            "BurstShootBulletsNumFromEntity=AspectRatio_MaintainsYFOV\n" +
            "GetBurstShootBulletsNumFromEntity=AspectRatio_MaintainsYFOV\n" +
            "HandleAlShootBulletHit=AspectRatio_MaintainsYFOV\n" +
            "OwnerClient_HandleAlShootBulletHit=AspectRatio_MaintainsYFOV\n" +
            "RPC_OwnerClient_HandleAlShootBulletHit=AspectRatio_MaintainsYFOV\n" +
            "CurBulletNumInClip=AspectRatio_MaintainsYFOV\n" +
            "FIRING RATE\n" +
            "[/Script.Engine.AutoAimingRangeConfig] \n" +
            "Speed=Max \n" +
            "RangeRate=Max \n" +
            "SpeedAim=Max" +
            "\n" ;

    protected final String BlackSky ="\n" +
            "[SystemSettings]\n" +
            "r.CylinderMaxDrawHeight=9999\n" +
            "\n \n" +
            "[ConsoleVariables]\n" +
            "r.CharacterMinShadowFactor=100";

    protected final String ZeroGravity =
            "\n" +
            "[/Script/Engine.PhysicsSettings]\n" +
            "DefaultTerminalVelocity=0f\n" +
                    "DefaultGravityZ=0f\n" +
            "\n \n" +
                    "[ConsoleVariables]\n" +
                    "r.Android.DisableVulkanSupport=0";

    protected final String iPad =
            "\n" +
            "WIDE VIEW\n" +
            "[/Script/Engine.LocalPlayer]\n" +
            "AspectRatioAxisConstraint=0\n" +
                    "\n" ;

    protected final String noLog = "[UserCustom DeviceProfile]\n" +
            "Core.Log\n" +
            "LogOnline=off\n" +
            "LogOnlineGame=off\n" +
            "LogHttp=off\n" +
            "LogSTEOnlineGame=off\n" +
            "LogCircle=off\n" +
            "LogItemGeneratorBase=off\n" +
            "LogBulletHitImpact=off\n" +
            "LogGCloud=off\n" +
            "LogClass=off\n" +
            "LogSTCharMoveSpecial=off\n" +
            "LogAntiCheat=off\n" +
            "\n" +
            "ShippingCore.Log\n" +
            "LogInit=off\n" +
            "LogTaskGraph=off\n" +
            "LogDevObjectVersion=off\n" +
            "LogMemory=off\n" +
            "LogTextLocalizationManager=off\n" +
            "LogObj=off\n" +
            "LogExit=off\n" +
            "LogPlatformFile=off\n" +
            "LogOnline=off\n" +
            "LogOnlineGame=off\n" +
            "LogHttp=off\n" +
            "LogSTEOnlineGame=off\n" +
            "LogCircle=off\n" +
            "LogItemGeneratorBase=off\n" +
            "LogBulletHitImpact=off\n" +
            "LogTemp=off\n" +
            "LogScriptPlugin=off\n" +
            "LogUMG=off\n" +
            "LogSecurityInfoCollector=off\n" +
            "AttrModifyComponent=off\n" +
            "LogNearDeath=off\n" +
            "LogSkinnedMeshComp=off\n" +
            "LogNetPartialBunch=off\n" +
            "LogDoor=off\n" +
            "LogBackPack=off\n" +
            "LogPickUp=off\n" +
            "LogIOS=off\n" +
            "LogAndroid=off\n" +
            "LogGCloud=off\n" +
            "LogGameInfo=off\n" +
            "LogNet=off\n" +
            "LogAirAttack=off\n" +
            "LogSTCharacterMovement=off\n" +
            "LogWeaponImportant=off\n" +
            "LogClient=off\n" +
            "LogAvatar=off\n" +
            "LogLandscape=off\n" +
            "LogMidasInterface=off\n" +
            "LogNula=off\n" +
            "LogChangeWearing=off\n" +
            "LogSTCharMoveSpecial=off\n" +
            "LogParticleCache=off\n" +
            "LogVehicle=off\n" +
            "LogVehicleSync=off\n" +
            "LogSkillEditorSkill=off\n" +
            "LogSkillPoolManager=off\n" +
            "LogAIActing=off\n" +
            "LogSTExtraPetCharacter=off\n" +
            "LogCharacterState=off\n" +
            "LogCharacterDamage=off\n" +
            "LogCharParachute=off\n" +
            "LogPetAnimInstance=off\n" +
            "LogPetEventManagerComponent=off\n" +
            "LogNetPlayerMovement=off\n" +
            "LogAntiCheat=off\n" +
            "LogRep=off\n" +
            "LogFPP=off\n" +
            "LogTimeLineSync=off\n" +
            "LogSecurityCoronaLab=off\n" +
            "LogGeneratorItem=off\n" +
            "LogGeneratorTriggerItem=off\n" +
            "LogCharAnim=off\n" +
            "LogParachuteAnimComp=off\n" +
            "LogSTExtraAnimInstance=off\n" +
            "LogSTExtraVehicleAnimInstance=off\n" +
            "LogMonsterAnimInstance=off\n" +
            "LogSimpleAnimList=off\n" +
            "LogInfectionAnimList=off\n" +
            "LogPlayEmote=off\n" +
            "LogLobbyPlayEmoteCom=off\n" +
            "LogActivity=off\n" +
            "LogSpotGroupObject=off\n" +
            "LogFilterConfig=off\n" +
            "LogCharacterParachute=off\n" +
            "MyLandscape=off\n" +
            "PandoraSlua=off\n" +
            "LogSkill=off\n" +
            "LogLevelStreaming=off\n" +
            "LogAkAudio=off\n" +
            "LogGarbage=off\n" +
            "LogTaskTrigger=off\n" +
            "LogWeapon=off\n" +
            "LogWeaponNew=off\n" +
            "LogBackPackComp=off\n" +
            "LogGameplay=off";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apply = findViewById(R.id.apply);
        bullet = findViewById(R.id.bullet);
        grass = findViewById(R.id.grass);
        color = findViewById(R.id.color_mod);
        power = findViewById(R.id.power);
        fpp_glitch = findViewById(R.id.fpp_glitch);
        gravity = findViewById(R.id.zero_gravity);
        global = findViewById(R.id.global);
        korean = findViewById(R.id.korean);
        ipad = findViewById(R.id.ipad);
        aimbot = findViewById(R.id.aimbot);
        recoil = findViewById(R.id.less_recoil);
        headshot = findViewById(R.id.headshot);


        GradientDrawable custom = new GradientDrawable();
        custom.setCornerRadius(5);
        apply.setBackground(custom);

 //                                                           Getting the app settings
        setSwitch(ipad,fpp_glitch,gravity,grass,color,power);
        enablePower();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                setPower();
                setMod();
                launchPUBG();
                finishAffinity();


                saveSwitch(ipad,fpp_glitch,gravity,grass,color,power);
            }
        });

        FileManager.oneTimeEvent(this, new FileManager.OneTimeEventInterface() {
            @Override
            public void OneTimeEvent() {
                if (new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.pubg.krmobile").isDirectory()){
                    FileManager.delete(MainActivity.this,Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.pubg.krmobile/cache");
                    FileManager.delete(MainActivity.this,Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Logs");
                    new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config").mkdir();
                    try {
                        new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserLogSuppression.ini").createNewFile();
                        new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini").createNewFile();
                        FileManager.writeFile(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserLogSuppression.ini",noLog,true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.tencent.ig").isDirectory()){
                    FileManager.delete(MainActivity.this,Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.tencent.ig/cache");
                    FileManager.delete(MainActivity.this,Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Logs");
                    new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config").mkdir();
                    try {
                        new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserLogSuppression.ini").createNewFile();
                        new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini").createNewFile();
                        FileManager.writeFile(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserLogSuppression.ini",noLog,true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(MainActivity.this, "Setup Completed", Toast.LENGTH_SHORT).show();
            }
        });


        global.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_HOLO_DARK).setCancelable(false).setTitle("Exception").setMessage("This app will not work if you have \"PUBG GLOBAL 600mb\" version.As this version do not use obb.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            global.setChecked(true);
                        }
                    }).setNegativeButton("Use korean", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            global.setChecked(false);
                            korean.setChecked(true);
                        }
                    }).create().show();
                }
            }
        });

        fpp_glitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManager.oneTimeDialog(MainActivity.this, new FileManager.OneTimeDialogInterface() {
                    @Override
                    public void OneTimeDialog(AlertDialog.Builder builder) {
                        builder.setTitle("Tutorial").setMessage("-> Click on FPP swap button to see through walls.\nWorks best on low-end device").setPositiveButton("ok",null).create().show();
                    }
                });
            }
        });

    }


    private void launchPUBG() {
        if (global.isChecked()) {
            Intent intent_global = getPackageManager().getLaunchIntentForPackage("com.tencent.ig");
            if (intent_global == null || intent_global.toString().isEmpty()){
                Toast.makeText(this, "Selected Version Not Installed", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(intent_global);
            }

        } else if (korean.isChecked()) {
            Intent intent_korean = getPackageManager().getLaunchIntentForPackage("com.pubg.krmobile");
            if (intent_korean == null || intent_korean.toString().isEmpty()){
                Toast.makeText(this, "Selected Version Not Installed", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(intent_korean);
            }

        } else {
            Toast.makeText(this, "Select any one version", Toast.LENGTH_SHORT).show();
        }
    }


    private void setMod() {
        if (ipad.isChecked()) {
            if (global.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", iPad,false);
            } else if (korean.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", iPad,false);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (global.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", iPad);
            } else if (korean.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", iPad);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        }

        if (grass.isChecked()) {
            if (global.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", noGrass,false);
            } else if (korean.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", noGrass,false);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (global.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", noGrass);
            } else if (korean.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", noGrass);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        }

        if (color.isChecked()) {
            if (global.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", BlackSky,false);
            } else if (korean.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", BlackSky,false);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (global.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", BlackSky);
            } else if (korean.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", BlackSky);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        }

        if (gravity.isChecked()) {
            if (global.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", ZeroGravity,false);
            } else if (korean.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", ZeroGravity,false);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (global.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", ZeroGravity);
            } else if (korean.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", ZeroGravity);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        }

        if (power.isChecked()) {
            if (global.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", Aimbot,false);
            } else if (korean.isChecked()) {
                FileManager.writeFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", Aimbot,false);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (global.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", Aimbot);
            } else if (korean.isChecked()) {
                FileManager.eraseFile(Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Config/UserEngine.ini", Aimbot);
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        }

        if (fpp_glitch.isChecked()) {
            if (global.isChecked()) {
                FileManager.copyFromAssets(this,"Active.sav",Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGame");
            } else if (korean.isChecked()) {
                FileManager.copyFromAssets(this,"Active.sav",Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGame");
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (global.isChecked()) {
                FileManager.delete(this,Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGame/Active.sav");
            } else if (korean.isChecked()) {
                FileManager.delete(this,Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGame/Active.sav");
            } else {
                Toast.makeText(this, "Select Any one version", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void setPower() {
        if (aimbot.getProgress() > 0 || bullet.getProgress() > 0 || headshot.getProgress() > 0 || recoil.getProgress() > 0) {
            if (global.isChecked()) {
                 FileManager.copy(getExternalFilesDir(null).getPath() + "/Active.sav", Environment.getExternalStorageDirectory() + "/Android/data/com.tencent.ig/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames");
            } else if (korean.isChecked()) {
                FileManager.copy(getExternalFilesDir(null).getPath() + "/Active.sav", Environment.getExternalStorageDirectory() + "/Android/data/com.pubg.krmobile/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames");
            } else {
                Toast.makeText(this, "Select any one version", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Mod applied", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveSwitch(@SuppressLint("UseSwitchCompatOrMaterialCode") Switch ipad, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch grass, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch gravity, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch power, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch wall, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch color){
        SharedPreferences.Editor editor = getSharedPreferences("switch",MODE_PRIVATE).edit();
        editor.putBoolean("ipad",ipad.isChecked()).apply();
        editor.putBoolean("grass",grass.isChecked()).apply();
        editor.putBoolean("color",color.isChecked()).apply();
        editor.putBoolean("gravity",gravity.isChecked()).apply();
        editor.putBoolean("wall",wall.isChecked()).apply();
        editor.putBoolean("power",power.isChecked()).apply();
    }

    private void setSwitch(@SuppressLint("UseSwitchCompatOrMaterialCode") Switch ipad, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch grass, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch gravity, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch power, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch wall, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch color){
        ipad.setChecked(getSharedPreferences("switch",MODE_PRIVATE).getBoolean("ipad",false));
        wall.setChecked(getSharedPreferences("switch",MODE_PRIVATE).getBoolean("wall",false));
        grass.setChecked(getSharedPreferences("switch",MODE_PRIVATE).getBoolean("grass",false));
        color.setChecked(getSharedPreferences("switch",MODE_PRIVATE).getBoolean("color",false));
        power.setChecked(getSharedPreferences("switch",MODE_PRIVATE).getBoolean("power",false));
        gravity.setChecked(getSharedPreferences("switch",MODE_PRIVATE).getBoolean("gravity",false));
    }

    private void enablePower(){
        aimbot.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                power.setEnabled(aimbot.getProgress() > 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        recoil.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                power.setEnabled(recoil.getProgress() > 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bullet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                power.setEnabled(bullet.getProgress() > 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        headshot.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                power.setEnabled(headshot.getProgress() > 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }

}