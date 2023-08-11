package com.example.photo_that;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    // creating variables on below line.
    private Button recordVideoBtn;
    private Uri videoPath;
    private VideoView videoView;

    private ActivityResultLauncher<Intent> videoCaptureLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing variables on below line.
        recordVideoBtn = findViewById(R.id.idBtnRecordVideo);

        videoView = findViewById(R.id.videoView);

        videoCaptureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            videoPath = data.getData();
                            videoView.setVideoURI(videoPath);
                            videoView.start();
                            Log.i("VIDEO_RECORD_TAG", "Video is recorded and available at path" + videoPath);
                        }
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Log.i("VIDEO_RECORD_TAG", "Video is cancelled");
                    } else {
                        Log.i("VIDEO_RECORD_TAG", "Error");
                    }
                });

        // adding click listener for recording button.
        recordVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line opening an intent to capture a video.
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                // on below line starting an activity for result.
                videoCaptureLauncher.launch(i);
            }
        });




    }}




