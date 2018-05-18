package com.zxwl.myai;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private boolean b;
    private String abc;
    /**
     * 请输入手机号
     */
    private EditText mPhone;
    /**
     * 请输入密码
     */
    private EditText mPass;
    private ListView mListview;
    /**
     * 退出微信登录
     */
    private Button mExit;
    /**
     * 登录微信账号
     */
    private Button mLogin;
    /**
     * 添加好友手机号码逗号隔开
     */
    private EditText mPhonenumber;
    /**
     * 自动添加好友
     */
    private Button mZdh;
    /**
     * 保存微信号码
     */
    private Button mBcphone;
    /**
     * 设置间隔时间纯数字
     */
    private EditText mTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        startActivity(new Intent(this,Main2Activity.class));


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.e("AAAAAA", x + "---" + y);
        return super.onTouchEvent(event);


    }

    private void initView() {

        mPhone = (EditText) findViewById(R.id.phone);
        mPass = (EditText) findViewById(R.id.pass);
        mListview = (ListView) findViewById(R.id.listview);
        mExit = (Button) findViewById(R.id.exit);
        mExit.setOnClickListener(this);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mPhonenumber = (EditText) findViewById(R.id.phonenumber);
        mZdh = (Button) findViewById(R.id.zdh);
        mZdh.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        mPass.setOnClickListener(this);
        mBcphone = (Button) findViewById(R.id.bcphone);
        mBcphone.setOnClickListener(this);
        mPhonenumber.setOnClickListener(this);
        mTime = (EditText) findViewById(R.id.time);
    }



    private boolean serviceIsRunning() {
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Short.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : services) {
            Log.e("aaaaa", info.service.getClassName());
            if (info.service.getClassName().equals("com.zxwl.myai.MyAccessibilityService")) {
                return true;
            }
        }
        return false;
    }


    public void showClickArea(int area) {
        Log.e("TTTT", area + "123");
        Toast.makeText(MainActivity.this, "您点击到了第" + area + "块区域！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
//
//            case R.id.button:
//
//                if (b) {
//                    try {
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
//                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.setComponent(cmp);
//                        startActivity(intent);
//                    } catch (ActivityNotFoundException e) {
//                        // TODO: handle exception
//                        Toast.makeText(this, "检查到您手机没有安装微信,请安装后使用该功能", Toast.LENGTH_SHORT).show();
//
//                    }
//                } else {
//                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//                    startActivity(intent);
//                    Toast.makeText(MainActivity.this, "找到抢红包，然后开启服务即可", Toast.LENGTH_LONG).show();
//                }
//
//
//                break;
            case R.id.exit:
                String[] search = {

                        "input keyevent 3",// 返回到主界面，数值与按键的对应关系可查阅KeyEvent
                        "sleep 2",// 等待1秒
                        "am start -n com.tencent.mm/com.tencent.mm.ui.LauncherUI",// 打开微信的启动界面，am命令的用法可自行百度、Google
                        "sleep 2",// 等待1秒
                        "am start -n com.tencent.mm/com.tencent.mm.plugin.setting.ui.setting.SettingsUI",// 打开微信的搜索

                        "sleep 2",
                        "input tap 300 1000",

                        "sleep 2",
                        "input tap 300 1150",

                        "sleep 2",
                        "input tap 550 720",

                };
                execShell(search);
                break;
            case R.id.login:
                String[] search3 = {

                        "input keyevent 3",// 返回到主界面，数值与按键的对应关系可查阅KeyEvent
                        "sleep 2",// 等待1秒
                        "am start -n com.tencent.mm/com.tencent.mm.ui.LauncherUI",// 打开微信的启动界面，am命令的用法可自行百度、Google
                        "sleep 2",// 等待1秒
                        "am start -n com.tencent.mm/com.tencent.mm.plugin.account.ui.LoginPasswordUI",// 打开微信的搜索

                        "sleep 2",
                        "input tap 470 1240",

                        "sleep 2",
                        "input tap 300 1080",
                        "sleep 2",
                        "input text " + "18210909941",
                        "sleep 2",
                        "input tap 300 560",
                        "sleep 2",
                        "input text " + "wyz123.0",
                        "sleep 2",
                        "input tap 300 550",

                };
                execShell(search3);
                break;
            case R.id.zdh:




                    String[] split = mPhonenumber.getText().toString().split(",");
                    for (int x = 0; x < split.length; x++) {
                        abc = split[x];
                        Log.e("TTTT", abc);
                        if(abc.length()==11){
                        String[] search1 = {
                                "sleep 5",
                                "input keyevent 3",// 返回到主界面，数值与按键的对应关系可查阅KeyEvent
                                "sleep 2",// 等待1秒
                                "am start -n com.tencent.mm/com.tencent.mm.ui.LauncherUI",// 打开微信的启动界面，am命令的用法可自行百度、Google
                                "sleep 2",// 等待1秒
                                "am start -n com.tencent.mm/com.tencent.mm.plugin.fts.ui.FTSAddFriendUI",// 打开微信的搜索
                                "input text " + abc,// 像搜索框中输入123，但是input不支持中文，蛋疼，而且这边没做输入法处理，默认会自动弹出输入法
                                "sleep 2",
                                "input tap 300 152",
                                "sleep 3",
                                "input tap 300 452",
                                "input tap 300 502",
                                "input tap 300 552",
                                "input tap 300 602",
                                "sleep 2",
                                "input tap 675 52",
                                "sleep 4",
                                "input keyevent 4",
                                "input keyevent 4",
                                "input keyevent 4",
                        };
                        execShell(search1);
                        try {
                            String s = mTime.getText().toString();
                            int i = Integer.parseInt(s);
                            Thread.currentThread().sleep(i*1000);//阻断2秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        }else{

                        }
                    }




                break;
            case R.id.phone:
                break;
            case R.id.pass:
                break;
            case R.id.bcphone:
                if("".equals(mPhone.getText().toString())&&"".equals(mPass.getText().toString())){
                    SharedPreferences preferences=getSharedPreferences("user",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("name", mPhone.getText().toString());
                    editor.putString("pass", mPass.getText().toString());
                    editor.commit();
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "请输入正确号码和密码", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.listview:


                break;
            case R.id.phonenumber:


                break;
        }
    }

    public void execShell(String[] commands) {

        Toast.makeText(this, "请停止操作五秒后开始微信好友添加程序", Toast.LENGTH_SHORT).show();
        // 获取Runtime对象
        Runtime runtime = Runtime.getRuntime();

        DataOutputStream os = null;
        try {
            // 获取root权限，这里大量申请root权限会导致应用卡死，可以把Runtime和Process放在Application中初始化
            Process process = runtime.exec("su");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }

                // donnot use os.writeBytes(commmand), avoid chinese charset
                // error
                os.write(command.getBytes());
                os.writeBytes("\n");
                os.flush();
            }
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 64      * 把中文转成Unicode码
     * 65      * @param str
     * 66      * @return
     * 67
     */
    public String chineseToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {//汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 82      * 判断是否为中文字符
     * 83      * @param c
     * 84      * @return
     * 85
     */
    public boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    private void execShellCmd(String cmd) {

        try {
            // 申请获取root权限，这一步很重要，不然会没有作用
            Process process = Runtime.getRuntime().exec("su");
            // 获取输出流
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
