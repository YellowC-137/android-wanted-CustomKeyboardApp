package com.preonboarding.customkeyboard.keyboard.hyewon

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.preonboarding.customkeyboard.databinding.ViewKeyboardBinding
import com.preonboarding.customkeyboard.databinding.ViewKeyboardHyewonBinding

class KeyboardService : InputMethodService() {

    private lateinit var linearLayout: LinearLayout
    private lateinit var frameLayout: FrameLayout
    private lateinit var keyboard: Keyboard
    private lateinit var binding: ViewKeyboardHyewonBinding

    override fun onCreate() {
        super.onCreate()
        binding = ViewKeyboardHyewonBinding.inflate(layoutInflater)
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
        val keyboardInteractionListener = object : KeyboardInteractionListener {
            override fun addView() {
                currentInputConnection.finishComposingText()
                frameLayout.removeAllViews()
                keyboard.inputConnection = currentInputConnection
                frameLayout.addView(keyboard.getLayout())
            }
        }
        keyboardInteractionListener.addView()
    }
}