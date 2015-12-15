package com.example.matthew.mobilecoursework;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //GoogleApiClient mGoogleApiClient;
    //Location mLastLocation;
    FragmentManager fmAboutDialgue;// needed for about

    View mView;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needed for about
        fmAboutDialgue = this.getFragmentManager();

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.myDrawing);
        mView = new DrawingView(this);
        layout.addView(mView, new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        SetUpPaintBrush();
    }

    private void SetUpPaintBrush() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }

    class DrawingView extends View {
        private Path path;
        private Bitmap mBitmap;
        private Canvas mCanvas;

        public DrawingView(Context context) {
            super(context);
            path = new Path();
            mBitmap = Bitmap.createBitmap(820, 480, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            this.setBackgroundColor(Color.BLACK);
        }

        private ArrayList<PathWithPaint> _graphics1 = new ArrayList<PathWithPaint>();

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            PathWithPaint pp = new PathWithPaint();
            mCanvas.drawPath(path, mPaint);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                path.moveTo(event.getX(), event.getY());
                path.lineTo(event.getX(), event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                path.lineTo(event.getX(), event.getY());
                pp.setPath(path);
                pp.setmPaint(mPaint);
                _graphics1.add(pp);
            }
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (_graphics1.size() > 0) {
                canvas.drawPath(
                        _graphics1.get(_graphics1.size() - 1).getPath(),
                        _graphics1.get(_graphics1.size() - 1).getmPaint());
            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mMap:
                Intent mcMap = new Intent(this, MapsActivity.class);
                this.startActivity(mcMap);
                return true;
            case R.id.mMain:
                Intent mcMain = new Intent(this, MainActivity.class);
                this.startActivity(mcMain);
                return true;
            case R.id.mRss:
                Intent mcRss = new Intent(this, RssActivity.class);
                this.startActivity(mcRss);
                return true;
            case R.id.mAbout:
                DialogFragment mcAboutDlg = new mcAboutDialogue();
                mcAboutDlg.show(fmAboutDialgue, "mcAboutDlg");
                return true;
            case R.id.mSound:
                Intent mcSound = new Intent(this, SoundBoard.class);
                this.startActivity(mcSound);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//ToDo: implement https://guides.codepath.com/android/Basic-Painting-with-Views or this http://v4all123.blogspot.co.uk/2013/11/simple-drawing-example-in-android.html bit more fun? draw a cloud or something

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


}




