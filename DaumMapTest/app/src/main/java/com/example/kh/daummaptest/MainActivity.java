package com.example.kh.daummaptest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;


import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //지도 띄우기
        MapView mapView = new MapView(this);
        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.5396224, 129.31152759999998),true);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading); // 현위치 표시
        mapView.setShowCurrentLocationMarker(true);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //마커
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker!!!");
        marker.setTag(0);
        //marker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.5396224, 129.31152759999998));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양

        mapView.addPOIItem(marker);
    }

    //메뉴바
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //메뉴버튼 클릭시 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.setting){
            Toast.makeText(this, "설정화면으로 이동", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Setting.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);

        }
}