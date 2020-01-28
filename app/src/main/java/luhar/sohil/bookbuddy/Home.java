package luhar.sohil.bookbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import luhar.sohil.bookbuddy.Common.Common;

public class Home extends AppCompatActivity {
    TextView wlcmUser;
    Button btnSearch,btnSell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        wlcmUser=(TextView)findViewById(R.id.wlcmUser);
        btnSearch=(Button)findViewById(R.id.searchBook);
        btnSell=(Button)findViewById(R.id.sellBook);

        String msg="Hello ! "+Common.currentuser.getName();
        wlcmUser.setText(msg);
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "You pressed Sell button", Toast.LENGTH_SHORT).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "You pressed search button", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
