package com.example.AndroidProject.fragments;

import android.app.Activity;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment
{
    public LoginFragment()
    {
        // Required empty public constructor
    }

    public interface OnRegisterListener
    {
        void onRegister(String userName, String password, String phone);
    }

    private FirebaseAuth mAuth;


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText emailEditText = view.findViewById(R.id.editTxtEmail);
        final EditText passwordEditText = view.findViewById(R.id.editTxtPassword);



        Button buttonLogToReg = view.findViewById(R.id.btnReg);
        buttonLogToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        Button buttonLogToZoo = view.findViewById(R.id.btnLogin);
        Animation scaleAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink);
        buttonLogToZoo.startAnimation(scaleAnimation);

        buttonLogToZoo.setOnClickListener(new View.OnClickListener() {
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

                Activity currentActivity = getActivity();
                if (currentActivity != null) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(currentActivity, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(currentActivity, R.string.login_successful, Toast.LENGTH_LONG).show();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Email", email);
                                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_zooFragment, bundle);
                                    } else {
                                        Toast.makeText(currentActivity, R.string.login_failed, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        return view;
    }
}