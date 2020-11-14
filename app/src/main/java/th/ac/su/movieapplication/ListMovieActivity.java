package th.ac.su.movieapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListMovieActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;
    MyDatabaseHelper myDB;
    ArrayList<String> ID_movie, NAME_movie, TYPE_movie, RUNTIME_movie;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        myDB = new MyDatabaseHelper(ListMovieActivity.this);
        ID_movie = new ArrayList<>();
        NAME_movie = new ArrayList<>();
        TYPE_movie = new ArrayList<>();
        RUNTIME_movie = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ListMovieActivity.this,this, ID_movie, NAME_movie, TYPE_movie,
                RUNTIME_movie);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListMovieActivity.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                ID_movie.add(cursor.getString(0));
                NAME_movie.add(cursor.getString(1));
                TYPE_movie.add(cursor.getString(2));
                RUNTIME_movie.add(cursor.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }



}