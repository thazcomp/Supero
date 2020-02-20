package br.com.reporeader

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import java.io.Serializable

fun Activity.goToActivity(activityClass: Class<*>) {
    startActivity(Intent(this, activityClass))
}