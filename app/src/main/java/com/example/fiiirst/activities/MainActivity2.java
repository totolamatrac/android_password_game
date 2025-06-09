package com.example.fiiirst.activities;

import static android.view.View.VISIBLE;

import android.os.Bundle;

import com.example.fiiirst.fragments.MainFragment;
import com.example.fiiirst.R;
import com.example.fiiirst.models.Rule;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private EditText input = null;
    private LinearLayout containerFragment;

    private static List<Rule> rules = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        containerFragment = findViewById(R.id.fragment_container);


        rules.add(new Rule("Rule 1", "Your password mus be at least 5 characters."));
        rules.add(new Rule("Rule 2", "Your password must include a number."));
        rules.add(new Rule("Rule 3", "Your password must include an uppercase letter."));

        for (Rule r : rules) {
            addFragment(r.getTitle(), r.getDescription());
        }
        input = (EditText) findViewById(R.id.input);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView counter = findViewById(R.id.char_counter);
                counter.setText(String.valueOf(s.length()));
                for (int i = 0; i < rules.size(); i++) {
                    switch (i) {
                        case 0 : {
                            MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(rules.get(i).getTitle());
                            if (fragment != null && fragment.getView() != null) {
                                fragment.getView().setVisibility(VISIBLE);
                                if (s.length() >= 5) {
                                    fragment.setValid(true);
                                    break;
                                } else if (fragment.getValid().get()) {
                                    fragment.setValid(false);
                                }
                                return;
                            }
                        }
                        case 1 : {
                            MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(rules.get(i).getTitle());
                            if (fragment != null && fragment.getView() != null) {
                                fragment.getView().setVisibility(VISIBLE);
                                boolean containDigit = s.chars().anyMatch(Character::isDigit);
                                if (containDigit) {
                                    fragment.setValid(true);
                                    break;
                                } else if (fragment.getValid().get()) {
                                    fragment.setValid(false);
                                }
                                return;
                            }
                        }
                        case 2: {
                            MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(rules.get(i).getTitle());
                            if (fragment != null && fragment.getView() != null) {
                                fragment.getView().setVisibility(VISIBLE);
                                boolean containAnUppercase = s.chars().anyMatch(Character::isUpperCase);
                                if (containAnUppercase) {
                                    fragment.setValid(true);
                                    break;
                                } else if (fragment.getValid().get()) {
                                    fragment.setValid(false);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    private void addFragment(String title, String description) {
        Log.d("DEBUG", "addFragment: " + title);
        MainFragment fragment = MainFragment.newInstance(title, description);
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerFragment.getId(), fragment, title)
                .commit();
    }
}