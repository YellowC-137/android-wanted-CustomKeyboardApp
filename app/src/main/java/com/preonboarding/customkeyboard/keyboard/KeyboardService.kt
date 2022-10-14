package com.preonboarding.customkeyboard.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.myhome.rpgkeyboard.KeyboardInterationListener
import com.preonboarding.customkeyboard.databinding.ViewKeyboardBinding

class KeyboardService : InputMethodService() {

    lateinit var linearLayout: LinearLayout
    lateinit var frameLayout: FrameLayout
    lateinit var keyboard: Keyboard
    lateinit var binding: ViewKeyboardBinding
    val keyboardInterationListener = object : KeyboardInterationListener {
        override fun addView() {
            currentInputConnection.finishComposingText()
            frameLayout.removeAllViews()
            keyboard.inputConnection = currentInputConnection
            frameLayout.addView(keyboard.getLayout())
        }
    }

    override fun onCreate() {
        super.onCreate()
        binding = ViewKeyboardBinding.inflate(layoutInflater)
    }
    override fun onCreateInputView(): View {
        linearLayout = binding.linearlayoutKeyboard
        frameLayout = binding.framelayoutKeyboard
        keyboard =
            Keyboard(applicationContext, layoutInflater)
        keyboard.inputConnection = currentInputConnection
        keyboard.init()
        return binding.root
    }

    override fun updateInputViewShown() {
        super.updateInputViewShown()
        currentInputConnection.finishComposingText()
        keyboardInterationListener.addView()
    }
}