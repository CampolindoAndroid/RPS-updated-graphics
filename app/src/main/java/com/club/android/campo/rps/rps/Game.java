package com.club.android.campo.rps.rps;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class Game extends Activity {
//defines how many points are on the board
    public final int SIZE_OF_BOARD_W = 3;
    public final int SIZE_OF_BOARD_H = 1;
//player number
    private int player = 1;
    private int[] board = new int[SIZE_OF_BOARD_W];
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cleanGrid();
        gameView = new GameView(this);
        setContentView(gameView);
        gameView.requestFocus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getGrid(int i) {
        return board[i];
    }

    public int checkGameFinished() {
        return 0;
    }

    public boolean setGrid(int i, int j, int value) {
        board[i] = value;
        return false;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void cleanGrid() {
        for (int i = 0; i < SIZE_OF_BOARD_W; i++)
           board[i] = 0;
    }

    public void computerMove() {

    }

    public void addChoice(int x) {
    }
}
