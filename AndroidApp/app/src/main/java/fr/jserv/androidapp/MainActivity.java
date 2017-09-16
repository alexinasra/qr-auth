package fr.jserv.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.google.zxing.Result;


import java.net.URISyntaxException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static final String EXTRA_MESSAGE = "fr.jserv.androidapp.MESSAGE";
    private ZXingScannerView mScannerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);
        // Set the scanner view as the content view
        setContentView(mScannerView);

        //setContentView(R.layout.activity_main);
        //editText = (EditText) findViewById(R.id.editText);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Prints scan results
        // Logger.verbose("result", rawResult.getText());
        // Prints the scan format (qrcode, pdf417 etc.)
        // Logger.verbose("result", rawResult.getBarcodeFormat().toString());
        //If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, rawResult.getText());
        setResult(RESULT_OK, intent);
        startActivity(intent);
        //finish();
    }

 /*   public void sendMessage(View view){
        // Let's do something
 //       Intent intent = new Intent(this,DisplayMessageActivity.class);
        //String message = editText.getText().toString();
        //attemptSend();

 //       intent.putExtra(EXTRA_MESSAGE,message);
 //       startActivity(intent);
     }
*/
}