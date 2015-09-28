package soccergame.assignment2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.jar.Attributes;

/**
 * Created by Grayson on 9/27/2015.
 */
public class fieldSurface extends SurfaceView{


    public fieldSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);


    }

    //draws the field and the players
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        Paint red = new Paint();
        red.setColor(Color.RED);
        canvas.drawRect(0,250,65,375,white);
        canvas.drawRect( 1727,250, 1792, 375, white);
        for (int i = 0; i < 4; i++) {
            canvas.drawCircle( 750,275+(i*40), 15, blue);
            canvas.drawCircle( 950,275+(i*40), 15, red);
        }

    }



}