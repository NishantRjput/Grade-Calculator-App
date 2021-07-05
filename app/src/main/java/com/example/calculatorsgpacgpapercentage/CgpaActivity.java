package com.example.calculatorsgpacgpapercentage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AlertDialog;

public class CgpaActivity extends AppCompatActivity {
    String obj;
    float cgpa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint({"WrongConstant", "ResourceType"})
    public void addition(View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.linearlayout);
        EditText editText = (EditText) findViewById(R.id.sem_no);

        /*EditText editText2 = (EditText) findViewById(R.id.total_marks);*/
        obj = editText.getText().toString();

        if (TextUtils.isEmpty(obj)) {
            editText.setError("Please Enter Required Field");
            editText.requestFocus();
            return;
        }


        gridLayout.removeAllViews();
        int parseInt = Integer.parseInt(obj);
        if (parseInt > 25) {
            editText.setError("Semester limit is 25");
            editText.setText("");
            editText.requestFocus();
            return;
        }


        editText.setEnabled(false);
       /* editText2.setEnabled(false);
        if (editText2.getText().toString().equals("")) {
            editText2.setHint("--");
        }*/
        int i = parseInt + 1;
        int i2 = parseInt;
        for (int i3 = 0; i3 < parseInt; i3++) {
            TextView textView = new TextView(this);
            textView.setTextAlignment(4);
            textView.setTextColor(-16777216);
            textView.setTextSize(18.0f);
            textView.setText("Semester " + i2 + " ");
            i2--;
            gridLayout.addView(textView, 0);
            EditText editText3 = new EditText(this);
            editText3.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            editText3.setTextColor(-16777216);
            editText3.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2,2)});
            editText3.setId(i3);
            editText3.setTextAlignment(4);
            editText3.setHint(" Sem-SGPA ");
            gridLayout.addView(editText3, 1);

            Spinner spinner = new Spinner(this);
            spinner.setId(i);
            i++;
            ArrayList arrayList = new ArrayList();
            arrayList.add("Select Grade");
            arrayList.add("O");
            arrayList.add("E");
            arrayList.add("A");
            arrayList.add("B");
            arrayList.add("C");
            arrayList.add("D");
            arrayList.add("F");
            arrayList.add("M");
            arrayList.add("S");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, arrayList);
            arrayAdapter.setDropDownViewResource(17367049);
            spinner.setAdapter(arrayAdapter);
            spinner.setVisibility(View.GONE);
            gridLayout.addView(spinner, 2);

        }


        Button button = new Button(this);
        button.setTextAlignment(4);
        button.setTextColor(-16777216);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CgpaActivity.this.show(parseInt);
            }
        });
        button.setText("Calculate");
        gridLayout.addView(button, parseInt * 3);
        Toast.makeText(this, "Semesters Added Successfully", 0).show();



    }


    private void show(int noOfSemesters) {

        //Here you have to do cgpa calculation by getting value of editext and spinner

       /* //for referencing grade points
        Map<String, Integer> gradePoint = new HashMap<>();
        gradePoint.put("Select Grade", -1);
        gradePoint.put("O", 10);
        gradePoint.put("E", 9);
        gradePoint.put("A", 8);
        gradePoint.put("B", 7);
        gradePoint.put("C", 6);
        gradePoint.put("D", 5);
        gradePoint.put("F", 2);
        gradePoint.put("M", 0);
        gradePoint.put("S", 0);*/

        int idForEditText = 0;
       /* int idForSpinner = noOfSubjects + 1;
        int totalCreditPoints = 0;*/
        /*int obtainedCreditPoints = 0;*/
        float s1=0;
        for (int i = 1; i <= noOfSemesters; i++) {

            //get the credits edit text and check if empty
            EditText semSgpaEditText = findViewById(idForEditText++);
            /* semSgpaEditText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2,2)});*/
            String sgpa = semSgpaEditText.getText().toString();
            if (TextUtils.isEmpty(sgpa)){
                semSgpaEditText.setError("Please enter the SGPA");
                semSgpaEditText.requestFocus();
                return;
            }


            float semSgpa = Float.valueOf(sgpa);
            s1=s1+semSgpa;
            cgpa = s1/noOfSemesters;



           /* //get the spinner for grade point
            Spinner gradeSpinner = findViewById(idForSpinner++);
            int point = gradePoint.get(gradeSpinner.getSelectedItem().toString());
            //if nothing is selected
            if (point == -1){
                Toast.makeText(this, "Please enter the grade", Toast.LENGTH_SHORT).show();
                return;
            }*/

            /*totalCreditPoints += credits;
            obtainedCreditPoints += credits*point;*/


        }





        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) "Success");
        builder.setIcon(R.drawable.successicon);
        builder.setMessage("Your Secured CGPA Is: " +Math.round(cgpa*100.0)/100.0);
        builder.setNegativeButton((CharSequence) "OK", (DialogInterface.OnClickListener) null).setCancelable(false);
        builder.create();
        builder.show();

    }

    private class DecimalDigitsInputFilter implements InputFilter {
        private Pattern mPattern;
        public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
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
}