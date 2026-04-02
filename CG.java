public class CG {
    
    /**
     * Memeriksa apakah dari p ke q ke r berlawanan arah jarum jam atau tidak
     * @return >0 jika berlawanan arah jarum jam (belok kiri), 
	 * <0 jika searah jarum jam (belok kanan), dan 0 jika lurus (kolinear)
     */
    public static double ccw(MyPoint p, MyPoint q, MyPoint r) {
        MyPoint pq = new MyPoint((q.x-p.x),(q.y-p.y));
        MyPoint pr = new MyPoint((r.x-p.x),(r.y-p.y));
		double res = ((pq.x*pr.y)-(pq.y*pr.x));
		if (Math.abs(res)<=0.00000001) res = 0;
		//else res = res/Math.abs(res);
        return res;
    }

    /**
     * jika perlu
     * @return
     */
    public static double dot(MyPoint p, MyPoint q, MyPoint r) {
        // dot = pqx prx + pqy pry
        //     = (q.x - p.x)(r.x - p.x) + (q.y - p.y)(r.y - p.y)
        // NOTE : dot gbs pake pq qr, harus pq pr (sumber : nanya Gemini)
        double res = (q.x - p.x)*(r.x - p.x) + (q.y - p.y)*(r.y - p.y);
        return Math.abs(res) <= 0.00000001 ? 0 : res;
    }

    /**
     * jika perlu
     * kalo 2d, hasilnya vektor di 3d, yg dipake hasil komponen z nya aja
     * return type nya juga saya ganti jadi double
     * @return
     */
    public static double cross(MyPoint p, MyPoint q, MyPoint r) {
        // cross = pqx qry - pqy qrx 
        //       = (q.x - p.x)(r.y-q.y) - (q.y - p.y)(r.x - q.x)
        // NOTE : cross pq qr atau cross pq pr sama hasilnya, saya pake yg pq qr (sumber : nyoba sendiri di bawah)
        // double res = (q.x - p.x)*(r.y - p.y) - (q.y - p.y)*(r.x - p.x);
        double res = (q.x - p.x)*(r.y - q.y) - (q.y - p.y)*(r.x - q.x);
        return Math.abs(res) <= 0.00000001 ? 0 : res;
    }

    // debug
    // public static void main(String[] args) {
    //     MyPoint p = new MyPoint(0, 0);
    //     MyPoint q = new MyPoint(1, 0);
    //     MyPoint r = new MyPoint(1, 1);
    //     // pqr harusnya left turn

    //     System.out.println(cross(p, q, r)); // 1.0 > 0 -> belok kiri
    // }
}
