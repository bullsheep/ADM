package ru.troq.adm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalActivity  extends Activity {
    private static String message = "Привет, {0}! \nУ меня для тебя отличные новости! " +
            "\nВ этом году твоим анонимным получателем подарка будет {1}. " +
            "\nЗапомни эту информацию, нажми кнопку ниже и передай телефон следующему участнику.";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LinearLayout ml = new LinearLayout(this);
        ml.setOrientation(LinearLayout.VERTICAL);
        setContentView(ml);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        Intent intent = getIntent();
        ADM adm = (ADM) intent.getSerializableExtra("ADM");
        String app = AvailableAPPs.getInstance().getAPPFor(adm);
        adm.setApp(app);
        TextView text = new TextView(this);
        text.setText(MessageFormat.format(message, adm.getName(), adm.getApp()));
        setResult(RESULT_OK, intent);
        ml.addView(text);

        Button doneButton = new Button(this);
        doneButton.setText("Ок, я всё понял!");
        doneButton.setLayoutParams(params);
        ml.addView(doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {


    }
}
