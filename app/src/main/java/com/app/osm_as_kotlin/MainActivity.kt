package com.app.osm_as_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Configuration.getInstance().load(applicationContext, getSharedPreferences("osmdroid", MODE_PRIVATE))

        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)

        // стартовая точка
        val mapController: IMapController = mapView.controller
        mapController.setZoom(17.0)
        mapController.setCenter(GeoPoint(60.0071, 30.3720))

        mapView.setMultiTouchControls(true)

        // ограничение области
        val northBoundary = 60.01520
        val southBoundary = 59.99299
        val eastBoundary = 30.39141
        val westBoundary = 30.35188
        val boundingBox = BoundingBox(northBoundary, eastBoundary, southBoundary, westBoundary)
        // mapView.setScrollableAreaLimit(boundingBox)
        mapView.setScrollableAreaLimitDouble(boundingBox)
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
