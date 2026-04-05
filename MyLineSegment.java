public class MyLineSegment {
    /** ``vektor'' dari start ke end */
    MyPoint start;
    /** komen di sini */
    MyPoint end;

    /**
     * konstruktor
     * @param start
     * @param end
     */
    MyLineSegment(MyPoint start, MyPoint end) {
        this.start = start;
        this.end = end;
    }

    /**
     * [2a] jarak ke titik
     * @param p
     * @return
     */
    double distanceToPoint(MyPoint p) {
        // misal start = a, end = b
		// ap . ab < 0 -> d(a, p)
        // bp . ba < 0 -> d(b, p)
        // else d = (pq x pa) / |pq|

        // kalau di ppt, dibalik tandanya (karena ap pb, bukan ap ab)
        // ap . pb > 0 -> d(p, b)
        // bp . pa > 0 -? d(p, a)
        
        // tapi saya pake yang di atas (ap ab), kebanyakan yg saya lihat di internet pakai itu
        // sumber : https://www.youtube.com/watch?v=Zt_xVbqx8b0
        
        MyPoint a = this.start;
        MyPoint b = this.end;
        double dist;
        if(CG.dot(a, p, b) < 0){
            dist = p.distanceToOtherPoints(a);
        } else if(CG.dot(b, p, a) < 0){
            dist = p.distanceToOtherPoints(b);
        } else{
            dist = Math.abs(CG.cross(a, p, b)) / a.distanceToOtherPoints(b);
        }

        return dist;
    }

    /**
     * [3] dari start ke end ke target belok kiri, kanan, atao lurus
     * @param target
     * @return
     */
    double leftTurnToPoint(MyPoint target) {
		double res = 0.0;
		res = CG.ccw(this.start, this.end, target);
        return res; //return 0 jika lurus, plus/minus jika belok kanan/kiri,  
    }
    
    /**
     * [4] motong segmen laen?
     * @param other
     * @return
     */
    boolean isIntersect(MyLineSegment other) {
        // pake 2 cross di tiap segmen
        // segmen this pake a, b
        // segmen other pake p, q
		
        MyPoint a = this.start;
        MyPoint b = this.end;
        MyPoint p = other.start;
        MyPoint q = other.end;

        // pertama cek apakah cross apb dan aqb berbeda (bisa cross apb <= 0 dan cross aqb >= 0, atau sebaliknya)
        // disebut di soal ujung di garis dianggap berpotongan, jadi pake <= 0 dan >= 0
        if((CG.cross(a, p, b) <= 0 && CG.cross(a, q, b) >= 0) || (CG.cross(a, p, b) >= 0 && CG.cross(a, q, b) <= 0))
            // kalau masuk kondisi benar, cek lagi dari segmen paq dan pbq dengan cara yg sama
            if((CG.cross(p, a, q) <= 0 && CG.cross(p, b, q) >= 0) || (CG.cross(p, a, q) >= 0 && CG.cross(p, b, q) <= 0))
                // kalau udah cek kedua segmen dan kondisi terpenuhi, berarti berpotongan
                return true;

        return false; // ga berpotongan
    }

}