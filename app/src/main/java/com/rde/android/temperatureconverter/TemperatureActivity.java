package com.rde.android.temperatureconverter;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class TemperatureActivity extends AppCompatActivity {

    private EditText textInput ;
    private RadioButton celciusButton;
    private RadioButton fahrenheitButton;
    private Button calculateButton;
    private TextView textOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
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

        textInput = findViewById(R.id.TextInput);
        celciusButton = findViewById(R.id.CelciusButton);
        fahrenheitButton = findViewById(R.id.FarenheitButton);
        calculateButton = findViewById(R.id.CalculateButton);
        textOutput = findViewById(R.id.TextOutput);

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonClickHandler();
            }
        });

    }

    private void buttonClickHandler() {
        String text = textInput.getText().toString();
        if(text.length() == 0) {
            showToast("Please enter a a valid number");
            return;
        }

        float value = Float.parseFloat(text);
        String result = "";
        if(celciusButton.isChecked()) {
            float c = convertFahrenheitToCelsius(value);
            result = "" + c + " Celsius";
        }
        else if(fahrenheitButton.isChecked()) {
            float f = convertCelsiusToFahrenheit(value);
            result = "" + f + " Fahrenheit";
        }
        else {
            showToast("Please select a radio button");
            return;
        }
        textOutput.setText(result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temperature, menu);
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
            showToast("Me Settings");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }
    private float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
