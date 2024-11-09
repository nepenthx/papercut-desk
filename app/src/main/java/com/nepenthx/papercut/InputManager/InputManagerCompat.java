package com.nepenthx.papercut.InputManager;

import android.view.InputDevice;
import android.view.MotionEvent;

import android.os.Handler;

public interface InputManagerCompat {
    public InputDevice getInputDevice(int id);
    public int[] getInputDeviceIds();

    public void registerInputDeviceListener(
            InputManagerCompat.InputDeviceListener listener,
            Handler handler);
    public void unregisterInputDeviceListener(
            InputManagerCompat.InputDeviceListener listener);

    public interface InputDeviceListener {
        void onInputDeviceAdded(int deviceId);
        void onInputDeviceChanged(int deviceId);
        void onInputDeviceRemoved(int deviceId);
    }
}
