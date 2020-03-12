package be.ehb.lichtshow.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import be.ehb.lichtshow.R;
import be.ehb.lichtshow.model.LightFestival;
import be.ehb.lichtshow.model.LightFestivalDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrusselFragment extends Fragment {

    //fields
    private MapView mapView;
    private GoogleMap myMap;
    //listeners
    private OnMapReadyCallback onMapReady = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            //field maken om de googlemap instantie in andere methoden te krijgen
            myMap = googleMap;
            //kaart klaar
            //kaart centreren op een coördinaat.
            LatLng coördBrussel = new LatLng(50.858712,4.347446);

            CameraUpdate moveToBrussel = CameraUpdateFactory.newLatLngZoom(coördBrussel, 16);

            googleMap.animateCamera(moveToBrussel);
            //googleMap.setMapType(googleMap.MAP_TYPE_SATELLITE);

            myMap.setOnInfoWindowClickListener(infoWindowClickListener);
            setMarkerAdapter();
            drawMarkers();

        }
    };
    private GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            LightFestival c = (LightFestival) marker.getTag();
            if (c!= null)
                Toast.makeText(getActivity(),c.getInfo(),Toast.LENGTH_SHORT).show();

        }
    };


    private void setMarkerAdapter(){
        myMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View cardView = getActivity().getLayoutInflater().inflate(R.layout.marker_card, null, false);
                TextView tvTitle = cardView.findViewById(R.id.tv_card_title);
                TextView tvSnippet = cardView.findViewById(R.id.tv_snippet);

                tvTitle.setText(marker.getTitle());
                tvSnippet.setText(marker.getSnippet());

                return cardView;
            }
        });
    }

    private void drawMarkers(){
        myMap.addMarker( new MarkerOptions()
                .position( new LatLng(50.858712,4.347446))
                .title("Kaaitheater")
                .snippet("Er komt een boot met vuurwerk langs")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        myMap.addMarker( new MarkerOptions()
                .position( new LatLng(50.846777, 4.352360))
                .title("Grote markt")
                .snippet("Het gebouw belicht om de geschiedenis van Brussel voor te stellens")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        myMap.addMarker( new MarkerOptions()
                .position( new LatLng(50.860215, 4.350880))
                .title("Maximiliaan")
                .snippet("Interactieve projectie")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        myMap.addMarker( new MarkerOptions()
                .position( new LatLng(50.863994, 4.349828))
                .title("Magasin 4")
                .snippet("Lasershow met muziek")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        for (LightFestival lightFestival: LightFestivalDAO.INSTANCE.getLightFestivals()){
            Marker m = myMap.addMarker(new MarkerOptions()
                    .position( lightFestival.getCoordinate()));

            m.setTitle( lightFestival.getName());
            m.setSnippet( lightFestival.getInfo());
            m.setTag(lightFestival);
        }
    }




    public BrusselFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_brussel, container, false);
        mapView = rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(onMapReady);


        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
