package com.quark.terminal.terminal

import com.quark.terminal.pty.PtyNative
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.FileInputStream

class TerminalSession(private val rows: Int = 24, private val cols: Int = 80) {
    private val ptyNative = PtyNative()
    private var masterFd: Int = -1
    var isRunning: Boolean = false
        private set

    fun start() {
        masterFd = ptyNative.openPty(rows, cols)
        if (masterFd >= 0) {
            isRunning = true
        }
    }

    fun write(input: ByteArray) {
        if (masterFd >= 0) {
            // Write input to PTY master descriptor
        }
    }

    fun stop() {
        if (masterFd >= 0) {
            isRunning = false
            // Close master fd
        }
    }
}