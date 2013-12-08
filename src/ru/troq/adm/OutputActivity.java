package ru.troq.adm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class OutputActivity extends Activity {
    int counter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final ScrollView sv = new ScrollView(this);
        final LinearLayout ml = new LinearLayout(this);
        ml.setOrientation(LinearLayout.VERTICAL);
        sv.setBackgroundResource(Background.getBackground());
        setContentView(sv);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER;
        final Context context = this;
        counter = 0;
        // Создаем столько кнопок, сколько участвует игроков
        for (final ADM adm : InputActivity.PLAYERS) {
            final LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            final Button player = new Button(this);
            player.setText(adm.getName());
            player.setMinimumWidth(200);
            player.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    incrCount();
                    Intent intent = new Intent(context, FinalActivity.class);
                    intent.putExtra("ADM", adm);
                    player.setEnabled(false);
                    startActivityForResult(intent, 0);
                    if (counter == InputActivity.PLAYERS.size()) {
                        final Button newButton = new Button(context);
                        newButton.setText(" Начать заново ");
                        newButton.setLayoutParams(params);
                        ml.addView(newButton);
                        sv.removeAllViews();
                        sv.addView(ml);
                        newButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intentNew = new Intent(context, InputActivity.class);
                                startActivity(intentNew);
                            }
                        });
                    }
                }
            });
            player.setLayoutParams(params);
            ll.addView(player);
            ml.addView(ll, params);
            sv.removeAllViews();
            sv.addView(ml);
        }

    }

    private void incrCount() {
        counter++;
    }

    @Override
    public void onBackPressed() {

    }

}
