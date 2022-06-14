package com.kkaemi.appdetect

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!checkAccessibilityPermissions()){
            setAccessibilityPermissions()
        }

    }

    fun checkAccessibilityPermissions(): Boolean {
        val accessibilityManager = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
        val list =
            accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
        for (info in list) {
            if (info.resolveInfo.serviceInfo.packageName == application.packageName) {
                return true
            }
        }
        return false
    }

    fun setAccessibilityPermissions() {
        val permissionDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        permissionDialog.setTitle("접근성 권한 설정")
        permissionDialog.setMessage("앱을 사용하기 위해 접근성 권한이 필요합니다.")
        permissionDialog.setPositiveButton("허용",
            DialogInterface.OnClickListener { dialog, which ->
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                return@OnClickListener
            }).create().show()
    }
}