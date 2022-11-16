package com.farhad.visitingcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void onCallClick(View view) {

        String phone = "+8801844991983";
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.fromParts("tel", phone, null));
        startActivity(intent);

    }


    public void onMailClick(View view) {
        String[] addresses = {"imfarhad01@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Business Deal");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onWebClick(View view) {

        Uri uri = Uri.parse("https://www.mdfarhad.com"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void onLocationClick(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=23.694925,90.4129&daddr=23.8777249,90.2658408"));
        startActivity(intent);
    }

    public void onWhatsAppClick(View view) {
        String contact = "+88 01844991983"; // use country code with your phone number
        String url = "https://api.whatsapp.com/send?phone=" + contact;
        try {
            PackageManager pm = getApplication().getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(MainActivity.this, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void onLinkedInClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@" + "estoyfarhad"));
        final PackageManager packageManager = getApplicationContext().getPackageManager();
        final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.isEmpty()) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + "estoyfarhad"));
        }
        startActivity(intent);
    }

    public void onGithubClick(View view) {
        Uri uri = Uri.parse("https://github.com/farhadhossen"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onFacebookClick(View view) {
        Intent facebookIntent = getOpenFacebookIntent(MainActivity.this);
        startActivity(facebookIntent);
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/estoyfarhad")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/estoyfarhad")); //catches and opens a url to the desired page
        }
    }
}

