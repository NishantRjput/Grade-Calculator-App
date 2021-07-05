package com.example.calculatorsgpacgpapercentage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button resultButton , shareButton , supportButton , sgpaButton , cgpaButton , percentageButton;

    private Toast toast;
    private long lastBackPressTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For BPUT Result Button
        resultButton = (Button)findViewById(R.id.bput_result_link);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });  // Code Of BPUT ResultButton Ends Here

        // For Share Button
        shareButton = (Button)findViewById(R.id.share_icon);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String body ="https://drive.google.com/file/d/180romjJ26RmsicF-qcEFX8OvXYKAsxd6/view?usp=drivesdk";
                String subject = "Share the App among your friends";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(sharingIntent,"Share via"));

            }
        });  // Code Of Share Button Ends Here

        // For Help/Support Button
        supportButton = (Button)findViewById(R.id.help_support);
        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] strTo = { "royalrajput7641@gmail.com" };
                intent.putExtra(Intent.EXTRA_EMAIL,strTo);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Write To Us");
                intent.putExtra(Intent.EXTRA_TEXT,"...!!! Write your query here,we will try to reach you soon !!!...");
                intent.setType("message/rfc822");
                intent.setPackage("com.google.android.gm");
                startActivity(intent);
            }
        });  // Code Of Help/Support Button Ends Here


        // For Sgpa Button
        sgpaButton = (Button)findViewById(R.id.calculate_sgpa);
        sgpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SgpaActivity.class));
            }
        });  // Code Of Sgpa Button Ends Here


        // For Percentage Button
        percentageButton = (Button)findViewById(R.id.calculate_percentage);
        percentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PercentageActivity.class));
            }
        });  // Code of Percentage Button Ends Here

        // For Cgpa Button
        cgpaButton = (Button)findViewById(R.id.calculate_cgpa);
        cgpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CgpaActivity.class));
            }
        });  // Code of Cgpa Button Ends Here

    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Tap again to exit app", 4000);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }



}