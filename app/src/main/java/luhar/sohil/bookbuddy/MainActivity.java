package luhar.sohil.bookbuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import luhar.sohil.bookbuddy.Common.Common;

public class MainActivity extends AppCompatActivity {
    EditText uPhone,uPwd;
    Button btnLogin;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView register = (TextView)findViewById(R.id.lnkRegister);
       uPhone=(EditText)findViewById(R.id.txtPhone);
       uPwd=(EditText)findViewById(R.id.txtPwd);

       btnLogin=(Button)findViewById(R.id.btnLogin);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("User");


        btnLogin.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                    check();

            }
        });

        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    private void check() {

      final String usrPhone=uPhone.getText().toString();
       final String usrpwd=uPwd.getText().toString();
        if(usrpwd.isEmpty()||usrPhone.isEmpty()){
            Toast.makeText(MainActivity.this, "Please fill all details ", Toast.LENGTH_SHORT).show();
        }
        else{

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child(usrPhone).exists()){
                        User user=dataSnapshot.child(usrPhone).getValue(User.class);
                        if(user.getPass().equals(usrpwd)){

                            Common.currentuser=user;
                            Intent  intent=new Intent(MainActivity.this,Home.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "User Not Exists", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }
}
