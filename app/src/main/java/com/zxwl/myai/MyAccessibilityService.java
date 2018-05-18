package com.zxwl.myai;

import android.accessibilityservice.AccessibilityService;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.List;


/**
 * Created by sks on 2018/5/7.
 */

public class MyAccessibilityService extends BaseAccessibilityService {

    private CharSequence className;

    private Handler hann = new Handler();
    private Runnable runn = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void run() {
            checkbb();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        className = event.getClassName();
        Log.e("TTTTTT", className +"");
        if("com.tencent.mm.ui.LauncherUI".equals(className +"")){

        }else  if("com.tencent.mm.plugin.subapp.ui.friend.FMessageConversationUI".equals(className +"")){


        }else  if("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI".equals(className +"")){

            checkaa();

        }
        else  if("com.tencent.mm.plugin.profile.ui.ContactInfoUI".equals(className +"")){
            checkdd();
        }

    }

    @Override
    public void onInterrupt() {

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkdd() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo == null) {
            return;
        }
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("添加到通讯录");
        if (list.isEmpty()) {
            return;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            AccessibilityNodeInfo parent = list.get(i).getParent();
            Log.e("TTTTTAAABBDD",parent+"");
            if (parent != null) {
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkbb() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo == null) {
            return;
        }
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/j5");
        if (list.isEmpty()) {
            return;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            AccessibilityNodeInfo parent = list.get(i).getParent();
            Log.e("TTTTTAAABBCC",parent+"");
            if (parent != null) {
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkaa() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo == null) {
            return;
        }
        List<AccessibilityNodeInfo> list= nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/hx");
        if (list.isEmpty()) {
            return;
        }
        ClipData clip = ClipData.newPlainText("text", "15066306736");
        for (int i = list.size() - 1; i >= 0; i--) {
            AccessibilityNodeInfo parent = list.get(i).getParent();
            Log.e("TTTTTAAA",parent+"");
            if (parent != null) {
                AccessibilityNodeInfo accessibilityNodeInfo = parent.findFocus(AccessibilityNodeInfo.FOCUS_INPUT);
                boolean isFocuse = accessibilityNodeInfo.isFocused();
                ClipboardManager clipboard = (ClipboardManager)this.getSystemService(Context.CLIPBOARD_SERVICE);

                clipboard.setPrimaryClip(clip);
                //焦点（n是AccessibilityNodeInfo对象）
                accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                ////粘贴进入内容
                accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);


                hann.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkbb();
                    }
                },1000);

                break;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkKeyFirst() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo == null) {
            return;
        }
        List<AccessibilityNodeInfo> list1 = nodeInfo.findAccessibilityNodeInfosByText("通讯录");
        if (list1.isEmpty()) {
            return;
        }
        for (int i = list1.size() - 1; i >= 0; i--) {
            AccessibilityNodeInfo parent = list1.get(i).getParent();
            Log.e("TTTTTAAABB",parent+"");
            if (parent != null) {
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
        }

    }








    public void inputTexts(AccessibilityNodeInfo nodeInfo, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle arguments = new Bundle();
            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text);
            clipboard.setPrimaryClip(clip);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);
        }
    }

}