package dongting.bwei.com.weektwoa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.remember)
    CheckBox remember;
    @BindView(R.id.autoLogin)
    CheckBox autoLogin;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.contract)
    TextView contract;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sp = getSharedPreferences("config", MODE_PRIVATE);

        editor = sp.edit();

        if(sp.getBoolean("autoLogin",false)==true){

            goActivity();
        }

        if(sp.getBoolean("remember",false)==true && !TextUtils.isEmpty(account.getText().toString().trim())){
            password.setText(sp.getString("password",""));
        }

        String str = "我已阅读并同意服务条款";
        SpannableString spannableString =new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED),7,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        contract.setText(spannableString);
    }

    @OnClick({R.id.remember, R.id.autoLogin,R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.remember:

                if (!TextUtils.isEmpty(account.getText().toString().trim())
                        && !TextUtils.isEmpty(password.getText().toString().trim())) {

                    editor.putBoolean("remember", true).commit();

                    editor.putString("password", password.getText().toString().trim()).commit();
                }

                break;
            case R.id.autoLogin:

                if (!TextUtils.isEmpty(account.getText().toString().trim())
                        && !TextUtils.isEmpty(password.getText().toString().trim())) {

                    editor.putString("username1",account.getText().toString().trim()).commit();
                    editor.putString("password1",password.getText().toString().trim()).commit();

                    editor.putBoolean("autoLogin", true).commit();
                }
                break;

            case R.id.login:
                if (TextUtils.isEmpty(account.getText().toString().trim())
                        || TextUtils.isEmpty(password.getText().toString().trim())) {

                    Toast.makeText(this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("username1",account.getText().toString().trim()).commit();
                    editor.putString("password1",password.getText().toString().trim()).commit();

                    goActivity();
                }

                break;
        }
    }

    private void goActivity() {
        Intent intent =new Intent(this,SecondActivity.class);
        intent.putExtra("username",sp.getString("username1",""));
        intent.putExtra("password",sp.getString("password1",""));

        intent.putExtra("rem",sp.getBoolean("remember",false));

        startActivity(intent);
        finish();
    }
}
