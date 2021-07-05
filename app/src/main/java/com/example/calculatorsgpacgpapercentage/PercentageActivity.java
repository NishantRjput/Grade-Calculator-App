package com.example.calculatorsgpacgpapercentage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AlertDialog;


public class PercentageActivity extends AppCompatActivity {

    EditText cgpa_EditText ;
    Button calculate_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage);

        calculate_Button = (Button)findViewById(R.id.calculate_button);
        cgpa_EditText = (EditText)findViewById(R.id.cgpa_editText);
        cgpa_EditText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2,2)});


        calculate_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cgpa = cgpa_EditText.getText().toString();
                if (TextUtils.isEmpty(cgpa)) {
                    cgpa_EditText.setError("Please Enter Required Field");
                    cgpa_EditText.requestFocus();
                    return;
                }
                float finalCgpa = Float.valueOf(cgpa);
                float percentage = (float) ((finalCgpa - 0.5)*10);

                AlertDialog.Builder builder = new AlertDialog.Builder(PercentageActivity.this);
                builder.setTitle("Success");
                builder.setIcon(R.drawable.successicon);
                builder.setMessage( "Your Secured Percentage Is : "+percentage+"%");
                builder.setNegativeButton((CharSequence) "OK", (DialogInterface.OnClickListener) null).setCancelable(false);
                builder.create();
                builder.show();
            }
        });
    }
}

class DecimalDigitsInputFilter implements InputFilter {
    private Pattern mPattern;
    DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }
}