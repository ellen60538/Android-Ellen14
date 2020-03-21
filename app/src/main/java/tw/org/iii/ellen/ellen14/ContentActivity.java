package tw.org.iii.ellen.ellen14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

public class ContentActivity extends AppCompatActivity {
    private ImageView img ;
    private TextView content ;
    private String strPic, strContent ;
    private Bitmap bmp ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        img = findViewById(R.id.content_img) ;
        content = findViewById(R.id.content_content) ;

        strPic = getIntent().getStringExtra("pic") ;
        strContent = getIntent().getStringExtra("content") ;

        content.setText(strContent) ;
        fetchImage() ;
    }

    private void fetchImage(){

        new Thread(){
            @Override
            public void run() {
                try{

//                    URL url = new URL(strPic) ;
                    URL url = new URL("https://d17fnq9dkz9hgj.cloudfront.net/breed-uploads/2018/09/dog-landing-hero-lg.jpg?bust=1536935129&width=1080") ;

                    Log.v("ellen",strPic) ;

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection() ;
                    conn.connect() ;

                    bmp = BitmapFactory.decodeStream(conn.getInputStream()) ;
                    uiHandler.sendEmptyMessage(0);
                    Log.v("ellen","debug1") ;

                }catch (Exception e){
                    Log.v("ellen",e.toString()) ;
                }
            }
        }.start();
    }

    UIHandler uiHandler = new UIHandler() ;
    private class UIHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            img.setImageBitmap(bmp) ;
        }
    }
}
