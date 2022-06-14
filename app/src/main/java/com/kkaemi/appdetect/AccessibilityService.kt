package com.kkaemi.appdetect

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class AccessibilityService : AccessibilityService() {
    var check = false
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event!!.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Log.v("detect!!!", "detect!!!"+event.packageName)
            if("com.coupang.mobile" == event.packageName){
                if(!check) Toast.makeText(this.applicationContext, "네이버 웹툰 앱 실행 !", Toast.LENGTH_SHORT).show()

                check = true
            }
            else {
                if ("com.samsung.android.honeyboard" != event.packageName) check = false
            }
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
    }

    override fun onInterrupt() {
       Log.v("detect !!!!!", "onInterrupt")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

}