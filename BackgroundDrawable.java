package com.test.koibrowser.view.customView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

import androidx.core.content.ContextCompat;

import com.test.koibrowser.R;
import com.test.koibrowser.utils.ThemeUtils;


public class BackgroundDrawable extends TransitionDrawable {
    private boolean mSelected;

    public BackgroundDrawable(Context context) {
        super(new Drawable[]{new ColorDrawable(ContextCompat.getColor(context, R.color.transparent)), new ColorDrawable(ThemeUtils.getColor(context, R.attr.selectedBackground))});
    }

    @Override 
    public void startTransition(int i) {
        if (!this.mSelected) {
            super.startTransition(i);
        }
        this.mSelected = true;
    }

    @Override 
    public void reverseTransition(int i) {
        if (this.mSelected) {
            super.reverseTransition(i);
        }
        this.mSelected = false;
    }
}
