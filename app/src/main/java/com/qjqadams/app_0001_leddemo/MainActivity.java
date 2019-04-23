package com.qjqadams.app_0001_leddemo;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.os.ILedService;
import android.os.ServiceManager;

public class MainActivity extends AppCompatActivity {

    private boolean ledon = false;
    private Button button = null;
    private CheckBox checkBoxLed1 = null;
    private CheckBox checkBoxLed2 = null;
    private CheckBox checkBoxLed3 = null;
    private CheckBox checkBoxLed4 = null;
    private ILedService iLedService = null;

    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int i;
            ledon = !ledon;
            if (ledon) {
                checkBoxLed1.setChecked(true);
                checkBoxLed2.setChecked(true);
                checkBoxLed3.setChecked(true);
                checkBoxLed4.setChecked(true);
                button.setText("ALL OFF");
                Toast.makeText(getApplicationContext(), "ALL on", Toast.LENGTH_SHORT).show();

                try {
                    for (i = 0; i <  4; i++)
                        iLedService.ledCtrl(i, 1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else {
                checkBoxLed1.setChecked(false);
                checkBoxLed2.setChecked(false);
                checkBoxLed3.setChecked(false);
                checkBoxLed4.setChecked(false);
                button.setText("ALL ON");
                Toast.makeText(getApplicationContext(), "ALL off", Toast.LENGTH_SHORT).show();
                try {
                    for (i = 0; i <  4; i++)
                        iLedService.ledCtrl(i, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        try {
            switch (view.getId()) {
                case R.id.LED1:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "LED1 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0, 1);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "LED1 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0, 0);
                    }
                    break;
                case R.id.LED2:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "LED2 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(1, 1);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "LED2 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(1, 0);
                    }
                    break;
                case R.id.LED3:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "LED3 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(2, 1);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "LED3 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(2, 0);
                    }
                    break;
                case R.id.LED4:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "LED4 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(3, 1);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "LED4 off", Toast.LENGTH_SHORT).show();
                       iLedService.ledCtrl(3, 0);
                    }
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        iLedService = ILedService.Stub.asInterface(ServiceManager.getService("Led"));

        button = (Button) findViewById(R.id.BUTTON);

        checkBoxLed1 = (CheckBox) findViewById(R.id.LED1);
        checkBoxLed2 = (CheckBox) findViewById(R.id.LED2);
        checkBoxLed3 = (CheckBox) findViewById(R.id.LED3);
        checkBoxLed4 = (CheckBox) findViewById(R.id.LED4);

        button.setOnClickListener(new MyButtonListener());
/*        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ledon = !ledon;
                if (ledon)
                    button.setText("ALL OFF");
                else
                    button.setText("ALL ON");
            }
        });
*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
