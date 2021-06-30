package com.example.ejemplolectorqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView miEscaner;  // ese es el tipo de clase definida por la libreria



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            checkPermission();
        }


    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    public void capturar(View view){
        miEscaner=new ZXingScannerView(this);
        setContentView(miEscaner);
        miEscaner.setResultHandler(this);
        miEscaner.startCamera();

    }

    @Override   //son los métodos definidos en la interface ZXingScannerView.ResultHandler
    public void handleResult(Result result) {
        // En la variable result obtendremos el texto del código QR
        // Crearemos un Intent para navegar a la dirección escaneada
        Intent accion = new Intent("android.intent.action.VIEW",
                Uri.parse(result.getText()));
        startActivity(accion);
    }


    @Override  //son los métodos definidos en la interface ZXingScannerView.ResultHandler
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
