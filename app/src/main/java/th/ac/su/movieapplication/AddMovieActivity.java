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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddMovieActivity extends AppCompatActivity {

    public static final int REQUEST_GALLERY = 1;

    Bitmap bitmap;
    ImageView imageView1;
    EditText movie_input,genres_input,runtime_input;
    Button ADD;
    RadioGroup G_TYPE;
    RadioButton R_Adventure;
    RadioButton R_Comedy;
    RadioButton R_Drama;
    RadioButton R_Erotica;
    RadioButton R_Fantasy;
    RadioButton R_Horror;
    RadioButton R_Sci_Fi;
    RadioButton R_Western;
    String TYPE_MOVIE_S ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        imageView1 = (ImageView)findViewById(R.id.imageView);
        G_TYPE =findViewById(R.id.radioGroup_TYPE);
        R_Adventure =findViewById(R.id.radioButton_Adventure);
        R_Comedy =findViewById(R.id.radioButton_Comedy);
        R_Drama =findViewById(R.id.radioButton_Drama);
        R_Erotica =findViewById(R.id.radioButton_Erotica);
        R_Fantasy =findViewById(R.id.radioButton_Fantasy);
        R_Horror =findViewById(R.id.radioButton_Horror);
        R_Sci_Fi =findViewById(R.id.radioButton_Sci_Fi);
        R_Western =findViewById(R.id.radioButton_Western);
        G_TYPE.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton TTT = AddMovieActivity.this.findViewById(i);
                TYPE_MOVIE_S =TTT.getText().toString();
                Toast T1 =Toast.makeText(AddMovieActivity.this,"CHECKED "+ TYPE_MOVIE_S,Toast.LENGTH_LONG);
                T1.show();
            }
        });


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
                        TYPE_MOVIE_S.toString().trim(),
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