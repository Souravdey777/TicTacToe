package souravapp.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    Button b1,b2,b3,b4,vib,m;
    boolean vibration=true, sound=true;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        vib = (Button) findViewById(R.id.vib);
        m=(Button)findViewById(R.id.m);

        vib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibration==true){vibration=false;vib.setText("Vibration: OFF");}
                else if(vibration==false){vibration=true;vib.setText("Vibration: ON");}
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound==true){sound=false;m.setText("Sound: OFF");}
                else if(sound==false){sound=true;m.setText("Sound: ON");}
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Splayer.class);
                intent.putExtra("vibra", vibration );
                intent.putExtra("audio", sound );
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,mplayer.class);
                intent.putExtra("vibra", vibration );
                intent.putExtra("audio", sound );
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(v.getContext(), R.style.AlertDialogCustom));

                builder.setMessage("Tic Tac Teo or (Xs and Os) is a paper-and-pencil game for two players, X and O, who take turns marking the spaces in a 3×3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game. This is my first Android Application. Hope you liked it. You can mail me if you have any suggestions. Thank You for downloading\n" +
                        "\n" +
                        "         Email ID: piyush.kolkata@gmail.com");
                builder.setTitle("ABOUT THE APPLICATION");
                builder.setPositiveButton("Mail the Developer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] TO ={"piyush.kolkata@gmail.com"};
                        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        final PackageManager pm = getPackageManager();
                        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
                        ResolveInfo best = null;
                        for (final ResolveInfo info : matches)
                            if (info.activityInfo.packageName.endsWith(".gm") ||
                                    info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
                        if (best != null)
                            intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                        intent.putExtra(intent.EXTRA_EMAIL, TO);
                        intent.putExtra(intent.EXTRA_SUBJECT, "Related to Tic Tac Toe App");
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog =builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

        builder.setMessage("Do you want to exit?");
        builder.setTitle("Exit");

        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}
