package testapp.com.staffmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
/**
 * Created by pingpongofficial on 11/8/2559.
 */
public class testnon2 extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        Bundle bundle = getIntent().getExtras();

        String message = bundle.getString("message");

        TextView textView = (TextView) findViewById(R.id.txt_message);
        textView.setText(message);
    }
}
