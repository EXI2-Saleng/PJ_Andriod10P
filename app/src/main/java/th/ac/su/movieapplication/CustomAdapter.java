package th.ac.su.movieapplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList ID_movie,NAME_movie,TYPE_movie,RUNTIME_movie;

    CustomAdapter(Activity activity, Context context, ArrayList ID_movie, ArrayList NAME_movie, ArrayList TYPE_movie,
                  ArrayList RUNTIME_movie){
        this.activity = activity;
        this.context = context;
        this.ID_movie = ID_movie;
        this.NAME_movie = NAME_movie;
        this.TYPE_movie = TYPE_movie;
        this.RUNTIME_movie = RUNTIME_movie;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.ID_movie_txt.setText(String.valueOf(ID_movie.get(position)));
        holder.NAME_movie_txt.setText(String.valueOf(NAME_movie.get(position)));
        holder.TYPE_movie_txt.setText(String.valueOf(TYPE_movie.get(position)));
        holder.RUNTIME_movie_txt.setText(String.valueOf(RUNTIME_movie.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(ID_movie.get(position)));
                intent.putExtra("NAME", String.valueOf(NAME_movie.get(position)));
                intent.putExtra("type", String.valueOf(TYPE_movie.get(position)));
                intent.putExtra("runtime", String.valueOf(RUNTIME_movie.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ID_movie.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ID_movie_txt, NAME_movie_txt, TYPE_movie_txt, RUNTIME_movie_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ID_movie_txt = itemView.findViewById(R.id.ID_movie_txt);
            NAME_movie_txt = itemView.findViewById(R.id.NAME_movie_txt);
            TYPE_movie_txt = itemView.findViewById(R.id.TYPE_movie_txt);
            RUNTIME_movie_txt = itemView.findViewById(R.id.RUNTIME_movie_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
           // Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
           // mainLayout.setAnimation(translate_anim);
        }

    }
}
