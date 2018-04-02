package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.R;
import practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.general.Constants;
import practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.service.PracticalTest01Var02Service;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {

    Button sum, dif, navigate;
    TextView op;
    EditText number1, number2;


    private int serviceStatus = Constants.SERVICE_STOPPED;
    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            int nr1, nr2;
            String operation = "";

            if (number1.getText().toString().matches("") || number2.getText().toString().matches("")) {
                CharSequence error = "The field is empty";
                Toast.makeText(PracticalTest01Var02MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
            if (!number1.getText().toString().matches("") && !number2.getText().toString().matches("")) {
                nr1 = Integer.valueOf(number1.getText().toString());
                nr2 = Integer.valueOf(number2.getText().toString());

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02Service.class);
                intent.putExtra(Constants.FIRST_NUMBER, nr1);
                intent.putExtra(Constants.SECOND_NUMBER, nr2);

                switch (view.getId()) {
                    case R.id.sum:
                        int suma = nr1 + nr2;
                        operation = String.valueOf(nr1) + ' ' + sum.getText() + ' ' + String.valueOf(nr2) + " = " + String.valueOf(suma);
                        op.setText(operation);

                        if(serviceStatus == Constants.SERVICE_STOPPED){
                            getApplicationContext().startService(intent);
                            serviceStatus = Constants.SERVICE_STARTED;
                        }

                        break;
                    case R.id.dif:
                        int diferenta = nr1 - nr2;
                        operation = String.valueOf(nr1) + ' ' + dif.getText() + ' ' + String.valueOf(nr2) + " = " + String.valueOf(diferenta);
                        op.setText(operation);

                        if(serviceStatus == Constants.SERVICE_STOPPED){
                            getApplicationContext().startService(intent);
                            serviceStatus = Constants.SERVICE_STARTED;
                        }

                        break;
                    case R.id.navigate:
                        Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var02SecondaryActivity.class);
                        String op_second_activ = op.getText().toString();
                        intent1.putExtra(Constants.OP, op_second_activ);
                        startActivityForResult(intent1, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                        break;
                }
            }
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[TEST]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        sum = findViewById(R.id.sum);
        sum.setOnClickListener(buttonClickListener);
        dif = findViewById(R.id.dif);
        dif.setOnClickListener(buttonClickListener);
        navigate = findViewById(R.id.navigate);
        navigate.setOnClickListener(buttonClickListener);

        op = findViewById(R.id.textView);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        if (savedInstanceState != null) {
            String memorizedValues = "";
            if (savedInstanceState.containsKey("nr1")) {
                number1.setText(savedInstanceState.getString("nr1"));
                memorizedValues += savedInstanceState.getString("nr1");
            }
            if (savedInstanceState.containsKey("nr2")) {
                number2.setText(savedInstanceState.getString("nr2"));
                memorizedValues += " & " + savedInstanceState.getString("nr2");
            }

            Toast.makeText(this, memorizedValues, Toast.LENGTH_LONG).show();
        }

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(Constants.NR1, number1.getText().toString());
        savedInstanceState.putString(Constants.NR2, number2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String memorizedValues = "";
            if (savedInstanceState.containsKey("nr1")) {
            number1.setText(savedInstanceState.getString("nr1"));
            memorizedValues += savedInstanceState.getString("nr1");
        }
        if (savedInstanceState.containsKey("nr2")) {
            number2.setText(savedInstanceState.getString("nr2"));
            memorizedValues += " & " + savedInstanceState.getString("nr2");
        }

        Toast.makeText(this, memorizedValues, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onStop() {
        Intent intent = new Intent(this, PracticalTest01Var02Service.class);
        stopService(intent);
        super.onStop();
    }

}
