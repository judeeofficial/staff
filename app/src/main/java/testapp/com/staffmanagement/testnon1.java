package testapp.com.staffmanagement;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class testnon1 extends Activity {

    private static final String TITLE = "Devahoy News";
    private static final String MESSAGE = "สวัสดีครับ ยินดีต้อนรับเข้าสู่บทความ Android Notification :)";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non);

    }

    public void showNotification(View view) {

//        Intent intent = new Intent(Intent.ACTION_VIEW,
//                Uri.parse("http://devahoy.com/2015/08/android-notification/"));
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent intent = new Intent(this, testnon2.class);
        intent.putExtra("message", MESSAGE);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(testnon2.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(TITLE)
                        .setContentText(MESSAGE)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);

    }
}
