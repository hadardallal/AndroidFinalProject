package com.example.AndroidProject.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AndroidProject.MusicService;
import com.example.AndroidProject.R;
import com.example.AndroidProject.animals.AnimalsData;
import com.example.AndroidProject.animals.CustomeAdapter;

import java.util.ArrayList;


public class AnimalFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<AnimalsData> dataSet;
    private CustomeAdapter adapter;
    private Button btnStopMusic;
    private boolean isMusicPlaying = true;

    public AnimalFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
    }

    private void initDataSet() {
        if (getActivity() != null) {
            dataSet = new ArrayList<>();

            dataSet.add(new AnimalsData(R.string.animal_name_elephant, R.string.animal_desc_elephant, R.drawable.elephant));
            dataSet.add(new AnimalsData(R.string.animal_name_lion, R.string.animal_desc_lion, R.drawable.lion));
            dataSet.add(new AnimalsData(R.string.animal_name_hippopotamus, R.string.animal_desc_hippopotamus, R.drawable.hippo));
            dataSet.add(new AnimalsData(R.string.animal_name_monkey, R.string.animal_desc_monkey, R.drawable.monkey));
            dataSet.add(new AnimalsData(R.string.animal_name_alligator, R.string.animal_desc_alligator, R.drawable.alligator));
            dataSet.add(new AnimalsData(R.string.animal_name_zebra, R.string.animal_desc_zebra, R.drawable.zebra));
            dataSet.add(new AnimalsData(R.string.animal_name_giraffe, R.string.animal_desc_giraffe, R.drawable.giraffe));
            dataSet.add(new AnimalsData(R.string.animal_name_penguin, R.string.animal_desc_penguin, R.drawable.penguin));



        }
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zoo, container, false);



        btnStopMusic = view.findViewById(R.id.btnStopMusic);





        btnStopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic();
            }
        });

        toggleMusic();


        recyclerView = view.findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomeAdapter(getContext(), dataSet);
        recyclerView.setAdapter(adapter);


        return view;
    }



    private void toggleMusic() {
        Intent serviceIntent = new Intent(getActivity(), MusicService.class);

        if (!isMusicPlaying) {
            serviceIntent.setAction(MusicService.ACTION_PLAY);
            btnStopMusic.setText(R.string.stop_music);
            getActivity().startService(serviceIntent);
            isMusicPlaying = true;
        } else {
            serviceIntent.setAction(MusicService.ACTION_PAUSE);
            btnStopMusic.setText(R.string.play_music);
            getActivity().startService(serviceIntent);
            isMusicPlaying = false;
        }
    }
}



