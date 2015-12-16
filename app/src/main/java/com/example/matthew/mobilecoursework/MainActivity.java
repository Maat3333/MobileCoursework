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

    FragmentManager fmAboutDialgue;// needed for about
    View mView;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //needed for about
        fmAboutDialgue = this.getFragmentManager();

        //Creating a new layout which is used to contain the canvas
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.myDrawing);
        mView = new DrawingView(this);
        layout.addView(mView, new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT ,
                LinearLayout.LayoutParams.MATCH_PARENT));
        //Calling function used to set up paint brush
        SetUpPaintBrush();
    }

    //Sets up the different values needed for the brush e.g. colour and stroke style
    private void SetUpPaintBrush() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }

    // Sub class needed because MainActivity extends appCompact not view, used to handle the touch event
    class DrawingView extends View {
        //Member level variables
        private Path path;
        private Bitmap mBitmap;
        private Canvas mCanvas;


        public DrawingView(Context context) {
            super(context);
            path = new Path();
            mBitmap = Bitmap.createBitmap(820, 480, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            this.setBackgroundColor(Color.WHITE);
        }
        //Creates an array of the object used for storing where has been painted on the canvas
        private ArrayList<PathWithPaint> _graphics1 = new ArrayList<PathWithPaint>();

        //Listens for a touch then paints to the canvas
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
        //ToDo:Not completely sure what this does come back to it
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (_graphics1.size() > 0) {
                //Not sure about the convention "_graphics"
                canvas.drawPath( _graphics1.get(_graphics1.size() - 1).getPath(),
                                _graphics1.get(_graphics1.size() - 1).getmPaint());
            }
        }
    }

    //Standard Code for menu used in all classes.
    //ToDo: Need to find a way of not repeating this code!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mMain:
                Intent mcMain = new Intent(this, MainActivity.class);
                this.startActivity(mcMain);
                return true;
            case R.id.mRss:
                Intent mcRss = new Intent(this, RssActivity.class);
                this.startActivity(mcRss);
                return true;
            case R.id.mAbout:
                DialogFragment mcAboutDlg = new clsAbout();
                mcAboutDlg.show(fmAboutDialgue, "mcAboutDlg");
                return true;
            case R.id.mSound:
                Intent mcSound = new Intent(this, clsSoundboard.class);
                this.startActivity(mcSound);
                return true;
            case R.id.mMapp:
                Intent mcMapp = new Intent(this,mcMapActivity.class);
                this.startActivity(mcMapp);
                return true;
            case R.id.mSave:
                Intent mcSaved = new Intent(this,mcSavingDataOutput.class);
                this.startActivity(mcSaved);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}