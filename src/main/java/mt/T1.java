package mt;
import java.util.Scanner;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 19:21 2022/4/14
 * @ Description：
 */
public class T1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double m = scanner.nextInt();
		double n = scanner.nextInt();
		double sum = m;
		while (n!=1) {
			m = Math.sqrt(m);
			sum += m;
			n--;
		}
		double p = scanner.nextInt();
		double q = scanner.nextInt();
		double sum1 = p;
		while (q-- != 0) {
			p = Math.sqrt(p);
			sum1 += p;
		}
		System.out.println(String.format("%.2f", sum));
		System.out.println(String.format("%.2f", sum1));
		System.out.println(sum1);
	}
}
