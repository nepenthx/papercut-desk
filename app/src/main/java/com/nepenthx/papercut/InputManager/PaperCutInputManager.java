package com.nepenthx.papercut.InputManager;

import android.content.Context;
import android.util.Log;
import android.view.InputDevice;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;
import android.os.Handler;

import com.nepenthx.papercut.PaperCutConfig;

public class PaperCutInputManager implements InputManagerCompat {
    public static String TAG = "PaperCutInputManager";

    private final android.hardware.input.InputManager inputManager;
    private final Map<InputManagerCompat.InputDeviceListener, InputDeviceListener> listeners;

    public PaperCutInputManager(Context context) {
        inputManager = (android.hardware.input.InputManager)
                context.getSystemService(Context.INPUT_SERVICE);
        listeners = new HashMap<>();
    }


    @Override
    public InputDevice getInputDevice(int id) {
        return inputManager.getInputDevice(id);
    }

    @Override
    public int[] getInputDeviceIds() {
        return inputManager.getInputDeviceIds();
    }

    static class InputDeviceListener implements
            android.hardware.input.InputManager.InputDeviceListener {
        final InputManagerCompat.InputDeviceListener mIDL;

        public InputDeviceListener(InputManagerCompat.InputDeviceListener idl) {
            mIDL = idl;
        }

        @Override
        public void onInputDeviceAdded(int deviceId) {
                mIDL.onInputDeviceAdded(deviceId);
            if (PaperCutConfig.instance.isDebug()) {
                Log.d(TAG,"device is added:"+deviceId);
            }
        }

        @Override
        public void onInputDeviceRemoved(int i) {
            mIDL.onInputDeviceRemoved(i);

        }

        @Override
        public void onInputDeviceChanged(int i) {
            mIDL.onInputDeviceChanged(i);
        }
    }

    @Override
    public void registerInputDeviceListener(InputManagerCompat.InputDeviceListener listener,
                                            Handler handler) {
        InputDeviceListener v16Listener = new
                InputDeviceListener(listener);
        inputManager.registerInputDeviceListener(v16Listener, handler);
        listeners.put(listener, v16Listener);
    }

    @Override
    public void unregisterInputDeviceListener(InputManagerCompat.InputDeviceListener listener) {

    }
}