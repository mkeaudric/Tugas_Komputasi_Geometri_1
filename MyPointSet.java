import java.util.ArrayList;
import java.util.List;

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
        // saya pikir coba pake insertion sort + binary search
        // masukin dulu sambil urutin, kalau udah rada banyak nyari posisi masukinnya pake binary search
        // sumber : https://www.geeksforgeeks.org/dsa/binary-insertion-sort/

        // kalo arraylist kosong, lgs add terus beres
        if(Points.isEmpty()) {
            Points.add(p);
            return true;
        }

        // nyari titik kiri terbawah buat jadi pivot
        // int i, size = Points.size();
        // double ymin = Integer.MAX_VALUE;
        // MyPoint curPoint, pivot;
        // for(i = 0; i < size; i++){
        //     curPoint = Points.get(i);
        //     if(curPoint.y < ymin){
        //         ymin = curPoint.y;
        //         pivot = curPoint;
        //     } else if(curPoint.y == ymin){ // kalo misal ada 2 titik yg y nya sama" di bawah, ambil yg terkiri (x nya lebih kecil)
        //         if(curPoint.x < pivot.x) pivot = curPoint; // kalo titik saat ini lebih di kiri, update pivot
        //     }
        // }

        // kalo titik di add, bisa jadi pivot baru atau bukan
        // 1. kalo dia jadi pivot baru
        if(p.y < pivot.y){
            
        }

        // 2. kalo dia bukan pivot baru, masukin pake binary search

        int loc, low = 0, high = Points.size();
        while(low <= high){
            int mid = low + (high - low)/2;
            if(p.)
        }

        this.Points.add(p);

        return true;
    }
}