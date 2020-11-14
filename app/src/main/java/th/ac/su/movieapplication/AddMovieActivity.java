package th.ac.su.movieapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.provider.MediaStore.Images.Media;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddMovieActivity extends AppCompatActivity {

    public static final int REQUEST_GALLERY = 1;

    Bitmap bitmap;
    ImageView imageView1;
    EditText movie_input,genres_input,runtime_input;
    Button ADD;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        imageView1 = (ImageView)findViewById(R.id.imageView);

        Button buttonIntent = (Button)findViewById(R.id.button);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select Picture"), REQUEST_GALLERY);
            }
        });
        movie_input= findViewById(R.id.movie_edit_text);
        genres_input= findViewById(R.id.movie_genres_edit_text);
        runtime_input= findViewById(R.id.runtime_edit_text);
        ADD = findViewById(R.id.add_button);
        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddMovieActivity.this);
                myDB.addMovie(movie_input.getText().toString().trim(),
                        genres_input.getText().toString().trim(),
                        Integer.valueOf(runtime_input.getText().toString().trim()));
                Intent intent = new Intent(AddMovieActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    public void onActivityResult(int requestCode, int resultCode
            , Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = Media.getBitmap(this.getContentResolver(), uri);
                imageView1.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////




}