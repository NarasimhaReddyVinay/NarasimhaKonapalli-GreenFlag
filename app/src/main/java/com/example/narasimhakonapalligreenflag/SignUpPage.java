package com.example.narasimhakonapalligreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUpPage extends AppCompatActivity {

    EditText email,password,repeatPassword;
    TextView confirmInput;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        ImageView Back;
        Back = findViewById(R.id.back);
        Back.setOnClickListener(view -> startActivity(new Intent(SignUpPage.this, MainActivity.class)));
        confirmInput = findViewById(R.id.CreateAnAccount);
        confirmInput.setOnClickListener(this::onClick);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        repeatPassword=findViewById(R.id.editTextTextPassword2);
        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
        repeatPassword.addTextChangedListener(loginTextWatcher);

    }





    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address", Drawable.createFromPath("@drawable/cross.png"));
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    public boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    private boolean reValidatePassword() {
        String passwordInput = password.getText().toString().trim();
        String repeatPasswordInput = repeatPassword.getText().toString().trim();

        if (repeatPasswordInput.isEmpty()) {
            repeatPassword.setError("Field can't be empty");
            return false;
        } else if (!repeatPasswordInput.equals(passwordInput)) {
            repeatPassword.setError("Password doesn't matches");
            return false;
        } else {
            repeatPassword.setError(null);
            return true;
        }

    }





    private final TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            confirmInput.setEnabled(validateEmail() && validatePassword() && reValidatePassword());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        
    };

    private void onClick(View view) {
        if (!validateEmail()|!validatePassword()|!reValidatePassword()) {
            return;
        }

        String input = "Email: " + email.getText().toString();
        input += "\n";

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}