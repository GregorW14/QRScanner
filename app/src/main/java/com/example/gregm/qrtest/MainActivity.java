package com.example.gregm.qrtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    static final String SCAN = "com.google.zxing.client.android.SCAN";
    static String contents;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ScanQR(View v){

        try{
            Intent in = new Intent (SCAN);
            in.putExtra("SCAN_MODE", "QR_CODE_MODE");
            new IntentIntegrator(this).initiateScan();
            startActivityForResult(in, 0);
        }catch (ActivityNotFoundException e) {
            // TODO: handle exception
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Log.i("QR Result", contents);
                Intent i1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i1);
                // Handle successful scan
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Log.i("QR Result","Result canceled");
            }
        }
    }




}