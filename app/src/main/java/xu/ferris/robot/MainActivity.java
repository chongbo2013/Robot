package xu.ferris.robot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_input;
    Button btn_enter;
    TextView tv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RobotUtils.readAssetsFiles(MainActivity.this);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                    }
                });

            }
        }).start();


        edt_input=findViewById(R.id.edt_input);
        btn_enter=findViewById(R.id.btn_enter);
        tv_msg=findViewById(R.id.tv_msg);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_msg.setText(RobotUtils.getAnswer(edt_input.getText().toString()));
            }
        });
    }
}
