package com.example.android.bitconvert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ConvertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        //Code to get data from previous activity
        Intent intent = getIntent();
        final double dollar_data = intent.getDoubleExtra("Dollar_Value", 0.0);
        final double naira_data = intent.getDoubleExtra("Naira_Value", 0.0);
        final double pounds_data = intent.getDoubleExtra("Pounds_Value", 0.0);

        //Initializing textview, edittext and button
        final TextView result=(TextView)findViewById(R.id.result);
        final EditText et1=(EditText)findViewById(R.id.input_currency);
        Button button = (Button) findViewById(R.id.convert_button);


        //Make the button clickable and set the action after it is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Access the RadioGroup view and save it to a variable.
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.conversion_options);
                //Get the id of the RadioButton that is checked and save it
                //as an integer variable.
                int conversionChoice = radioGroup.getCheckedRadioButtonId();
                //Use if statements to respond based on whether
                //it is the id of the correct answer.
                if (conversionChoice == R.id.bit_dollars) {
                    // Perform action on key press
                    double f = Double.parseDouble(et1.getText().toString());
                    String bit_to_dollar_result = String.valueOf(dollar_data * f);
                    String answer = bit_to_dollar_result + " US Dollars";
                    result.setText(answer);
                }
                else
                    if (conversionChoice == R.id.bit_naira){
                        // Perform action on key press
                        double f = Double.parseDouble(et1.getText().toString());
                        String bit_to_naira_result = String.valueOf(naira_data * f);
                        String answer = bit_to_naira_result + " Naira";
                        result.setText(answer);
                    }
                    else
                        if (conversionChoice == R.id.bit_pounds){
                            // Perform action on key press
                            double f = Double.parseDouble(et1.getText().toString());
                            String bit_to_pounds_result = String.valueOf(pounds_data * f);
                            String answer = bit_to_pounds_result + " Pounds";
                            result.setText(answer);
                        }
                        else
                            if (conversionChoice == R.id.dollars_bit){
                                // Perform action on key press
                                double f = Double.parseDouble(et1.getText().toString());
                                String dollars_to_bit_result = String.valueOf(f / dollar_data);
                                String answer = dollars_to_bit_result + " BTC";
                                result.setText(answer);
                            }
                            else
                            if (conversionChoice == R.id.pounds_bit){
                                // Perform action on key press
                                double f = Double.parseDouble(et1.getText().toString());
                                String pounds_to_bit_result = String.valueOf(f / pounds_data);
                                String answer = pounds_to_bit_result + " BTC";
                                result.setText(answer);
                            }
                            else
                            if (conversionChoice == R.id.naira_bit){
                                // Perform action on key press
                                double f = Double.parseDouble(et1.getText().toString());
                                String naira_to_bit_result = String.valueOf(f / naira_data);
                                String answer = naira_to_bit_result + " BTC";
                                result.setText(answer);
                            }
            }
        });
    }

}