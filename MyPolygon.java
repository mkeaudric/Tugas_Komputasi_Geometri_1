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
    // boolean isPointInside(MyPoint p) {
	// 	int i, size = Points.size(); // biar size dihitung sekali aja
    //     for(i = 0; i < size; i++){ 
    //         int idx1 = i; // gaperlu mod krn mentok sampe size-1
    //         int idx2 = (i+1)%size; // biar balik ke i=0 kalau mentok, pake mod
    //         // terurut CCW, jadi kalau p di dalem poligon semuanya harus belok kiri (cross > 0)
    //         // begitu 1 belok kanan (cross < 0) berarti point diluar, lgs return false
    //         if(CG.cross(Points.get(idx1), Points.get(idx2), p) < 0) 
    //             return false;
    //     }
    //     return true;
    // }
    // baru inget simple polygon ternyata bs konkav, gbs pake ini

    // pake nya yang ganjil genap
    boolean isPointInside(MyPoint p){
        // tarik garis dr p ke kanan, kalo interseksi ganjil -> dalem, genap -> luar
        // bikin suatu titik (y nya samain sama p.y) yang lebih kanan dari titik terkanan
        MyPoint q = new MyPoint(Integer.MAX_VALUE, p.y);
        
        // degenerate case
        // kalo pq nyentuh titik -> /= 2 (itungnya jadi 1 interseksi)
        // kalo pq nyentuh garis misal ab pada kedua titik a dan b (segmen dan segaris) -> diitung di dalem (dari soal)

        int i, size = Points.size();
        MyLineSegment pq = new MyLineSegment(p, q);
        int ct = 0;

        // cek tiap segmen poligon
        for(i = 0; i < size; i++){
            MyPoint a = Points.get(i);
            MyPoint b = Points.get((i + 1)%size);

            MyLineSegment ab = new MyLineSegment(a, b);

            if(pq.isIntersect(ab));
        }

        return ct%2 != 0; // ganjil -> true, genap -> false
    }

    // gaboleh ngitung sudut jd gbs pake Winding Number biasa
    // Tapi baru liat di Wikipedia ada juga algo Dan Sunday Winding Number, ternyata gaperlu ngitung sudut segala bs pake cross aja
    // "An improved algorithm to calculate the winding number was developed by Dan Sunday in 2001.[6] 
    // It does not use angles in calculations, nor any trigonometry, and functions exactly the same as the 
    // ray casting algorithms described above"

    // tapi disini saya cuma implementasi yg ray casting
}
