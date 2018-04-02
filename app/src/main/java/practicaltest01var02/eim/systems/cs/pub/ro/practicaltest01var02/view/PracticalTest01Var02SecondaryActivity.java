package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.R;
import practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.general.Constants;

public class PracticalTest01Var02SecondaryActivity extends AppCompatActivity {

    Button correct, incorrect;
    TextView text;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.correct:
                    setResult(RESULT_OK);
                    break;
                case R.id.incorrect:
                    setResult(RESULT_CANCELED);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_secondary);

        correct = findViewById(R.id.correct);
        correct.setOnClickListener(buttonClickListener);
        incorrect = findViewById(R.id.incorrect);
        incorrect.setOnClickListener(buttonClickListener);

        text = findViewById(R.id.operation2);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.OP)) {
            String operation = intent.getStringExtra(Constants.OP);
            text.setText(String.valueOf(operation));
        }
    }
}
