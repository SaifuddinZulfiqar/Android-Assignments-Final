package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<word> words = new ArrayList<word>();

        words.add(new word("Red", "La'al",R.drawable.color_red));
        words.add(new word("Yellow", "Peela",R.drawable.color_mustard_yellow));
        words.add(new word("Green", "Hara",R.drawable.color_green));
        words.add(new word("Brown", "Kat'thai",R.drawable.color_brown));
        words.add(new word("Black", "Kala",R.drawable.color_black));
        words.add(new word("White", "Safay'd",R.drawable.color_white));


        WordAdapter Adapter =
                new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word w = words.get(position);


        }
    });
}}
