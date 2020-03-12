package be.ehb.lichtshow.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class LightFestivalDAO {

    private ArrayList<LightFestival> lightFestivals;
    public static final LightFestivalDAO INSTANCE = new LightFestivalDAO();

    private LightFestivalDAO(){
    }

    public ArrayList<LightFestival> getLightFestivals(){
        if (lightFestivals == null){
            lightFestivals = new ArrayList<>();
            lightFestivals.add(new LightFestival(new LatLng(50.858712, 4.347446),"KaaiTheater","Er komt een boot met vuurwerk langs"));
            lightFestivals.add(new LightFestival(new LatLng(50.860215, 4.350880),"Maximiliaan","Interactieve projectie"));
            lightFestivals.add(new LightFestival(new LatLng(50.863994, 4.349828),"Magasin 4","Lasershow met muziek"));
            lightFestivals.add(new LightFestival(new LatLng(50.846777, 4.352360),"Grote markt","Het gebouw belicht om de geschiedenis van Brussel voor te stellen"));
        }
        return lightFestivals;
    }
}
