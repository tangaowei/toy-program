/**
Calculate (a^b)mod1337 where a is a positive integer and b is an extremely
large positive integer given in the form of an array
2016-07-10
**/ 
public class SuperPow {
	private static int M = 1337;
	public int superPow(int a, int[] b) {
		int s = 0, e = b.length;
		if(s == e - 1) {
			if(b[s] == 0) return 1;
			if(b[s] == 1) return a % M;
		}
		int ret = 1;
		while(s < e) {
			if((b[e-1] & 1) == 0) {
				a = ((a % M) * (a % M)) % M;
				s = divArray(s, e, b);
			} else {
				b[e-1]--;
				ret = (ret * (a % M)) % M;
			}
		}
		return (ret * (a % M)) % M;
	}

	private int divArray(int s, int e, int[] b) {
		int p = 0;
		for(int i = s; i < e; ++i) {
			int n = p * 10 + b[i];
			b[i] = n / 2;
			p = n % 2;
		}
		if(b[s] == 0) ++s;
		if(s + 1 == e && b[s] == 1) ++s;
		return s;
	}
			
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Usage SuperPow x y1 y2 ...");
			return;
		}
		int a = Integer.parseInt(args[0]);
		int[] b = new int[args.length - 1];
		for(int i = 1; i < args.length; ++i) {
			b[i - 1] = Integer.parseInt(args[i]);
		}

		SuperPow obj = new SuperPow();
		int ret = obj.superPow(a, b);
		System.out.println(ret);
	}
}	
