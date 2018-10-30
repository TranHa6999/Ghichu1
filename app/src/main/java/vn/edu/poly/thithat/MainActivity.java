package vn.edu.poly.thithat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.thithat.adapter.ModelAdapter;
import vn.edu.poly.thithat.database.DatabaseHelper;
import vn.edu.poly.thithat.model.Model;

public class MainActivity extends AppCompatActivity {
private DatabaseHelper databaseHelper;
private List<Model> list=new ArrayList<>();
private RecyclerView recyclerView;
private ModelAdapter adapter;
        private EditText edtID;
        private EditText edtNAME;
        private EditText edtPRICE;
        private Button btnThem,btnSua;
        private int i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        databaseHelper=new DatabaseHelper(this);
        recyclerView=findViewById(R.id.recycler);
        list=databaseHelper.getFoodAll();
        adapter=new ModelAdapter(list,databaseHelper,this);
        final RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list=databaseHelper.getFoodAll();
                String a = null;
                String b = "daco";
                String id = edtID.getText().toString().trim();
                for (int i = 0; i < list.size(); i++) {

                    Model food1 = list.get(i);
                    if (id.equals(food1.getId())) {
                        a = "daco";
                    } else {
                        a = "chuaco";
                    }


                }
                if (edtID.getText().toString().equals("")||edtNAME.getText().toString().equals("")||edtPRICE.getText().toString().equals("")){
                    if (edtID.getText().toString().equals("")){
                        edtID.setError("ID không được để trống");
                    }
                    if (edtNAME.getText().toString().equals("")){
                        edtNAME.setError("Nội dung không được để trống");
                    }
                    if (edtPRICE.getText().toString().equals("")){
                        edtPRICE.setError("Price không được để trống");
                    }
                }else {
                    if (edtID.getText().toString().length() > 5 ||
                            edtNAME.getText().toString().length() > 100 ||
                            edtPRICE.getText().toString().length() > 15) {
                        if (edtID.getText().toString().length() > 5) {
                            edtID.setError("ID không được nhập quá 5 số");
                        }
                        if (edtNAME.getText().toString().length() > 100) {
                            edtNAME.setError("Nội dung không được nhập quá 100 kí tự");
                        }
                        if (edtPRICE.getText().toString().length() > 15) {
                            edtPRICE.setError("Price không được nhập quá 15 số");
                        }
                    } else {
                        if (b.equals(a)) {
                            Toast.makeText(getApplicationContext(), "Đã có", Toast.LENGTH_SHORT).show();
                        } else {
                            Model food=new Model();
                            food.setId(edtID.getText().toString());
                            food.setName(edtNAME.getText().toString());
                            food.setPrice(Long.parseLong(edtPRICE.getText().toString()));
                            databaseHelper.insertFood(food);
                            list=databaseHelper.getFoodAll();
                            adapter=new ModelAdapter(list,databaseHelper,getApplicationContext());
                            final RecyclerView.LayoutManager manager=new LinearLayoutManager(getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(manager);
                        }
                    }


                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtID.getText().toString().equals("")||edtNAME.getText().toString().equals("")||edtPRICE.getText().toString().equals("")){
                    if (edtID.getText().toString().equals("")){
                        edtID.setError("ID không được để trống");
                    }
                    if (edtNAME.getText().toString().equals("")){
                        edtNAME.setError("Food Name không được để trống");
                    }
                    if (edtPRICE.getText().toString().equals("")){
                        edtPRICE.setError("Price không được để trống");
                    }
                }else {
                    if (edtID.getText().toString().length() > 5 ||
                            edtNAME.getText().toString().length() > 100 ||
                            edtPRICE.getText().toString().length() > 15) {
                        if (edtID.getText().toString().length() > 5) {
                            edtID.setError("ID không được nhập quá 5 số");
                        }
                        if (edtNAME.getText().toString().length() > 100) {
                            edtNAME.setError("Nội dung được nhập quá 100 kí tự");
                        }
                        if (edtPRICE.getText().toString().length() > 15) {
                            edtPRICE.setError("Price không được nhập quá 15 số");
                        }
                    } else {
                            Model food=new Model();
                            food.setName(edtNAME.getText().toString());
                            food.setPrice(Long.parseLong(edtPRICE.getText().toString()));
                            databaseHelper.updateFood(edtID.getText().toString(),food);
                        list=databaseHelper.getFoodAll();
                        adapter=new ModelAdapter(list,databaseHelper,getApplicationContext());
                        final RecyclerView.LayoutManager manager=new LinearLayoutManager(getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(manager);
                    }


                }
            }
        });


    }
    private void initView() {
        edtID=findViewById(R.id.edtid);
        edtNAME=findViewById(R.id.edtname);
        edtPRICE=findViewById(R.id.edtprice);
        btnThem=findViewById(R.id.btnThem);
        btnSua=findViewById(R.id.btnSua);
    }

}
