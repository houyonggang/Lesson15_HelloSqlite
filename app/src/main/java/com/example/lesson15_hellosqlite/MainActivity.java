package com.example.lesson15_hellosqlite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lesson15_hellosqlite.db.DbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    /**
     * VIEW
     */
    @BindView(R.id.TextView01)
    TextView TextView01;
    @BindView(R.id.Button01)
    Button Button01;
    @BindView(R.id.Button02)
    Button Button02;
    @BindView(R.id.Button03)
    Button Button03;
    @BindView(R.id.Button04)
    Button Button04;
    @BindView(R.id.Spinner01)
    Spinner Spinner01;
    @BindView(R.id.TextView02)
    TextView TextView02;

    /**
     * DATA
     */
    private SQLiteDatabase db;//SQLiteDatabase对象
    private String db_name = "gallery.sqlite";//数据库名
    private String table_name = "pic";//表名
    @SuppressLint("StaticFieldLeak")
    public static MainActivity instance;
    final DbHelper helper = new DbHelper(this, db_name, null, 1);//辅助类名
    private ContentValues contentValues;//ContentValues对象
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = MainActivity.this;
        ButterKnife.bind(this);
        mContext = MainActivity.this;
        initData();
    }

    private void initData() {
        Toast.makeText(mContext,"热修复测试",Toast.LENGTH_SHORT).show();
        //从辅助类获得数据库对象
        db = helper.getWritableDatabase();
        //初始化数据
        initDatabase(db);
        //更新下拉列表中的数据
        updateSpinner();
    }

    /*
     * 初始化表
     *
     * @param db
     */
    private void initDatabase(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fileName", "pic1.jpg");
        contentValues.put("description", "图片1");
        db.insert(table_name, "", contentValues);

        contentValues.put("fileName", "pic2.jpg");
        contentValues.put("description", "图片2");
        db.insert(table_name, "", contentValues);

        contentValues.put("fileName", "pic3.jpg");
        contentValues.put("description", "图片3");
        db.insert(table_name, "", contentValues);

        contentValues.put("fileName", "pic4.jpg");
        contentValues.put("description", "图片4");
        db.insert(table_name, "", contentValues);
    }

    @OnClick({R.id.Button01, R.id.Button02, R.id.Button03, R.id.Button04})
    public void onClick(View view) {
        contentValues = new ContentValues();
        switch (view.getId()) {
            case R.id.Button01:
                contentValues.put("fileName", "pic5.jpg");
                contentValues.put("description", "图片5");
                //添加方法
                long long1 = db.insert("pic", "", contentValues);
                //添加成功后返回行号，失败后返回-1
                if (long1 == -1) {
                    Toast.makeText(mContext, "ID是" + long1 + "的图片添加失败！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "ID是" + long1 + "的图片添加成功！", Toast.LENGTH_SHORT).show();
                }
                updateSpinner();
                break;
            case R.id.Button02:
                //删除方法
                long long2 = db.delete("pic", "description='图片5'", null);
                //删除失败返回0，成功则返回删除的条数
                Toast.makeText(mContext, "删除了" + long2 + "条记录", Toast.LENGTH_SHORT).show();
                //更新下拉列表
                updateSpinner();
                break;
            case R.id.Button03:
                contentValues.put("fileName", "pic0.jpg");
                contentValues.put("description", "图片0");
                int long3 = db.update("pic", contentValues, "fileName='pic5.jpg'", null);
                //删除失败返回0，成功则返回删除的条数
                Toast.makeText(mContext, "更新了" + long3 + "条记录", Toast.LENGTH_SHORT).show();
                //更新下拉列表
                updateSpinner();
                break;
            case R.id.Button04:
                Cursor cursor = db.query("pic", null, null, null, null, null, null);
                //cursor.getCount()是记录条数
                Toast.makeText(mContext, "当前共有" + cursor.getCount() + "条记录，下面一一显示：", Toast.LENGTH_SHORT).show();
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    Toast.makeText(mContext, "第" + cursor.getInt(0) + "条数据，文件名是" + cursor.getString(1) + "，描述是" + cursor.getString(2), Toast.LENGTH_SHORT).show();
                }
                updateSpinner();
                break;
        }
    }

    //更新下拉列表
    private void updateSpinner() {
        //从数据库中获取数据放入游标Cursor对象
        final Cursor cursor = db.query("pic", null, null, null, null, null, null);
        //创建简单游标匹配器
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, new String[]{
                "fileName", "description",}, new int[]{android.R.id.text1, android.R.id.text2});
        simpleCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //给下拉列表设置适配器
        Spinner01.setAdapter(simpleCursorAdapter);
        //定义子元素选择监听器
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                TextView02.setText("当前pic的描述为：" + cursor.getString(2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        //给下拉列表绑定子元素选择监听器
        Spinner01.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.delete(table_name, null, null);
        updateSpinner();
    }
}