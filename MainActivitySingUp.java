package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivitySingUp extends AppCompatActivity {

    EditText emailSignUp;
    EditText passwordSingUp;
    Button createAccount;

    public static ArrayList<User> user_list = new ArrayList<User>();

    // For multiuser function to work hashmap would have to be implemented to control the users and their data
    // Therefore this section is not fully functional but opens up into its own view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sing_up);

        emailSignUp = findViewById(R.id.emailSingUp);
        passwordSingUp = findViewById(R.id.passwordSignUp);
        createAccount = findViewById(R.id.createAccountButton);


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = emailSignUp.getText().toString();
                String newPassword = passwordSingUp.getText().toString();
                for(int i = 0; i < user_list.size(); i++) {
                    User u = user_list.get(i);
                    if(!newEmail.equals(u.getName()) && !newPassword.equals(u.getPassword())) {
                        user_list.add(new User(newPassword, newEmail));
                        System.out.println(newEmail + newPassword);
                        Toast.makeText(MainActivitySingUp.this, "Account creation successful", Toast.LENGTH_SHORT).show();
                        openMainActivityLoggedIn();
                    }
                    else {
                        Toast.makeText(MainActivitySingUp.this, "Account already exists", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public void openMainActivityLoggedIn() {
        Intent intent = new Intent(this, MainActivity_LoggedIn.class);
        startActivity(intent);
    }

    public static ArrayList<String> returnUserList() {
        ArrayList<String> ul = new ArrayList<>();
        for (int i = 0; i < user_list.size(); i++) {
            User u = user_list.get(i);
            String s = (u.getName() + "/" + u.getPassword());
            ul.add(s);
        }
        return ul;
    }

}
