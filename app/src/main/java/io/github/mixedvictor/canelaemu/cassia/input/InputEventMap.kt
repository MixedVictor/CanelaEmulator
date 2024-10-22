package io.github.mixedvictor.canelaemu.cassia.input

import android.view.KeyEvent.*
import android.view.MotionEvent

class InputEventMap {
    companion object {
        /**
         * Converts an Android keycode to a Linux scancode, or 0 if there is no mapping. These are used by XKB with the default keymap.
         * @url https://github.com/torvalds/linux/blob/453f5db0619e2ad64076aab16ff5a00e0f7c53a2/include/uapi/linux/input-event-codes.h
         * @note This was automatically generated from https://android.googlesource.com/platform/frameworks/base/+/2c42929c53254aa8c0ece2a8be19fdebe5e91eb6/data/keyboards/Generic.kl using regex: '^(?!#)key ([0-9]+)\s+(\S+)' to 'KEYCODE_\2 -> \1$'.
         */
        fun toScanCode(keyCode: Int): Int {
            @Suppress("DUPLICATE_LABEL_IN_WHEN")
            return when (keyCode) {
                KEYCODE_ESCAPE -> 1
                KEYCODE_1 -> 2
                KEYCODE_2 -> 3
                KEYCODE_3 -> 4
                KEYCODE_4 -> 5
                KEYCODE_5 -> 6
                KEYCODE_6 -> 7
                KEYCODE_7 -> 8
                KEYCODE_8 -> 9
                KEYCODE_9 -> 10
                KEYCODE_0 -> 11
                KEYCODE_MINUS -> 12
                KEYCODE_EQUALS -> 13
                KEYCODE_DEL -> 14
                KEYCODE_TAB -> 15
                KEYCODE_Q -> 16
                KEYCODE_W -> 17
                KEYCODE_E -> 18
                KEYCODE_R -> 19
                KEYCODE_T -> 20
                KEYCODE_Y -> 21
                KEYCODE_U -> 22
                KEYCODE_I -> 23
                KEYCODE_O -> 24
                KEYCODE_P -> 25
                KEYCODE_LEFT_BRACKET -> 26
                KEYCODE_RIGHT_BRACKET -> 27
                KEYCODE_ENTER -> 28
                KEYCODE_CTRL_LEFT -> 29
                KEYCODE_A -> 30
                KEYCODE_S -> 31
                KEYCODE_D -> 32
                KEYCODE_F -> 33
                KEYCODE_G -> 34
                KEYCODE_H -> 35
                KEYCODE_J -> 36
                KEYCODE_K -> 37
                KEYCODE_L -> 38
                KEYCODE_SEMICOLON -> 39
                KEYCODE_APOSTROPHE -> 40
                KEYCODE_GRAVE -> 41
                KEYCODE_SHIFT_LEFT -> 42
                KEYCODE_BACKSLASH -> 43
                KEYCODE_Z -> 44
                KEYCODE_X -> 45
                KEYCODE_C -> 46
                KEYCODE_V -> 47
                KEYCODE_B -> 48
                KEYCODE_N -> 49
                KEYCODE_M -> 50
                KEYCODE_COMMA -> 51
                KEYCODE_PERIOD -> 52
                KEYCODE_SLASH -> 53
                KEYCODE_SHIFT_RIGHT -> 54
                KEYCODE_NUMPAD_MULTIPLY -> 55
                KEYCODE_ALT_LEFT -> 56
                KEYCODE_SPACE -> 57
                KEYCODE_CAPS_LOCK -> 58
                KEYCODE_F1 -> 59
                KEYCODE_F2 -> 60
                KEYCODE_F3 -> 61
                KEYCODE_F4 -> 62
                KEYCODE_F5 -> 63
                KEYCODE_F6 -> 64
                KEYCODE_F7 -> 65
                KEYCODE_F8 -> 66
                KEYCODE_F9 -> 67
                KEYCODE_F10 -> 68
                KEYCODE_NUM_LOCK -> 69
                KEYCODE_SCROLL_LOCK -> 70
                KEYCODE_NUMPAD_7 -> 71
                KEYCODE_NUMPAD_8 -> 72
                KEYCODE_NUMPAD_9 -> 73
                KEYCODE_NUMPAD_SUBTRACT -> 74
                KEYCODE_NUMPAD_4 -> 75
                KEYCODE_NUMPAD_5 -> 76
                KEYCODE_NUMPAD_6 -> 77
                KEYCODE_NUMPAD_ADD -> 78
                KEYCODE_NUMPAD_1 -> 79
                KEYCODE_NUMPAD_2 -> 80
                KEYCODE_NUMPAD_3 -> 81
                KEYCODE_NUMPAD_0 -> 82
                KEYCODE_NUMPAD_DOT -> 83
                KEYCODE_ZENKAKU_HANKAKU -> 85
                KEYCODE_BACKSLASH -> 86
                KEYCODE_F11 -> 87
                KEYCODE_F12 -> 88
                KEYCODE_RO -> 89
                KEYCODE_HENKAN -> 92
                KEYCODE_KATAKANA_HIRAGANA -> 93
                KEYCODE_MUHENKAN -> 94
                KEYCODE_NUMPAD_COMMA -> 95
                KEYCODE_NUMPAD_ENTER -> 96
                KEYCODE_CTRL_RIGHT -> 97
                KEYCODE_NUMPAD_DIVIDE -> 98
                KEYCODE_SYSRQ -> 99
                KEYCODE_ALT_RIGHT -> 100
                KEYCODE_MOVE_HOME -> 102
                KEYCODE_DPAD_UP -> 103
                KEYCODE_PAGE_UP -> 104
                KEYCODE_DPAD_LEFT -> 105
                KEYCODE_DPAD_RIGHT -> 106
                KEYCODE_MOVE_END -> 107
                KEYCODE_DPAD_DOWN -> 108
                KEYCODE_PAGE_DOWN -> 109
                KEYCODE_INSERT -> 110
                KEYCODE_FORWARD_DEL -> 111
                KEYCODE_VOLUME_MUTE -> 113
                KEYCODE_VOLUME_DOWN -> 114
                KEYCODE_VOLUME_UP -> 115
                KEYCODE_POWER -> 116
                KEYCODE_NUMPAD_EQUALS -> 117
                KEYCODE_BREAK -> 119
                KEYCODE_RECENT_APPS -> 120
                KEYCODE_NUMPAD_COMMA -> 121
                KEYCODE_KANA -> 122
                KEYCODE_EISU -> 123
                KEYCODE_YEN -> 124
                KEYCODE_META_LEFT -> 125
                KEYCODE_META_RIGHT -> 126
                KEYCODE_MENU -> 127
                KEYCODE_MEDIA_STOP -> 128
                KEYCODE_COPY -> 133
                KEYCODE_PASTE -> 135
                KEYCODE_CUT -> 137
                KEYCODE_MENU -> 139
                KEYCODE_CALCULATOR -> 140
                KEYCODE_SLEEP -> 142
                KEYCODE_WAKEUP -> 143
                KEYCODE_EXPLORER -> 150
                KEYCODE_POWER -> 152
                KEYCODE_ENVELOPE -> 155
                KEYCODE_BOOKMARK -> 156
                KEYCODE_BACK -> 158
                KEYCODE_FORWARD -> 159
                KEYCODE_MEDIA_CLOSE -> 160
                KEYCODE_MEDIA_EJECT -> 161
                KEYCODE_MEDIA_EJECT -> 162
                KEYCODE_MEDIA_NEXT -> 163
                KEYCODE_MEDIA_PLAY_PAUSE -> 164
                KEYCODE_MEDIA_PREVIOUS -> 165
                KEYCODE_MEDIA_STOP -> 166
                KEYCODE_MEDIA_RECORD -> 167
                KEYCODE_MEDIA_REWIND -> 168
                KEYCODE_CALL -> 169
                KEYCODE_MUSIC -> 171
                KEYCODE_HOME -> 172
                KEYCODE_REFRESH -> 173
                KEYCODE_PAGE_UP -> 177
                KEYCODE_PAGE_DOWN -> 178
                KEYCODE_NUMPAD_LEFT_PAREN -> 179
                KEYCODE_NUMPAD_RIGHT_PAREN -> 180
                KEYCODE_MEDIA_PLAY -> 200
                KEYCODE_MEDIA_PAUSE -> 201
                KEYCODE_NOTIFICATION -> 204
                KEYCODE_MEDIA_PLAY -> 207
                KEYCODE_MEDIA_FAST_FORWARD -> 208
                KEYCODE_CAMERA -> 212
                KEYCODE_MUSIC -> 213
                KEYCODE_ENVELOPE -> 215
                KEYCODE_SEARCH -> 217
                KEYCODE_BRIGHTNESS_DOWN -> 224
                KEYCODE_BRIGHTNESS_UP -> 225
                KEYCODE_HEADSETHOOK -> 226
                KEYCODE_KEYBOARD_BACKLIGHT_TOGGLE -> 228
                KEYCODE_KEYBOARD_BACKLIGHT_DOWN -> 229
                KEYCODE_KEYBOARD_BACKLIGHT_UP -> 230
                KEYCODE_MUTE -> 248
                KEYCODE_BUTTON_1 -> 256
                KEYCODE_BUTTON_2 -> 257
                KEYCODE_BUTTON_3 -> 258
                KEYCODE_BUTTON_4 -> 259
                KEYCODE_BUTTON_5 -> 260
                KEYCODE_BUTTON_6 -> 261
                KEYCODE_BUTTON_7 -> 262
                KEYCODE_BUTTON_8 -> 263
                KEYCODE_BUTTON_9 -> 264
                KEYCODE_BUTTON_10 -> 265
                KEYCODE_BUTTON_11 -> 266
                KEYCODE_BUTTON_12 -> 267
                KEYCODE_BUTTON_13 -> 268
                KEYCODE_BUTTON_14 -> 269
                KEYCODE_BUTTON_15 -> 270
                KEYCODE_BUTTON_16 -> 271
                KEYCODE_BUTTON_1 -> 288
                KEYCODE_BUTTON_2 -> 289
                KEYCODE_BUTTON_3 -> 290
                KEYCODE_BUTTON_4 -> 291
                KEYCODE_BUTTON_5 -> 292
                KEYCODE_BUTTON_6 -> 293
                KEYCODE_BUTTON_7 -> 294
                KEYCODE_BUTTON_8 -> 295
                KEYCODE_BUTTON_9 -> 296
                KEYCODE_BUTTON_10 -> 297
                KEYCODE_BUTTON_11 -> 298
                KEYCODE_BUTTON_12 -> 299
                KEYCODE_BUTTON_13 -> 300
                KEYCODE_BUTTON_14 -> 301
                KEYCODE_BUTTON_15 -> 302
                KEYCODE_BUTTON_16 -> 303
                KEYCODE_BUTTON_A -> 304
                KEYCODE_BUTTON_B -> 305
                KEYCODE_BUTTON_C -> 306
                KEYCODE_BUTTON_X -> 307
                KEYCODE_BUTTON_Y -> 308
                KEYCODE_BUTTON_Z -> 309
                KEYCODE_BUTTON_L1 -> 310
                KEYCODE_BUTTON_R1 -> 311
                KEYCODE_BUTTON_L2 -> 312
                KEYCODE_BUTTON_R2 -> 313
                KEYCODE_BUTTON_SELECT -> 314
                KEYCODE_BUTTON_START -> 315
                KEYCODE_BUTTON_MODE -> 316
                KEYCODE_BUTTON_THUMBL -> 317
                KEYCODE_BUTTON_THUMBR -> 318
                KEYCODE_STYLUS_BUTTON_TERTIARY -> 329
                KEYCODE_STYLUS_BUTTON_PRIMARY -> 331
                KEYCODE_STYLUS_BUTTON_SECONDARY -> 332
                KEYCODE_DPAD_CENTER -> 353
                KEYCODE_GUIDE -> 362
                KEYCODE_DVR -> 366
                KEYCODE_CAPTIONS -> 370
                KEYCODE_TV -> 377
                KEYCODE_CALENDAR -> 397
                KEYCODE_PROG_RED -> 398
                KEYCODE_PROG_GREEN -> 399
                KEYCODE_PROG_YELLOW -> 400
                KEYCODE_PROG_BLUE -> 401
                KEYCODE_CHANNEL_UP -> 402
                KEYCODE_CHANNEL_DOWN -> 403
                KEYCODE_LAST_CHANNEL -> 405
                KEYCODE_ZOOM_IN -> 418
                KEYCODE_ZOOM_OUT -> 419
                KEYCODE_FOCUS -> 528
                KEYCODE_CONTACTS -> 429
                KEYCODE_FUNCTION -> 464
                KEYCODE_ESCAPE -> 465
                KEYCODE_F1 -> 466
                KEYCODE_F2 -> 467
                KEYCODE_F3 -> 468
                KEYCODE_F4 -> 469
                KEYCODE_F5 -> 470
                KEYCODE_F6 -> 471
                KEYCODE_F7 -> 472
                KEYCODE_F8 -> 473
                KEYCODE_F9 -> 474
                KEYCODE_F10 -> 475
                KEYCODE_F11 -> 476
                KEYCODE_F12 -> 477
                KEYCODE_1 -> 478
                KEYCODE_2 -> 479
                KEYCODE_D -> 480
                KEYCODE_E -> 481
                KEYCODE_F -> 482
                KEYCODE_S -> 483
                KEYCODE_B -> 484
                KEYCODE_STAR -> 522
                KEYCODE_POUND -> 523
                KEYCODE_APP_SWITCH -> 580
                KEYCODE_VOICE_ASSIST -> 582
                KEYCODE_ASSIST -> 583
                KEYCODE_MACRO_1 -> 656
                KEYCODE_MACRO_2 -> 657
                KEYCODE_MACRO_3 -> 658
                KEYCODE_MACRO_4 -> 659
                else -> 0
            }
        }

        /**
         * Converts an Android MotionEvent button to a Linux button, or 0 if there is no mapping.
         * @url https://github.com/torvalds/linux/blob/453f5db0619e2ad64076aab16ff5a00e0f7c53a2/include/uapi/linux/input-event-codes.h
         */
        fun toLinuxButton(motionButton: Int): Int {
            return when (motionButton) {
                MotionEvent.BUTTON_PRIMARY, MotionEvent.BUTTON_STYLUS_PRIMARY -> 0x110
                MotionEvent.BUTTON_SECONDARY, MotionEvent.BUTTON_STYLUS_SECONDARY -> 0x111
                MotionEvent.BUTTON_TERTIARY -> 0x112
                MotionEvent.BUTTON_BACK -> 0x113
                MotionEvent.BUTTON_FORWARD -> 0x114
                else -> 0
            }
        }
    }
}
