package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        final ArrayList<word> words = new ArrayList<word>();

        words.add(new word("Father", "Abbu",R.drawable.family_father));
        words.add(new word("Mother", "Ammi",R.drawable.family_mother));
        words.add(new word("Son", "Beta",R.drawable.family_son));
        words.add(new word("Daughter", "Beti",R.drawable.family_daughter));
        words.add(new word("Older Brother", "Bara Bhai",R.drawable.family_older_brother));
        words.add(new word("Younger Brother", "Chota Bhai",R.drawable.family_younger_brother));
        words.add(new word("Older Sister", "Bari Bhen",R.drawable.family_older_sister));
        words.add(new word("Younger Sister", "Choti Bhen",R.drawable.family_younger_sister));
        words.add(new word("Grandmother ", "Dadi",R.drawable.family_grandmother));
        words.add(new word("Grandfather", "Dada",R.drawable.family_grandmother));


        WordAdapter Adapter =
                new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word w = words.get(position);

                           }
        });
    }}

