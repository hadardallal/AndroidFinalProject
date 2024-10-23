package com.example.AndroidProject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.AndroidProject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText emailEditText = view.findViewById(R.id.editTxtEmailReg);
        final EditText passwordEditText = view.findViewById(R.id.editTxtPass);



        Button buttonRegToLog = view.findViewById(R.id.btnRegister);
        Animation scaleAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink);
        buttonRegToLog.startAnimation(scaleAnimation);


        buttonRegToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.error_missing_email_password, Toast.LENGTH_LONG).show();
                    return;
                }

                if (email.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.error_missing_email, Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.error_missing_password, Toast.LENGTH_LONG).show();
                    return;
                }
                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), getString(R.string.registration_successful), Toast.LENGTH_LONG).show();
                                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getActivity(), getString(R.string.error_email_exists), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getActivity(), getString(R.string.registration_failed) + " " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        return view;
    }
}
