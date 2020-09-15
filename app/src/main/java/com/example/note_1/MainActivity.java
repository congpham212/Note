package com.example.note_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnAddNote;
    private Intent intent;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        controlButton(this);

    }

    private void controlButton(final Activity main) {
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(main, Main2Activity.class);
                startActivity(intent);
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
//                builder.setTitle("Bạn có chắc muốn thoát khỏi app");
//                builder.setMessage("Hãy lựa chọ bên dưới để xác nhận");
//                builder.setIcon(android.R.drawable.ic_dialog_alert);
//                builder.setCancelable(false);
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        onBackPressed();
//                    }
//                });
//
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                builder.show();
            }
        });
    }

    private void anhXa() {
        btnAddNote = (Button) findViewById(R.id.btn_add);

    }




}
