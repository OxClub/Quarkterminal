package com.quark.terminal.pty

class PtyNative {
    companion object {
        init {
            System.loadLibrary("quarkpty")
        }
    }

    external fun openPty(rows: Int, cols: Int): Int
}