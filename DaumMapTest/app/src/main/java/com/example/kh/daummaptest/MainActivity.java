package com.example.kh.daummaptest;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class MainActivity extends Activity implements MapView.MapViewEventListener {

    private static final String LOG_TAG = "EventsDemoActivity";
    private MapView mapView;
    private TextView mTapTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 맵뷰
        mapView = (MapView)findViewById(R.id.daumMapView);
        mapView.setMapViewEventListener(this);

        mTapTextView = (TextView) findViewById(R.id.mTapTextView);

    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        mapView.setShowCurrentLocationMarker(true);
        //mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);
        Log.i(LOG_TAG, "onMapViewInitialized");
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
/*        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        MapPoint.PlainCoordinate mapPointScreenLocation = mapPoint.getMapPointScreenLocation();
        mTapTextView.setText("long pressed, point=" + String.format("lat/lng: (%f,%f) x/y: (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude, mapPointScreenLocation.x, mapPointScreenLocation.y));
        Log.i(LOG_TAG, String.format(String.format("MapView onMapViewLongPressed (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude)));*/

        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();    // GPS로 표현
        MapPoint.PlainCoordinate mapPointScreenLocation = mapPoint.getMapPointScreenLocation(); // X, Y축으로 표현

        mapView.removeAllPOIItems();
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker!!!");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양

        mapView.addPOIItem(marker);

        mTapTextView.setText(String.format(String.valueOf(mapPointGeo.latitude), mapPointGeo.longitude));
    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

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

    // 현위치 클릭
    public void btnMyLocationOnClick(View view) {
        mapView.setShowCurrentLocationMarker(true);
    }
}