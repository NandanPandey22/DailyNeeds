package com.example.dailyneeds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


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

    private ImageButton closeBtn;

    private Button signInBtn;

    private FirebaseAuth firebaseauth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);

        donthaveanAccount=view.findViewById(R.id.sign_up_btn);
        parentframeLayout=getActivity().findViewById(R.id.register_framelayout);

        email=view.findViewById(R.id.sign_in_email);
        password=view.findViewById(R.id.sign_in_password);

        closeBtn=view.findViewById(R.id.sign_in_close_btn);
        signInBtn=view.findViewById(R.id.sign_in_btn);
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

    }
    private void setFragment(Fragment  fragment)
    {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentframeLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}
