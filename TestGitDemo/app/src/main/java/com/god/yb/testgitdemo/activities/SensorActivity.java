package com.god.yb.testgitdemo.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.god.yb.testgitdemo.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    @BindView(R.id.tv_data1)
    TextView tvData1;
    @BindView(R.id.tv_data2)
    TextView tvData2;
    @BindView(R.id.tv_data3)
    TextView tvData3;
    @BindView(R.id.tv_data4)
    TextView tvData4;
    @BindView(R.id.tv_data5)
    TextView tvData5;
    @BindView(R.id.tv_data6)
    TextView tvData6;
    @BindView(R.id.tv_data7)
    TextView tvData7;
    @BindView(R.id.tv_data8)
    TextView tvData8;
    @BindView(R.id.tv_data9)
    TextView tvData9;
    @BindView(R.id.tv_data10)
    TextView tvData10;
    @BindView(R.id.data11)
    TextView data11;
    private SensorManager mSensorManager;
    private float a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ButterKnife.bind(this);

        // 获取传感器管理对象
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //true 说明这个timer以daemon方式运行（优先级低，程序结束timer也自动结束）
        Timer timer = new Timer(true);

        TimerTask task = new TimerTask() {
            public void run() {
                //每次需要执行的代码放到这里面。
//                float s = 4.9 * 0.5;
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 为加速度传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        // 为方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
        // 为陀螺仪传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_UI);
        // 为磁场传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
        // 为重力传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_UI);
        // 为线性加速度传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_UI);
        // 为温度传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_UI);
        // 为光传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_UI);
        // 为压力传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 取消监听
        mSensorManager.unregisterListener(this);
    }

    // 当传感器的值改变的时候回调该方法
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        // 获取传感器类型
        int type = event.sensor.getType();
        StringBuilder sb;
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER:
                sb = new StringBuilder();
                sb.append("加速度传感器返回数据：");
                sb.append("\nX方向的加速度：");
                sb.append(values[0]);
                sb.append("\nY方向的加速度：");
                sb.append(values[1]);
                sb.append("\nZ方向的加速度：");
                sb.append(values[2]);
                tvData1.setText(sb.toString());
                break;
            case Sensor.TYPE_ORIENTATION:
                sb = new StringBuilder();
                sb.append("\n方向传感器返回数据：");
                sb.append("\n绕Z轴转过的角度：");
                sb.append(values[0]);
                sb.append("\n绕X轴转过的角度：");
                sb.append(values[1]);
                sb.append("\n绕Y轴转过的角度：");
                sb.append(values[2]);
                tvData2.setText(sb.toString());
                break;
            case Sensor.TYPE_GYROSCOPE:
                sb = new StringBuilder();
                sb.append("\n陀螺仪传感器返回数据：");
                sb.append("\n绕X轴旋转的角速度：");
                sb.append(values[0]);
                sb.append("\n绕Y轴旋转的角速度：");
                sb.append(values[1]);
                sb.append("\n绕Z轴旋转的角速度：");
                sb.append(values[2]);
                tvData3.setText(sb.toString());
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sb = new StringBuilder();
                sb.append("\n磁场传感器返回数据：");
                sb.append("\nX轴方向上的磁场强度：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的磁场强度：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的磁场强度：");
                sb.append(values[2]);
                tvData4.setText(sb.toString());
                break;
            case Sensor.TYPE_GRAVITY:
                sb = new StringBuilder();
                sb.append("\n重力传感器返回数据：");
                sb.append("\nX轴方向上的重力：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的重力：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的重力：");
                sb.append(values[2]);
                tvData5.setText(sb.toString());
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                sb = new StringBuilder();
                sb.append("\n线性加速度传感器返回数据：");
                sb.append("\nX轴方向上的线性加速度：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的线性加速度：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的线性加速度：");
                sb.append(values[2]);
                tvData6.setText(sb.toString());

                a = values[2];
                if (a>8) {
                    String aa = String.valueOf(a).substring(0,4);
                    String s = String.valueOf(data11.getText());
                    data11.setText(s+"\nZ轴方向上的线性加速度"+aa);
                }
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("\n温度传感器返回数据：");
                sb.append("\n当前温度为：");
                sb.append(values[0]);
                tvData7.setText(sb.toString());
                break;
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("\n光传感器返回数据：");
                sb.append("\n当前光的强度为：");
                sb.append(values[0]);
                tvData8.setText(sb.toString());
                break;
            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("\n压力传感器返回数据：");
                sb.append("\n当前压力为：");
                sb.append(values[0]);
                tvData9.setText(sb.toString());
                break;
            case Sensor.TYPE_STEP_COUNTER:
                sb = new StringBuilder();
                sb.append("\n计步传感器传感器返回数据：");
                sb.append("\n当前步数为：");
                sb.append(values[0]);
                tvData10.setText(sb.toString());
                break;
        }
    }

    // 当传感器精度发生改变时回调该方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
