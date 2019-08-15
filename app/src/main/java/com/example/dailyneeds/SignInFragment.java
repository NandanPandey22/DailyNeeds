package com.example.dailyneeds;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }

    private TextView donthaveanAccount;
    private FrameLayout parentframeLayout;

    private EditText email;
    private EditText password;

    private TextView forgotPassword;

    private ImageButton closeBtn;
    private ProgressBar progressbar;

    private Button signInBtn;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    private FirebaseAuth firebaseauth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);

        donthaveanAccount=view.findViewById(R.id.sign_up_btn1);
        parentframeLayout=getActivity().findViewById(R.id.register_framelayout);

        forgotPassword=view.findViewById(R.id.sign_in_forgot_password);

        email=view.findViewById(R.id.sign_in_email);
        password=view.findViewById(R.id.sign_in_password);

        closeBtn=view.findViewById(R.id.sign_in_close_btn);
        signInBtn=view.findViewById(R.id.sign_in_btn1);

        progressbar=view.findViewById(R.id.progressBar4);

        firebaseauth=FirebaseAuth.getInstance();

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        donthaveanAccount.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v)
        {
            setFragment(new SignUpFragment());
        }
    });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ResetPasswordFragment());
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                mainIntent();
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v)
                {
                    checkEmailAndPassword();
                }
        });
    }
    private void checkInputs()
    {
        if(!TextUtils.isEmpty(email.getText()))
        {
            if(!TextUtils.isEmpty(password.getText()))
            {
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255,255,255));

            }
            else
            {
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.argb(50,255,255,255));
            }

        }else
        {
            signInBtn.setEnabled(false);
            signInBtn.setTextColor(Color.argb(50,255,255,255));
        }

    }
    private void setFragment(Fragment  fragment)
    {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentframeLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkEmailAndPassword()
    {
        if(email.getText().toString().matches(emailPattern))
        {
            if(password.length()>=8)
            {
                progressbar.setVisibility(View.VISIBLE);
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.argb(50,255,255,255));

                firebaseauth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful())
                                {
                                   mainIntent();
                                }
                                else
                                {
                                    progressbar.setVisibility(View.INVISIBLE);
                                    signInBtn.setEnabled(true);
                                    signInBtn.setTextColor(Color.rgb(255,255,255));
                                    String error=task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
            else
            {
                Toast.makeText(getActivity(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();
        }

    }
    private void mainIntent()
    {
        Intent mainIntent=new Intent(getActivity(),MainActivity2.class);
        startActivity(mainIntent);
        getActivity().finish();
    }

}
