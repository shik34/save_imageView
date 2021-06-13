package com.example.save_imageview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    Button btn;
    TextView tv;
    String folderToSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.myIV);

        tv=(TextView) findViewById(R.id.textView);
        folderToSave = Environment.getExternalStorageDirectory().toString();

        folderToSave+="/cats";
        File dir = new File(folderToSave);
        dir.mkdir();
        tv.setText(folderToSave);

        btn=(Button)findViewById(R.id.myB);
        btn.setOnClickListener(new View.OnClickListener(){
                                   public void onClick(View v){
                                       tv.setText("ok");
                                       SavePicture(iv,folderToSave);
                                   }
                               });
    }




    public String SavePicture(ImageView iv, String folderToSave)
    {
        OutputStream fOut = null;
//        Date currentTime = (Date) Calendar.getInstance().getTime();
/*        LocalTime now = LocalTime.now();
        LocalDate nowDate = LocalDate.now();*/

        try {
            File file = new File(folderToSave,
/*                    Integer.toString(nowDate.getYear()) +
                            Integer.toString(nowDate.getMonthValue()) +
                            Integer.toString(nowDate.getDayOfMonth()) +
                            Integer.toString(now.getHour()) +
                            Integer.toString(now.getMinute()) +
                            Integer.toString(now.getSecond()) */
/*                    Integer.toString(nowDate.getYear()) +
                            Integer.toString(nowDate.getMonthValue()) +
                            Integer.toString(nowDate.getDayOfMonth()) +
                            Integer.toString(now.getHour()) +*/
//                            Integer.toString(currentTime.getMinutes()) +
//                            Integer.toString(now.getSecond())+
                            "1.jpg");
            // создать уникальное имя для файла основываясь на дате сохранения

            fOut = new FileOutputStream(file);

            Bitmap bitmap =((BitmapDrawable) iv.getDrawable()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // сохранять картинку в jpeg-формате с 85% сжатия.
            fOut.flush();
            fOut.close();
//            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(),  file.getName()); // регистрация в фотоальбоме
       }
        catch (Exception e) // здесь необходим блок отслеживания реальных ошибок и исключений, общий Exception приведен в качестве примера
        {
            tv.setText(e.getMessage().toString());
            return e.getMessage();
        }
        return "";
    }

}