package com.example.gridoncanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvas extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private Paint whitePaint = new Paint();
    private boolean[][] cellChecked;
    int column;
    int row;
    Canvas mCanvas;


    public MyCanvas(Context context) {
        this(context, null);
    }

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setColor(Color.BLUE);
        whitePaint.setColor(Color.RED);
        blackPaint.setStrokeWidth(10);
//        whitePaint.setStrokeWidth(10);
        whitePaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        cellChecked = new boolean[numColumns][numRows];

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);
            cellChecked[column][row] = !cellChecked[column][row];
            invalidate();
        }
        return true;
    }


//    private static void drawText(String str, int column, int row, Paint paint,Canvas canvas){
//        canvas.drawText(str,column,row,paint);
//    }

    @Override
    protected void onDraw(Canvas mCanvas) {
//        canvas.drawColor(Color.WHITE);
//        mCanvas.drawColor(Color.RED);
        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (cellChecked[i][j]) {
                    mCanvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            blackPaint);
                }
            }
        }

//        amount of numbers needed are numColumns * numRows

        for (int i = 1; i < numColumns; i++) {
            mCanvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
            //            DRAW THE STRING PASS IN AS PARAMETER ON THE CANVAS
            whitePaint.setTextSize(50);
            whitePaint.setStyle(Paint.Style.FILL);
            mCanvas.drawText("1",i ,i ,whitePaint);

        }

        for (int i = 1; i < numRows; i++) {
            mCanvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
            //            DRAW THE STRING PASS IN AS PARAMETER ON THE CANVAS
            whitePaint.setTextSize(50);
            whitePaint.setStyle(Paint.Style.FILL);
            mCanvas.drawText("1",i ,i ,whitePaint);

        }


    }


}