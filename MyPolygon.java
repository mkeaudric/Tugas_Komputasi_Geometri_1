import java.util.ArrayList;
import java.util.List;

public class MyPolygon {
    /**  */
    List<MyPoint> Points;

    /**
     * konstruktor
     * @return
     */
    public MyPolygon() {
        this.Points = new ArrayList<MyPoint>();
    }

    /**
     * [5a,6a,7a] masukin titik
     * @param p
     * @return
     */
    boolean addPoint(MyPoint p) {
        Points.add(p);
        return true;
    }

    /**
     * [5b] konveks ato bukan
     * @return
     */
    boolean isConvex() {
        int i, size = Points.size(); // biar size dihitung sekali aja
        for(i = 0; i < size; i++){ 
            int idx1 = i; // gaperlu mod krn mentok sampe size-1
            int idx2 = (i+1)%size; // biar balik ke i=0 kalau mentok, pake mod
            int idx3 = (i+2)%size; // sama kek idx2
            // inikan terurut CCW, jadi semuanya harus belok kiri (cross > 0)
            // begitu 1 belok kanan (cross < 0), lgs return false
            if(CG.cross(Points.get(idx1), Points.get(idx2), Points.get(idx3)) < 0) 
                return false;
        }
        return true;
    }

    /**
     * [6b] luas poligon ini
     * @return
     */
    double area() {
		// pake yg rumus terakhir di ppt
        double A2 = 0.0;
        int i, size = Points.size(); // biar size dihitung sekali aja
        // kalo pake rumus terakhir di ppt, segmen terakhir juga harus dihitung
        // misal segitiga abc, maka loop nya cek untuk ab, bc, ca
        for(i = 0; i < size; i++){ 
            int idx1 = i%size; // index yang dimod
            int idx2 = (i+1)%size; // sama kek idx1

            // 2 point segmen (inikan loop per segmen poligon)
            MyPoint p1 = Points.get(idx1);
            MyPoint p2 = Points.get(idx2);

            // sumber : dari ppt, rumus terakhir
            A2 += (p1.x + p2.x)*(p2.y - p1.y); 
        }
        return A2/2; // area = 2*area / 2
    }

    /**
     * [7b] titik p di dalem ato luar,
     * jika berimpitan dengan titik atau segmen, di dalam 
     * @param p
     * @return
     */
    boolean isPointInside(MyPoint p) {
		int i, size = Points.size(); // biar size dihitung sekali aja
        for(i = 0; i < size; i++){ 
            int idx1 = i; // gaperlu mod krn mentok sampe size-1
            int idx2 = (i+1)%size; // biar balik ke i=0 kalau mentok, pake mod
            // terurut CCW, jadi kalau p di dalem poligon semuanya harus belok kiri (cross > 0)
            // begitu 1 belok kanan (cross < 0) berarti point diluar, lgs return false
            if(CG.cross(Points.get(idx1), Points.get(idx2), p) < 0) 
                return false;
        }
        return true;
    }

}
