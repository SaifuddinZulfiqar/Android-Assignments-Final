package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        final ArrayList<word> words = new ArrayList<word>();

        words.add(new word("One", "Aik",R.drawable.number_one));
        words.add(new word("Two", "Doo",R.drawable.number_two));
        words.add(new word("Three", "Teen",R.drawable.number_three));
        words.add(new word("Four", "Chaar",R.drawable.number_four));
        words.add(new word("Five", "Panch",R.drawable.number_five));
        words.add(new word("Six", "Chay",R.drawable.number_six));
        words.add(new word("Seven", "Sath",R.drawable.number_seven));
        words.add(new word("Eight", "Aanth",R.drawable.number_eight));
        words.add(new word("Nine", "No’e",R.drawable.number_nine));
        words.add(new word("Ten", "Das",R.drawable.number_ten));


        WordAdapter Adapter =
                new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word w = words.get(position);

            }
        });








    }}

