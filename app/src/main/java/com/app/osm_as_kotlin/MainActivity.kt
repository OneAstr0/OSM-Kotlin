package com.app.osm_as_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivity : AppCompatActivity() {
    private lateinit var currentco: TextView
    private lateinit var mapView: MapView
    private lateinit var currentCameraPosition: GeoPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Configuration.getInstance().load(applicationContext, getSharedPreferences("osmdroid", MODE_PRIVATE))

        currentco = findViewById(R.id.realtime_coordinates)
        // Инициализация карты
        mapView = findViewById(R.id.mapView)
        mapView.setBuiltInZoomControls(false) // Отключил кнопки масштабирования

        // Стартовая точка
        val mapController: IMapController = mapView.controller
        mapController.setZoom(17.0)
        var startpoint: GeoPoint = GeoPoint(60.0071, 30.3720)
        mapController.setCenter(startpoint)

        mapView.setMultiTouchControls(true)

        // Ограничение области
        val northBoundary = 60.01520
        val southBoundary = 59.99299
        val eastBoundary = 30.39141
        val westBoundary = 30.35188
        val boundingBox = BoundingBox(northBoundary, eastBoundary, southBoundary, westBoundary)

        // Установка ограничения области
        mapView.setScrollableAreaLimitDouble(boundingBox)

        // Получение и сохранение текущей координаты камеры
        /*currentCameraPosition = mapView.x as GeoPoint
        currentco.setText(currentCameraPosition.toString())*/
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
