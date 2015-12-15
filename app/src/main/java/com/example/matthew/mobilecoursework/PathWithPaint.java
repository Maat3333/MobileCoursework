package com.example.matthew.mobilecoursework;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by matthew on 15/12/2015.
 */
//Object used for storing the path painted on the canvas
public class PathWithPaint {
    private Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    private Paint mPaint;

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}
