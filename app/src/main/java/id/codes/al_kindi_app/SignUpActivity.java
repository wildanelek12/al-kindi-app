package id.codes.al_kindi_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.et_email_input)
    EditText et_email_input;
    @BindView(R.id.et_password_input)
    EditText et_password_input;
    @BindView(R.id.et_confirm_password_input)
    EditText et_confirm_password_input;
    @BindView(R.id.btn_signup)
    ConstraintLayout btn_signup;
    private FirebaseAuth mAuth;

    boolean isError1 = true;
    boolean isError2 = true;
    boolean isError3 = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        btn_signup.setEnabled(false);

        et_email_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(et_email_input.getText().toString());
                if (!matcher.matches()){
                    et_email_input.setError("Isi alamat email dengan benar");
                    isError1 = true;
                }else if (et_email_input.getText().toString().equals("")){
                    et_email_input.setError("Kosong");
                    isError1 = true;
                } else{
                    isError1 = false;

                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                validateField();
            }
        });
        et_password_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (et_password_input.getText().toString().equals("")){
                    et_password_input.setError("Kosong");
                    isError2 = true;
                }else if(et_password_input.getText().length() < 6){
                    et_password_input.setError("Password Minimal 6 Karakter");
                    isError2 = true;
                } else {
                    isError2 = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateField();
            }
        });
        et_confirm_password_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_confirm_password_input.getText().toString().equals("")){
                    et_confirm_password_input.setError("Kosong");
                    isError3 = true;
                } else if (!et_confirm_password_input.getText().toString().equals(et_password_input.getText().toString())){
                    et_confirm_password_input.setError("Pasword tidak sama");
                    isError3 = true;
                }else {
                    isError3 = false;
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
                validateField();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
                mAuth.createUserWithEmailAndPassword(et_email_input.getText().toString(), et_password_input.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pDialog.dismissWithAnimation();
                            new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Yeay")
                                    .setContentText("Pendaftaran Berhasil !")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();

                        }else{
                            new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Yah")
                                    .setContentText("Pendaftaran gagal, silahkan cek koneksi anda !")
                                    .show();
                            Toast.makeText(SignUpActivity.this, "SignUP Gagal "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    void validateField(){
        if (isError1||isError2||isError3){
            btn_signup.setEnabled(false);
        }else{
            btn_signup.setEnabled(true);
        }

    }
}