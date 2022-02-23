package com.example.exchangethis.utils

import android.widget.Button
import java.util.regex.Pattern

class MyUtils {
    companion object {
        fun isMobileValid(mobile: String): Boolean {
            val pattern =
                Pattern.compile("^\\+375\\(?(17|29|33|44)\\)?[0-9]{3}-?[0-9]{2}-?[0-9]{2}\$")
            val matcher = pattern.matcher(mobile)
            return (matcher.find() && matcher.group().equals(mobile))
        }

        fun enableButton(
            button1: Button,
            button2: Button,
            button3: Button,
            button4: Button,
            button5: Button,
            button6: Button
        ) {
            button1.isEnabled = true
            button1.isClickable = true
            button2.isEnabled = true
            button2.isClickable = true
            button3.isEnabled = true
            button3.isClickable = true
            button4.isEnabled = true
            button4.isClickable = true
            button5.isEnabled = true
            button5.isClickable = true
            button6.isEnabled = true
            button6.isClickable = true
        }

        fun disableButton(
            button1: Button,
            button2: Button,
            button3: Button,
            button4: Button,
            button5: Button,
            button6: Button
        ) {
            button1.isEnabled = false
            button1.isClickable = false
            button2.isEnabled = false
            button2.isClickable = false
            button3.isEnabled = false
            button3.isClickable = false
            button4.isEnabled = false
            button4.isClickable = false
            button5.isEnabled = false
            button5.isClickable = false
            button6.isEnabled = false
            button6.isClickable = false
        }
    }
}