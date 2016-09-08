package testapp.com.staffmanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mssd.staff.management.UserHelper;

public class MainActivity extends Activity {
EditText Username,Password;
    Button Login;
    UserHelper userhttp;
    AlertDialog.Builder ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Username = (EditText)findViewById(R.id.txtUsername);
        Password = (EditText)findViewById(R.id.txtPassword);
        Login = (Button)findViewById(R.id.btnLogin);
userhttp = new UserHelper();

        ad = new AlertDialog.Builder(this);
        Login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                loginstaff();
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     }

    private void loginstaff(){
        String url = "http://10.10.92.91/mobile_staff/loginstaff.ashx";

        String user = Username.getText().toString();
        String pass = Password.getText().toString();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("staffname", user));
        params.add(new BasicNameValuePair("password", pass));
        String resultServer  =  userhttp.getHttpPost(url,params);
        String strStatusID = "0";
        String strstaffid = "0";
        String strError = "Unknow Status!";
        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strstaffid = c.getString("staffid");
            strError = c.getString("status");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(strStatusID.equals("0"))
        {
            ad.setTitle("Error! ");
            ad.setIcon(android.R.drawable.btn_star_big_on);
            ad.setPositiveButton("Close", null);
            ad.setMessage(strError);
            ad.show();
            Username.setText("");
           Password.setText("");
        }else{
            Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
            Intent newActivity = new Intent(MainActivity.this,indexactivity.class);
            newActivity.putExtra("staffid",   strstaffid);
            startActivity(newActivity);
        }
    }
}
