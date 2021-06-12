
package com.maksymb.geteat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import models.User;



public class login extends AppCompatActivity {


    Button btnSignIn, btnReg;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference User;


    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = findViewById(R.id.btn_sign);
        btnReg = findViewById(R.id.btn_reg);

        root = findViewById(R.id.root_element);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        User = db.getReference("user");

        btnReg.setOnClickListener((v) -> showRegisterWindow());
        btnSignIn.setOnClickListener(v -> showSignInWindow());

    }

     private void showSignInWindow(){
         AlertDialog.Builder dialog = new AlertDialog.Builder(this);
         dialog.setTitle("Sign in");


         LayoutInflater inflater = LayoutInflater.from(this);
         View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
         dialog.setView(sign_in_window);

         final MaterialEditText email = sign_in_window.findViewById(R.id.emailField);
         final MaterialEditText pass = sign_in_window.findViewById(R.id.passField);


         dialog.setNegativeButton("cancel", (dialogInterface, which) -> dialogInterface.dismiss());

         dialog.setPositiveButton("Apply", (dialogInterface, which) -> {
             if (TextUtils.isEmpty(email.getText().toString())) {
                 Snackbar.make(root, "Write your email.", Snackbar.LENGTH_LONG).show();
                 return;
             }


             if (pass.getText().toString().length() < 5) {
                 Snackbar.make(root, "Write password more than 5 symbols", Snackbar.LENGTH_LONG);
                 return;

             }

             auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
             .addOnSuccessListener(authResult -> {
                 startActivity(new Intent(login.this, MainActivity.class));
                finish();
             }).addOnFailureListener(e -> Snackbar.make(root, "Error" + e.getMessage(), Snackbar.LENGTH_SHORT).show());


         });
         dialog.show();

    }










        private void showRegisterWindow() {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Sign up");

            LayoutInflater inflater = LayoutInflater.from(this);
            View regWindow = inflater.inflate(R.layout.register_window, null);
            dialog.setView(regWindow);

            final MaterialEditText email = regWindow.findViewById(R.id.emailField);
            final MaterialEditText pass = regWindow.findViewById(R.id.passField);
            final MaterialEditText name = regWindow.findViewById(R.id.nameField);
            final MaterialEditText phone = regWindow.findViewById(R.id.phoneField);

            dialog.setNegativeButton("cancel", (dialogInterface, which) -> dialogInterface.dismiss());

            dialog.setPositiveButton("Apply", (dialogInterface, which) -> {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(root, "Write your email.", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(name.getText().toString())) {
                    Snackbar.make(root, "Write your name.", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(phone.getText().toString())) {
                    Snackbar.make(root, "Write your phone.", Snackbar.LENGTH_LONG).show();
                    return;

                }
                if (pass.getText().toString().length() < 5) {
                    Snackbar.make(root, "Write password more than 5 symbols", Snackbar.LENGTH_LONG);
                    return;

                }
                //Registration
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(authResult -> {
                            User user = new User();
                            user.setEmail(email.getText().toString());
                            user.setEmail(name.getText().toString());
                            user.setEmail(pass.getText().toString());
                            user.setEmail(phone.getText().toString());


                            User.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnSuccessListener(aVoid -> Snackbar.make(root, "User add", Snackbar.LENGTH_LONG).show());

                        });


            });
            dialog.show();
        }
    }









