public class CallStack {
	public static void main(String[] args) {
		if (args.length > 0) {
			double[] vals = parse(args);
			double sd     = stdev(vals);
			System.out.println(sd);
		}
	}
	
	public static double[] parse(String[] args) {
		double[] d = new double[args.length];
		for (int i = 0; i < args.length; ++i) d[i] = Double.parseDouble(args[i]);
		return d;
	}
	
	public static double sum(double[] vals) {
		double total = 0.0;
		for (int i = 0; i < vals.length; ++i) total += vals[i];
		return total;
	}
	
	public static double mean(double[] vals) {
		double total = sum(vals);
		return total/vals.length;
	}
	
	public static double variance(double[] vals) {
		double mu = mean(vals);
		double var = 0.0;
		double residual = 0.0;
		for (int i = 0; i < vals.length; ++i) {
			residual = vals[i] - mu;
			var += residual * residual;
		}
		return var/vals.length;
	}
	
	public static double stdev(double[] vals) {
		double var = variance(vals);
		double sd = Math.sqrt(var);
		return sd;
	}
}
