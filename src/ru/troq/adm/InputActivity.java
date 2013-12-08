package ru.troq.adm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class InputActivity extends Activity implements View.OnClickListener {

    public int numberOfPlayers;
    public static Set<ADM> PLAYERS = new LinkedHashSet<ADM>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PLAYERS.clear();
        setContentView(R.layout.main);
        EditText numberInput = (EditText) findViewById(R.id.numberInput);
        numberInput.setHint("Введите количество игроков");
        Button okButton = (Button) findViewById(R.id.buttonOK);
        okButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        EditText numberInput = (EditText) findViewById(R.id.numberInput);
        try {
            numberOfPlayers = Integer.valueOf(numberInput.getText().toString());
        } catch (Exception e) {
            //Do nothing lol
        }
        if (! (numberOfPlayers > 2)) {
            new AlertDialog.Builder(this)
                    .setTitle("Некорректный ввод")
                    .setMessage("Введите корректное число игроков." +
                            "\nИгроков должно быть больше двух.")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        } else if (numberOfPlayers == 666) {
            PLAYERS = getDefaultPlayers();
            Intent intent = new Intent(this, OutputActivity.class);
            startActivity(intent);
        } else {
            createInputNamesField(this);
        }
    }

    private void createInputNamesField(final Context context) {
        ScrollView sv = new ScrollView(this);
        LinearLayout ml = new LinearLayout(this);
        ml.setOrientation(LinearLayout.VERTICAL);
        sv.setBackgroundResource(Background.getBackground());
        setContentView(sv);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        // Создаем столько полей ввода, сколько участвует игроков
        for (int j = 0; j < numberOfPlayers; j++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            EditText name = new EditText(this);
            name.setId(j);
            name.setHint("Введите имя игрока N" + (j + 1));
            ll.addView(name);
            ml.addView(ll, params);
        }
        final Button okBtn = new Button(this);
        okBtn.setText("OK");
        // set the layoutParams on the button
        okBtn.setLayoutParams(params);

        okBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int j = 0; j < numberOfPlayers; j++) {
                    EditText name = (EditText) findViewById(j);
                    PLAYERS.add(new ADM(name.getText().toString()));
                }
                if (PLAYERS.size() != numberOfPlayers) {
                    new AlertDialog.Builder(context)
                            .setTitle("Некорректный ввод")
                            .setMessage("Имена игроков не должны повторяться")
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                    PLAYERS.clear();
                } else {
                    Intent intent = new Intent(context, OutputActivity.class);
                    startActivity(intent);
                }
            }
        });
        //Add button to LinearLayout
        ml.addView(okBtn);
        sv.addView(ml);
    }


    private Set<ADM> getDefaultPlayers() {
        List<String> defaultPlayersNames = Arrays.asList("АНЯ", "ГАЙКА", "ИЛЮША", "КСЮША", "МАКС", "МИКОЛА", "МИШАНЯ");
        Set<ADM> defaultPlayers = new LinkedHashSet<>();
        for (String name : defaultPlayersNames) {
            defaultPlayers.add(new ADM(name));
        }
        return defaultPlayers;
    }

}
