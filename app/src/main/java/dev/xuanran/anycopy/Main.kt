package dev.xuanran.anycopy

import android.content.Context
import android.widget.Button
import android.widget.TextView
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class Main : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {

        XposedBridge.hookAllConstructors(TextView::class.java, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam?) {
                super.afterHookedMethod(param)
                val args = param?.args
                if (args != null && args.isNotEmpty() && args[0] is Context) {
                    val textView = param.thisObject as TextView
                    textView.setTextIsSelectable(true)
                }
            }
        })


        XposedBridge.hookAllConstructors(Button::class.java, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam?) {
                super.beforeHookedMethod(param)
                val args = param?.args
                if (args != null && args.isNotEmpty() && args[0] is Context) {
                    val button = param.thisObject as Button
                    button.setTextIsSelectable(true)
                }
            }
        })

        XposedHelpers.findAndHookMethod(
            TextView::class.java,
            "setTextIsSelectable",
            Boolean::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    param!!.args[0] = true
                }
            })

    }
}