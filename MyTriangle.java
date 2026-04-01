
public class MyTriangle {
    /**  */
    MyPoint a,b,c;

    /**
     * konstruktor
     * @return
     */
    public MyTriangle(MyPoint a, MyPoint b, MyPoint c) {
        this.a = a;
		this.b = b;
		this.c = c;
    }

    /**
     * 
     * @param 
     * @return
     */
    double area() {
		double A2 = CG.cross(this.a, this.b, this.c); // area parallelogram 
        return A2/2; // area segitiga abc = 2*area / 2
    }

}
