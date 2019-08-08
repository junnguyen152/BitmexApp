package skysoft.com.bitmexapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import skysoft.com.bitmexapp.R;


public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.login);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        final EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
        final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        Button btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        Button btnDangkyDialog = (Button) findViewById(R.id.btnDangKyDialog);

        btnDangkyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Login.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_dangky, null);
                final EditText mEmail = (EditText) mView.findViewById(R.id.mEmail);
                final EditText mPassword = (EditText) mView.findViewById(R.id.mPassword);
                Button mDangKy = (Button) mView.findViewById(R.id.btnDangKy);

                mDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = mEmail.getText().toString();
                        String password = mPassword.getText().toString();
                        if(!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
                            Toast.makeText(Login.this, "DangKyThanhCong", Toast.LENGTH_SHORT).show();
                            /*firebaseAuth = FirebaseAuth.getInstance();
                            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Login.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Login.this, "Dang ky khong thanh cong", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });*/


                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtUsername.getText().toString().isEmpty() && !edtPassword.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity(){
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }
}
