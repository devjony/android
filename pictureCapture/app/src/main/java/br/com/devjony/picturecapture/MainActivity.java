package br.com.devjony.picturecapture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    public void action(View v) {
        takePicture();
    }

    private void takePicture() {
        Intent take = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(take.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(take, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");

            bitmap = imageBitmap;
            imageView.setImageBitmap(imageBitmap);
        }
    }

    public void erasePicture(View v) {
        imageView.setImageDrawable(null);

        ((EditText)findViewById(R.id.fileName)).setText("");
    }

    private File createImageFile() throws IOException {
//        Using external card
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File storageDir = getFilesDir();
        String fileName = ((EditText)findViewById(R.id.fileName)).getText().toString();

        return new File(storageDir, fileName + ".png");
    }

    public void savePicture(View v) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(createImageFile());
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        outputStream.close();
        Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show();
    }
}