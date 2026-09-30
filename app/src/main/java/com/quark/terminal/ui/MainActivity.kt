package com.quark.terminal.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.quark.terminal.terminal.TerminalSession

class MainActivity : AppCompatActivity() {
    private lateinit var terminalView: TextView
    private val session = TerminalSession()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        terminalView = TextView(this).apply {
            text = "Welcome to QUARK TERMINAL\nReal Linux Workstation Initialized.\nroot@quark:~# "
            setTextColor(0xFF00FFCC.toInt())
            setBackgroundColor(0xFF0A0E17.toInt())
            textSize = 14f
            setPadding(24, 24, 24, 24)
            typeface = android.graphics.Typeface.MONOSPACE
        }
        setContentView(terminalView)

        session.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        session.stop()
    }
}