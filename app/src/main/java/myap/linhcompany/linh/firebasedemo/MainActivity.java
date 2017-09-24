package myap.linhcompany.linh.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
        //trường hợp 1
        mData=FirebaseDatabase.getInstance().getReference();
        mData.child("ho ten").setValue("Nguyen van linh");
        //trường hợp 2
        Sinhvien sinhvien=new Sinhvien("nguyenvanlinh",1993,"Nam Định");
        mData.child("Sinhvien").setValue(sinhvien);
        //Trường hơp 3
        Map<String,Integer> myMap=new HashMap<String,Integer>();
        myMap.put("Xe đạp",2);
        mData.child("phương tiện").setValue(myMap);
        //trường hợp 4
         Sinhvien sv=new Sinhvien("tuantuan",21,"Hà nội");
         mData.child("Học viên").push().setValue(sv);//push để dữ liệu thay đổi theo ng dùng

        mData.child("thuộc tính").setValue("đang mỏ", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null){
                    Toast.makeText(MainActivity.this, "lưu trữ thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Lưu chữ thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
