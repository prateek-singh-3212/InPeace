package com.example.inpeace.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inpeace.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class PhoneAuth extends AppCompatActivity {

    private Button phoneauthPhoneSendOtpBtn ;
    private LinearLayout phoneauthPhoneCL;
    private LinearLayout phoneauthOTPCL;
    private Button phoneauthOTPVerifyBtn ;
    private EditText phoneauthPhonePhone;
    private EditText phoneauthOTPOTP ;
    private TextView phoneauthPhonePhoneError;
    private TextView phoneauthTVOTPError;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private DatabaseReference mdatabase;
    private FirebaseAuth mauth;
    private String mVerificationId ;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String userId = "";
    private String phoneNumberFinal = "";
    private ProgressDialog loading;
    private CountryCodePicker phoneauthPhoneCcp ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        // Linking to XML
        phoneauthOTPVerifyBtn = findViewById(R.id.phoneauthOTPVerifyBtn);
        phoneauthPhoneSendOtpBtn = findViewById(R.id.phoneauthPhoneSendOtpBtn);
        phoneauthPhoneCL = findViewById(R.id.phoneauthPhoneCL);
        phoneauthOTPCL= findViewById(R.id.phoneauthOTPCL);
        //phoneauthPhonePhoneError =findViewById(R.id.phoneauthPhonePhoneError);
        phoneauthPhonePhone = findViewById(R.id.phoneauthPhonePhone);
        phoneauthOTPOTP = findViewById(R.id.phoneauthOTPOTP);
        phoneauthPhoneCcp = (CountryCodePicker) findViewById(R.id.phoneauthPhoneCcp);

        //regestering phone number
        phoneauthPhoneCcp.registerCarrierNumberEditText(phoneauthPhonePhone);

        // creating the database instance
        mdatabase = FirebaseDatabase.getInstance().getReference();

//        //getting userid
//        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //initalizing the mauth
        mauth =FirebaseAuth.getInstance();

        //Inializing loding bar
        loading = new ProgressDialog(this);






        // Send Otp Button
        phoneauthPhoneSendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setTitle("Verifying Number");
                loading.setMessage("Please sit and relax till we verify number for you");
                loading.setCanceledOnTouchOutside(false);
                loading.show();

                //Phone Number final
                phoneNumberFinal = phoneauthPhoneCcp.getFullNumberWithPlus();

                if(!phoneauthPhonePhone.equals("")) {
                    // Sending Phone Number for Verification
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mauth)
                            .setPhoneNumber(phoneNumberFinal)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(PhoneAuth.this)
                            .setCallbacks(mCallbacks)
                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }else {
                    Toast.makeText(PhoneAuth.this , "Please enter the phone number" , Toast.LENGTH_LONG).show();
                    loading.dismiss();
                }

            }
        });





        // Verify Button
        phoneauthOTPVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setTitle("Verifying code");
                loading.setMessage("Please sit and relax till we verify otp for you");
                loading.setCanceledOnTouchOutside(false);
                loading.show();

                if(!phoneauthOTPOTP.equals("")) {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, phoneauthOTPOTP.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }else {
                    Toast.makeText(PhoneAuth.this , "Please enter the otp number" , Toast.LENGTH_LONG).show();
                    loading.dismiss();
                }
            }
        });



        // Gernating mCallbacks
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                phoneauthPhoneCL.setVisibility(View.VISIBLE);
                phoneauthOTPCL.setVisibility(View.GONE);
                loading.dismiss();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                Toast.makeText(PhoneAuth.this , "Code has been send" , Toast.LENGTH_LONG).show();

                // making visible and invisibe
                phoneauthPhoneCL.setVisibility(View.GONE);
                phoneauthOTPCL.setVisibility(View.VISIBLE);
                loading.dismiss();

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }


        };
    }

    // Sign in with phone auth credintial
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                Toast.makeText(PhoneAuth.this , "Verification Complete" ,Toast.LENGTH_LONG).show();
                                loading.dismiss();
                                sendUserToNextActivity();
                        } else {
                            phoneauthOTPOTP.setText("");
                            loading.dismiss();
                            Toast.makeText(PhoneAuth.this , "Wrong code entered" , Toast.LENGTH_LONG);

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }

    //send user to signup
    private void sendUserToNextActivity() {
        Intent intent = new Intent(PhoneAuth.this , signup.class);
        intent.putExtra("Phone Number" ,phoneNumberFinal);
        startActivity(intent);
    }
}