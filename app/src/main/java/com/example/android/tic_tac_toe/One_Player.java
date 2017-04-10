package com.example.android.tic_tac_toe;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by HP PC on 26-02-2017.
 */

public class One_Player extends Activity {
    private int[][] table;
    private int[][] button_Ids;
    private boolean xMove;
    private int gx,gy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_player);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.example_color));
        table = new int[3][3];
        button_Ids = new int[3][3];
        for(int i=0;i<3;++i)
        {
            for(int j=0;j<3;++j)
                table[i][j]=0;
        }
        xMove=true;

        button_Ids[0][0] = R.id.button1;
        button_Ids[0][1] = R.id.button2;
        button_Ids[0][2] = R.id.button3;
        button_Ids[1][0] = R.id.button4;
        button_Ids[1][1] = R.id.button5;
        button_Ids[1][2] = R.id.button6;
        button_Ids[2][0] = R.id.button7;
        button_Ids[2][1] = R.id.button8;
        button_Ids[2][2] = R.id.button9;

//        table[0][0] = 2;
//        table[0][1] = 1;
//        table[0][2] = 2;
//        table[1][0] = 1;
//        table[1][1] = 1;
//        table[1][2] = 2;
    }

    boolean isMoveLeft()
    {
        int flag=0;
        for(int i=0;i<3;++i)
            for(int j=0;j<3;++j)
                if(table[i][j]==0)
                {
                    flag=1;
                    break;
                }
        if(flag==0)
            return false;
        return true;
    }

    int evaluate()
    {
        // Checking Rows
        for (int row = 0; row<3; row++)
        {
            if (table[row][0]==table[row][1] && table[row][1]==table[row][2])
            {
                if (table[row][0]==1)
                    return +10;
                else if (table[row][0]==2)
                    return -10;
            }
        }
        // Checking Columns
        for (int col = 0; col<3; col++)
        {
            if (table[0][col]==table[1][col] && table[1][col]==table[2][col])
            {
                if (table[0][col]==1)
                    return +10;

                else if (table[0][col]==2)
                    return -10;
            }
        }
        // Checking Diagonals
        if (table[0][0]==table[1][1] && table[1][1]==table[2][2])
        {
            if (table[0][0]==1)
                return +10;
            else if (table[0][0]==2)
                return -10;
        }

        if (table[0][2]==table[1][1] && table[1][1]==table[2][0])
        {
            if (table[0][2]==1)
                return +10;
            else if (table[0][2]==2)
                return -10;
        }
        // Else if none of them have won then return 0
        return 0;
    }
    public int check(int x,int y)
    {
        //check horizontal lines
        if (table[x][0] == 1 && table[x][1] == 1 && table[x][2] == 1)
        {
            return 10;
        }
        if (table[x][0] == 2 && table[x][1] == 2 && table[x][2] == 2)
        {
            return -10;
        }

        //check vertical lines
        if (table[0][y] == 1 && table[1][y] == 1 && table[2][y] == 1)
        {
            return 10;
        }
        if (table[0][y] == 2 && table[1][y] == 2 && table[2][y] == 2)
        {
            return -10;
        }

        //check diagonals
        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1)
        {
            return 10;
        }
        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1)
        {
            return 10;
        }

        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2)
        {
            return -10;
        }
        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2)
        {
            return -10;
        }
        return 0;
    }
    public int minimax(boolean isMax)
    {
        int score = evaluate();

        if(score == 10 || score == -10)
            return score;

        if(isMoveLeft() == false)
            return 0;

        if(isMax)
        {
            int temp = -100;
            for(int i=0;i<3;++i)
                for (int j = 0; j < 3; ++j)
                    if (table[i][j] == 0)
                    {
                        table[i][j] = 1;
                        temp = Math.max(temp, minimax(!isMax));
                        //undo the move
                        table[i][j] = 0;
                    }
            return temp;
        }
        else
        {
            int temp = 100;
            for(int i=0;i<3;++i)
                for (int j = 0; j < 3; ++j)
                    if (table[i][j] == 0)
                    {
                        table[i][j] = 2;
                        temp = Math.min(temp, minimax(!isMax));
                        //undo the move
                        table[i][j] = 0;
                    }
            return temp;
        }
    }

    public void findBestMove()
    {
        int score = -100;
        gx=-1;
        gy=-1;

        for(int i=0;i<3;++i)
            for(int j=0;j<3;++j)
                if(table[i][j] == 0)
                {
                    table[i][j]=1;

                    int temp = minimax(false);
                    table[i][j]=0;

                    if(temp>score)
                    {
                        gx=i;
                        gy=j;
                        score = temp;
                    }
                }
    }

    public void makeMove(View v)
    {
        int x = 0;
        int y = 0;

        int id = v.getId();
        Log.i("P1","make move: id= " + id);
        switch(id)
        {
            case R.id.button1: break;
            case R.id.button2: x = 0;
                y = 1;
                break;
            case R.id.button3: x = 0;
                y = 2;
                break;
            case R.id.button4: x = 1;
                y = 0;
                break;
            case R.id.button5: x = 1;
                y = 1;
                break;
            case R.id.button6: x = 1;
                y = 2;
                break;
            case R.id.button7: x = 2;
                y = 0;
                break;
            case R.id.button8: x = 2;
                y = 1;
                break;
            case R.id.button9: x = 2;
                y = 2;
                break;
        }

        Log.i("P1","make move: " + x + ", " + y);
        if(table[x][y] != 0)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("This cell is not empty!");
            dlgAlert.setTitle("Error");
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            return;
        }
        Button btn = (Button) findViewById(id);
        TextView label = (TextView) findViewById(R.id.moveTextView);
        Log.i("P1","make move: " + xMove);
        if(xMove)
        {
            btn.setText("X");
            table[x][y] = 2;
            label.setText("O move");
            xMove = false;
        }
        else
        {
            btn.setText("O");
            table[x][y] = 1;
            xMove = true;

            label.setText("X move");
        }

        checkResult();

        if(isMoveLeft() == false)
            return;

        findBestMove();
        Log.i("P1","AI wants " + gx + "," + gy);
        table[gx][gy]=1;
        Log.d("P1", "id: " + button_Ids[gx][gy]);
        Button bt = (Button) this.findViewById(button_Ids[gx][gy]);
        bt.setText("0");
//        btn = (Button) v.findViewById(button_Ids[gx][gy]);
//        btn.setText("O");
        xMove = true;

        label.setText("X move");
        checkResult();
    }

//    private void checkResult() {
//        boolean empty = false;
//        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
//        for (int i = 0; i != 3; ++i)
//        {
//            for (int j = 0; j != 3; ++j)
//            {
//                if (table[i][j]==0)
//                {
//                    empty = true;
//                    break;
//                }
//            }
//        }
//        if (!empty)
//        {
//            dlgAlert.setMessage("Draw!");
//            dlgAlert.setTitle("Draw");
//            dlgAlert.setCancelable(true);
//            dlgAlert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//
//                        }
//                    });
//            dlgAlert.create().show();
//            finish();
//        }
//        Log.i("P1","checking horizontal");
//        //check horizontal lines
//        for (int i = 0; i != 3; ++i)
//        {
//            if (table[i][0] == 1 && table[i][1] == 1 && table[i][2] == 1)
//            {
//                dlgAlert.setMessage("O Player wins!");
//                dlgAlert.setTitle("congratulations");
//                dlgAlert.setCancelable(true);
//                dlgAlert.setPositiveButton("Ok",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
//                dlgAlert.create().show();
//                finish();
//            }
//            if (table[i][0] == 2 && table[i][1] == 2 && table[i][2] == 2)
//            {
//                dlgAlert.setMessage("X Player wins!");
//                dlgAlert.setTitle("congratulations");
//                dlgAlert.setCancelable(true);
//                dlgAlert.setPositiveButton("Ok",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
//                dlgAlert.create().show();
//                finish();
//            }
//        }
//        //check vertical lines
//        Log.i("P1","checking vertical");
//        for (int i = 0; i != 3; ++i)
//        {
//            Log.i("P1","vertical for col " + i + ": " + table[0][i] + " " + table[1][i] + " " + table[2][i]);
//            if (table[0][i] == 1 && table[1][i] == 1 && table[2][i] == 1)
//            {
//                dlgAlert.setMessage("O Player wins!");
//                dlgAlert.setTitle("congratulations");
//                dlgAlert.setCancelable(true);
//                dlgAlert.setPositiveButton("Ok",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
//                dlgAlert.create().show();
//                finish();
//            }
//
//            if (table[0][i] == 2 && table[1][i] == 2 && table[2][i] == 2)
//            {
//                Log.i("P1","vertical for col " + i + ": " + table[0][i] + " " + table[1][i] + " " + table[2][i]);
//                dlgAlert.setMessage("X Player wins!");
//                dlgAlert.setTitle("congratulations");
//                dlgAlert.setCancelable(true);
//                dlgAlert.setPositiveButton("Ok",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
//                dlgAlert.create().show();
//                finish();
//            }
//        }
//        Log.i("P1","checking diagonal");
//        //check diagonals
//        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1)
//        {
//            dlgAlert.setMessage("O Player wins!");
//            dlgAlert.setTitle("congratulations");
//            dlgAlert.setCancelable(true);
//            dlgAlert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    });
//            dlgAlert.create().show();
//            finish();
//        }
//        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2)
//        {
//            dlgAlert.setMessage("X Player wins!");
//            dlgAlert.setTitle("congratulations");
//            dlgAlert.setCancelable(true);
//            dlgAlert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    });
//            dlgAlert.create().show();
//            finish();
//        }
//        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1)
//        {
//            dlgAlert.setMessage("O Player wins!");
//            dlgAlert.setTitle("congratulations");
//            dlgAlert.setCancelable(true);
//            dlgAlert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    });
//            dlgAlert.create().show();
//            finish();
//        }
//        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2)
//        {
//            dlgAlert.setMessage("X Player wins!");
//            dlgAlert.setTitle("congratulations");
//            dlgAlert.setCancelable(true);
//            dlgAlert.setPositiveButton("Ok",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    });
//
//            dlgAlert.create().show();
//            finish();
//        }
//    }

    private void checkResult() {
        boolean empty = false;
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        for (int i = 0; i != 3; ++i)
        {
            for (int j = 0; j != 3; ++j)
            {
                if (table[i][j]==0)
                {
                    empty = true;
                    break;
                }
            }
        }
        if (!empty)
        {
            dlgAlert.setMessage("Draw!");
            dlgAlert.setTitle("Draw");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });
            dlgAlert.create().show();
//            finish();
        }

        Log.i("P1","check horizontal");
        //check horizontal lines
        for (int i = 0; i != 3; ++i)
        {
            if (table[i][0] == 1 && table[i][1] == 1 && table[i][2] == 1)
            {
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                dlgAlert.create().show();
//                finish();
            }
            if (table[i][0] == 2 && table[i][1] == 2 && table[i][2] == 2)
            {
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                dlgAlert.create().show();
//                finish();
            }
        }

        Log.i("P1","check vertical");
        //check vertical lines
        for (int i = 0; i != 3; ++i)
        {
            if (table[0][i] == 1 && table[1][i] == 1 && table[2][i] == 1)
            {
                dlgAlert.setMessage("O Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                dlgAlert.create().show();
//                finish();
            }
            Log.i("main","vertical for col " + i + ": " + table[0][i] + " " + table[1][i] + " " + table[2][i]);
            if (table[0][i] == 2 && table[1][i] == 2 && table[2][i] == 2)
            {
                Log.i("main","inside");
                dlgAlert.setMessage("X Player wins!");
                dlgAlert.setTitle("congratulations");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                dlgAlert.create().show();
//                finish();
            }
        }

        Log.i("P1","check diagonal");
        //check diagonals
        if (table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1)
        {
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            dlgAlert.create().show();
//            finish();
        }
        if (table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2)
        {
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            dlgAlert.create().show();
//            finish();
        }
        if (table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1)
        {
            dlgAlert.setMessage("O Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            dlgAlert.create().show();
//            finish();
        }
        if (table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2)
        {
            dlgAlert.setMessage("X Player wins!");
            dlgAlert.setTitle("congratulations");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

            dlgAlert.create().show();
//            finish();
        }
    }
}
