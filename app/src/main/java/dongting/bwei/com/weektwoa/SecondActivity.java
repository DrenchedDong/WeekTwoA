package dongting.bwei.com.weektwoa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:${董婷}
 * 日期:2017/6/17
 * 描述:
 */

public class SecondActivity extends Activity {

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.check)
    Button check;
    private boolean b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        
        Intent intent = getIntent();

        username.setText("您的用户名:" + intent.getStringExtra("username"));
        password.setText("您的密码:" + intent.getStringExtra("password"));

        b = intent.getBooleanExtra("rem", false);
        
    }

    @OnClick(R.id.check)
    public void onClick() {
        if (b) {
            Toast toast = Toast.makeText(this, "您选择的是记住密码", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL , 0, 0);  //设置显示位置
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.BLACK);
            v.setTextSize(15);
            toast.show();


        }else{

            Toast toast = Toast.makeText(this, "您没有选择记住密码", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL , 0, 0);  //设置显示位置
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.BLACK);
            v.setTextSize(15);
            toast.show();
        }
    }
}
