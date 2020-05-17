package souravapp.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SOURAV on 1/30/2017.
 */
public class mplayer extends AppCompatActivity {

    int c[][];
    int win;
    int chance;
    int i, j;
    Button b[][],rset;
    TextView textView,t1,t2;
    int scoreAI = 0;
    int scoreplayer = 0;
    ImageView imageView;
    boolean vibration,sound;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mplayer);

        sound= getIntent().getBooleanExtra("audio",true);
        vibration= getIntent().getBooleanExtra("vibra",true);

        win=1;
        setBoard();
        rset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setBoard();
            }
        });
    }


    // Set up the game board.
    private void setBoard() {
        chance = 0;
        b = new Button[4][4];
        c = new int[4][4];
        rset =(Button) findViewById(R.id.rset);

        t1=(TextView)findViewById(R.id.score);
        t2=(TextView)findViewById(R.id.aiscore);


        t1.setText(String.valueOf(scoreplayer));
        t2.setText(String.valueOf(scoreAI));

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(0);

        textView = (TextView) findViewById(R.id.dialogue);


        b[1][3] = (Button) findViewById(R.id.one);
        b[1][2] = (Button) findViewById(R.id.two);
        b[1][1] = (Button) findViewById(R.id.three);


        b[2][3] = (Button) findViewById(R.id.four);
        b[2][2] = (Button) findViewById(R.id.five);
        b[2][1] = (Button) findViewById(R.id.six);


        b[3][3] = (Button) findViewById(R.id.seven);
        b[3][2] = (Button) findViewById(R.id.eight);
        b[3][1] = (Button) findViewById(R.id.nine);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

        if(win==1){
        textView.setText("Player 'O':");}
        else if(win==2){
            textView.setText("Player 'X':");}


        // add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }


    class MyClickListener implements OnClickListener {
        int x;
        int y;


        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void onClick(View view) {
            if(win==1) {
                if (b[x][y].isEnabled() && (chance % 2 == 0)) {
                    b[x][y].setText("O");
                    c[x][y] = 0;
                    textView.setText("Player 'X':");
                    chance++;
                    checkBoard();
                    b[x][y].setEnabled(false);

                }
                if (b[x][y].isEnabled() && (chance % 2 != 0)) {
                    b[x][y].setText("X");
                    c[x][y] = 1;
                    textView.setText("Player 'O':");
                    chance++;
                    checkBoard();
                    b[x][y].setEnabled(false);
                }
            }
            if(win==2) {
                if (b[x][y].isEnabled() && (chance % 2 != 0)) {
                    b[x][y].setText("O");
                    c[x][y] = 0;
                    textView.setText("Player 'X':");
                    chance++;
                    checkBoard();
                    b[x][y].setEnabled(false);

                }
                if (b[x][y].isEnabled() && (chance % 2 == 0)) {
                    b[x][y].setText("X");
                    c[x][y] = 1;
                    textView.setText("Player 'O':");
                    chance++;
                    checkBoard();
                    b[x][y].setEnabled(false);
                }
            }
        }
    }


    // check the board to see if someone has won
    private boolean checkBoard() {
        boolean gameOver = false;
        Vibrator vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        ToneGenerator generator = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);

        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0 )
                ||(c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)||(c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                ||(c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)||(c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)||(c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            textView.setText("Game over. Player 'O' win!");
            scoreplayer=scoreplayer+1;
            imageView.setImageResource(R.drawable.emowinking);
            t1.setText(String.valueOf(scoreplayer));
            gameOver = true;
            win=1;
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++)
                    b[i][j].setEnabled(false);
            }if(vibration==true){
                vibrator.vibrate(500);}
            if(sound==true) {
                generator.startTone(ToneGenerator.TONE_CDMA_PIP, 500);
            }

        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            textView.setText("Game over. Player 'X' win !");
            imageView.setImageResource(R.drawable.emowinking);
            scoreAI=scoreAI+1;
            t2.setText(String.valueOf(scoreAI));
            gameOver = true;
            win=2;
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++)
                    b[i][j].setEnabled(false);
            }
            if(vibration==true){
                vibrator.vibrate(500);}
            if(sound==true) {
                generator.startTone(ToneGenerator.TONE_CDMA_PIP, 500);
            }
        } else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                textView.setText("Game over. It's a draw!");
                if(vibration==true){
                    vibrator.vibrate(500);}
                if(sound==true) {
                    generator.startTone(ToneGenerator.TONE_CDMA_PIP, 500);
                }
            }
        }
        return gameOver;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new  ContextThemeWrapper(this, R.style.AlertDialogCustom));

        builder.setMessage("Do you want to exit this game?");
        builder.setTitle("Back to Main Menu:");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}
