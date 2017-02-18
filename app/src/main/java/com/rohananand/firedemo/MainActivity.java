package com.rohananand.firedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView wordList;
    EditText wordName;
    EditText wordDef;

    Button btnAddWord;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordList    = (TextView)findViewById(R.id.logs);
        wordName    = (EditText)findViewById(R.id.wordName);
        wordDef     = (EditText)findViewById(R.id.wordDef);

        btnAddWord  = (Button)findViewById(R.id.btnAddWord);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        wordList.setText("");

        DatabaseReference wordlistRef = mDatabase.child("wordlist").getRef();

        wordlistRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Word word = dataSnapshot.getValue(Word.class);

                wordList.append(word.word + "\n" + word.definition + "\n\n");
                Log.d("Words", "onChildAdded: " + dataSnapshot.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewWord(wordName.getText().toString(), wordDef.getText().toString());
                Toast.makeText(getApplicationContext(), "New Word added to Database", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeNewWord(String word, String wordDef) {
        Word newWord = new Word(word, wordDef);

        String wordId = mDatabase.child("wordlist").push().getKey();
        mDatabase.child("wordlist").child(wordId).setValue(newWord);
    }
}
