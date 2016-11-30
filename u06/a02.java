import static java.lang.System.out;
public class a02{
	private Integer i1;
	private int i2;
	private Double d;
	private float f;

	public a02(int a, int b, int c, int d){
		this.i1 = a;
		this.i2 = b;
		this.d = (double)d;
		this.f = c;
	}

	public a02(Integer a, int b, Double c, float d){
		this.i1 = a;
		this.i2 = b;
		this.d = c;
		this.f = d;
	}

	public int f(int x, float y){
		return 11;
	}

	public int f(Double x, long y){
		return 12;
	}

	public Integer f(double x, int y){
		return 13;
	}

	public double g(Float x){
		return 7.0;
	}

	public Float g(double x){
		return 8f;
	}
	public static void main(String[] args){
		a02 b1 = new a02(1,2,3,4);
		out.println(b1.d);
		out.println(b1.f(7d,8L));
		out.println(b1.f(10d,17));
		out.println(b1.f(5,6L));
		a02 b2 = new a02(b1.i1, 5, 6, 9);
		out.println(b2.f);
		out.println(b2.f(b1.f,b1.i2));
		a02 b3 = new a02(b2.i1, 14, 1.5, 16);
		out.println(b3.d);
		out.println(b3.g(b1.i1));
		out.println(b3.g(new Float(18)));
		out.println(b3.f(b2.g(19f),21));	
	}


}