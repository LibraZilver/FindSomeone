package com.example.libra.findsomeone;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;
import static android.util.Log.d;

public abstract class AnimationOnTouchListener implements View.OnTouchListener {
    private Rect rect;
    private boolean ignore = false;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (ignore && motionEvent.getAction() != MotionEvent.ACTION_UP)
            return false;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                animatePressed();

                return false;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // back to normal state
                animateBackToNormal();

                // IMPORTANT - touch down won't work if this isn't there.
                ignore = false;
                return false;

            case MotionEvent.ACTION_MOVE:
                if (!rect.contains(view.getLeft() + (int) motionEvent.getX(), view.getTop() + (int) motionEvent.getY())) {
                    d(TAG, "out of bounds");
                    // STOP LISTENING TO MY TOUCH EVENTS!
                    ignore = true;

                } else {
                    d(TAG, "in bounds");
                }
                return false;
            default:
                return true;
        }
    }

    abstract void animatePressed();
    abstract void animateBackToNormal();



}