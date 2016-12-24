package com.example.peach.bmobtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {
    private TextView tv_que;
    private TextView tv_cre;
    private TextView tv_upd;
    private TextView tv_del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一：默认初始化
        Bmob.initialize(this, "c5c162d264e8c847800f96eed89e5bc5");
        setContentView(R.layout.activity_main);
        tv_que = (TextView) findViewById(R.id.tv_que);
        tv_cre = (TextView) findViewById(R.id.tv_cre);
        tv_upd = (TextView) findViewById(R.id.tv_upd);
        tv_del = (TextView) findViewById(R.id.tv_del);
        tv_cre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p2 = new Person();
                p2.setName("lucky");
                p2.setAddress("北京海淀");
                p2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId,BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        tv_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查找Person表里面id为16663383d0的数据
                BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
                bmobQuery.getObject("16663383d0", new QueryListener<Person>() {
                    @Override
                    public void done(Person person, BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"查询失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        tv_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
                final Person p2 = new Person();
                p2.setAddress("北京朝阳");
                p2.update("16663383d0", new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,"更新成功:"+p2.getUpdatedAt(),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"更新失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Person p2 = new Person();
                p2.setObjectId("16663383d0");
                p2.delete(new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,"删除成功:"+p2.getUpdatedAt(),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"删除失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
    }
}
