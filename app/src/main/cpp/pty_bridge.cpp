#include <jni.h>
#include <string>
#include <sys/types.h>
#include <sys/ioctl.h>
#include <termios.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <android/log.h>

#define LOG_TAG "QuarkPTY"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT jint JNICALL
Java_com_quark_terminal_pty_PtyNative_openPty(JNIEnv *env, jobject thiz, jint rows, jint cols) {
    int master_fd;
    struct termios tios;
    struct winsize ws;

    ws.ws_row = rows;
    ws.ws_col = cols;
    ws.ws_xpixel = 0;
    ws.ws_ypixel = 0;

    // Open pseudo-terminal master
    master_fd = posix_openpt(O_RDWR | O_NOCTTY);
    if (master_fd < 0) {
        LOGE("Failed to open posix_openpt");
        return -1;
    }

    if (grantpt(master_fd) < 0 || unlockpt(master_fd) < 0) {
        LOGE("Failed to grant or unlock PTY");
        close(master_fd);
        return -1;
    }

    ioctl(master_fd, TIOCSWINSZ, &ws);
    LOGI("PTY opened successfully with rows=%d, cols=%d", rows, cols);
    return master_fd;
}