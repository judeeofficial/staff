package testapp.com.staffmanagement;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import mssd.staff.management.UserHelper;

/**
 * Created by pingpongofficial on 15/6/2559.
 */
public class outputActivity extends Activity {

    ListView request;
    UserHelper user;
    ArrayList<HashMap<String, String>> MyArrList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        request = (ListView)findViewById(R.id.lvlrequest);
        user = new UserHelper();
        getdataqueue();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    private void getdataqueue(){
        String url = "http://10.10.92.91/mobile_staff/customer_request_staff.ashx";

        try {
            JSONArray data = new JSONArray(user.getHttpGet(url));
            MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("work_id", c.getString("work_id"));
                map.put("work_desc", c.getString("work_desc"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(outputActivity.this, MyArrList, R.layout.queue_work_activity,
                    new String[] {"work_id", "work_desc"}, new int[] {R.id.Colwork_id, R.id.Colwork_desc});
            request.setAdapter(sAdap);
            request.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String dis_name = MyArrList.get(position).get("work_id").toString();
                   // String tel = MyArrList.get(position).get("tel").toString();
                   // Toast.makeText(districtactivity.this,dis_name,Toast.LENGTH_SHORT).show();
               //     Toast.makeText(districtactivity.this, tel ,Toast.LENGTH_SHORT).show();
/*

        viewDetail.setCancelable(true);
        viewDetail.setMessage("เขต : " + dis_name + "\n"
                + "เบอร์โทร : " + tel);
        viewDetail.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
        viewDetail.show();
*/
                }
            });
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
