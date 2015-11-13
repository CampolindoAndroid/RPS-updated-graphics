package com.club.android.campo.rps.rps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



public class GameView extends View {

    private int shiftBorder = 20;

    private final Game game;
    private Bitmap backgroundGrid;
    private int aiWins;
    private int userWins;
    private int draws;
    private String youLabel;
    private String aiLabel;
    private Paint paintBoard;
//specifies the width, color, and style of the circles that are painted
    public GameView(Context context) {
        super(context);
        this.game = (Game) context;

// defines scores and player names
        aiWins = 0;
        userWins = 0;
        draws = 0;
        youLabel = "You";
        aiLabel = "AI";

        backgroundGrid = BitmapFactory.decodeResource(game.getResources(),
                R.drawable.grid);

        setEnabled(true);
        setClickable(true);
    }
//sets up the game board, draws it
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        Paint text = new Paint();
        background.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        canvas.drawBitmap(backgroundGrid, 10, 0, null);

        float l_x = backgroundGrid.getWidth() / game.SIZE_OF_BOARD_W;
        float l_y = backgroundGrid.getHeight() / game.SIZE_OF_BOARD_H;


        //current rank
        text.setAntiAlias(true);
        text.setColor(Color.BLACK);
        text.setTextSize(30);
        canvas.drawText(youLabel, 30, backgroundGrid.getHeight() + 50, text);
        canvas.drawText(String.valueOf(userWins), 210, backgroundGrid.getHeight() + 50, text);

        canvas.drawText(aiLabel, 30, backgroundGrid.getHeight() + 90, text);
        canvas.drawText(String.valueOf(aiWins), 210, backgroundGrid.getHeight() + 90, text);

        canvas.drawText("Draw", 30, backgroundGrid.getHeight() + 130, text);
        canvas.drawText(String.valueOf(draws), 210, backgroundGrid.getHeight() + 130, text);
    }
//defines what happens when player clicks on board
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int winner = -1;
        boolean isValidMove = false;

        if (action == MotionEvent.ACTION_DOWN) {
            return true;

        } else if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Log.v("real  x:y", String.valueOf(x) + " : " + String.valueOf(y) + " : " + shiftBorder + " : " + getWidth());

            // if game over reset grid, and begin new game
            if (game.checkGameFinished() != 0) {
                game.cleanGrid();
                invalidate();
                return false;
            }

            int bgWidth = backgroundGrid.getWidth() / game.SIZE_OF_BOARD_W;
            int bgHeight = backgroundGrid.getHeight() / game.SIZE_OF_BOARD_H;

            x = (x - shiftBorder) / bgWidth;
            y = y / bgHeight;
//            Log.v("test x:y", String.valueOf(x) + " : " + String.valueOf(y));

            game.addChoice(x);

            if (x < game.SIZE_OF_BOARD_W && x >= 0 && y < game.SIZE_OF_BOARD_H && y >= 0) {
                isValidMove = game.setGrid(x, y, game.getPlayer());
            }
            if (isValidMove == true) {
                winner = game.checkGameFinished();

                if (winner == 0) {
                    game.computerMove();
                }
                winner = game.checkGameFinished();

                if (winner == 1) {
                    if (game.getPlayer() == 1) {
                        userWins++;
                    } else {
                        aiWins++;
                    }
                }
                if (winner == 2) {
                    if (game.getPlayer() == 2) {
                        userWins++;
                    } else {
                        aiWins++;
                    }
                } else if (winner == 3) {
                    draws++;
                }
            }
        }
        invalidate();
        return false;
    }

}
