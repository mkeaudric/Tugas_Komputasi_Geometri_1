import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class MyPointSet {
    /**  */
    List<MyPoint> Points;

    /**
     * konstruktor
     * @return
     */
    public MyPointSet() {
        this.Points = new ArrayList<MyPoint>();
    }

    /**
     * [8a] masukin titik
     * @param p
     * @return
     */
    boolean addPoint(MyPoint p) {
        return Points.add(p);
    }

    /** [8b] Mengurutkan titik dengan menyapu secara melingkar berlawanan arah jarum jam (ccw) berdasarkan sudut kutub (titik acuan/referensi paling kiri dan terbawah) (Graham Scan Sort) */
    public void sortPoints() {
        if (Points.size() < 2) return;

        // 1. Cari titik referensi (P): Y paling kecil, lalu X paling kecil
        MyPoint p0 = Points.get(0);
        for (MyPoint p : Points) {
            if (p.y < p0.y || (p.y == p0.y && p.x < p0.x)) {
                p0 = p;
            }
        }
        
        final MyPoint ref = p0; // Titik referensi tetap untuk comparator

        // 2. Gunakan Collections.sort dengan Comparator custom
        Collections.sort(Points, new Comparator<MyPoint>() {
            @Override
            public int compare(MyPoint b, MyPoint c) {
                if (b == ref) return -1;
                if (c == ref) return 1;

                // Gunakan CCW untuk melihat arah dari P -> b -> c
                // Jika belok kiri, berarti b memiliki sudut lebih kecil dari c
                double res = CG.ccw(ref, b, c);

                if (res > 0) return -1; // b di kiri c, b lebih dulu
                if (res < 0) return 1;  // b di kanan c, c lebih dulu
                
                // Jika kolinear, yang lebih dekat ke referensi didahulukan
                double distB = ref.distanceToOtherPoints(b);
                double distC = ref.distanceToOtherPoints(c);
                return Double.compare(distB, distC);
            }
        });
    }
}