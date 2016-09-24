package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<word> words = new ArrayList<word>();

        words.add(new word("Where are you going?", "Kahan ja rahe ho?"));
        words.add(new word("What is your name?", "Aap ka naam kya hai?"
        ));
        words.add(new word("My name is...", "Mera naam hai..."));
        words.add(new word("How are you feeling?", "Aap ko kesa lag raha hai?"));
        words.add(new word("I’am feeling good.", "Mujhe acha lag raha hai."));
        words.add(new word("Are you coming?", "Aap aarahe ho?"));
        words.add(new word("Yes, I’m coming.", "Han main aaraha hun."));
        words.add(new word("I’m coming.", "Main aaraha hun."));
        words.add(new word("Let’s go.", "Chalo chaltey hain."));
        words.add(new word("Come here.", "Idhar Aao."));



        WordAdapter Adapter =
                new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word w = words.get(position);


        }
    });
}}