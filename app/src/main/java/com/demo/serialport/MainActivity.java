package com.demo.serialport;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author renquan
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mMessage;
    private Button mOpen;
    private Button mSend;
    private Button mClose;
    private SerialPortUtils serialPortUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        //串口数据监听事件
        serialPortUtils.setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                Log.d("TAG", "进入数据监听事件中。。。" + new String(buffer));

            }
        });

    }

    private void init() {
        initView();
        serialPortUtils = new SerialPortUtils();
    }

    private void initView() {
        mMessage = (EditText) findViewById(R.id.message);
        mOpen = (Button) findViewById(R.id.open);
        mOpen.setOnClickListener(this);
        mSend = (Button) findViewById(R.id.send);
        mSend.setOnClickListener(this);
        mClose = (Button) findViewById(R.id.close);
        mClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open:
                // TODO 20/12/28
                serialPortUtils.openSerialPort("/dev/ttyS9",9600);
                break;
            case R.id.send:
                // TODO 20/12/28
                serialPortUtils.sendSerialPort(mSend.getText().toString());
                break;
            case R.id.close:
                serialPortUtils.closeSerialPort();
                // TODO 20/12/28
                break;
            default:
                break;
        }
    }
}