package A1Q1;

import java.util.*;

import A1Q1.SparseNumericElement;
import A1Q1.SparseNumericVector;
public class tester {

	public static void main(String[] args) {
		
		
		SparseNumericVector X = new SparseNumericVector();
        SparseNumericVector Y = new SparseNumericVector();
        double projection;

        X.add(new SparseNumericElement(150000, 3.1415));
        X.add(new SparseNumericElement(15, 3));
        X.add(new SparseNumericElement(1500, 3.14));
        X.add(new SparseNumericElement(150, 3.1));
        X.add(new SparseNumericElement(15000, 3.141));
        Y.add(new SparseNumericElement(150000, 1));
        Y.add(new SparseNumericElement(15, 1));
        X.remove((long) 150);
       /* System.out.println(X.toString());
        System.out.println(Y.toString());*/
        
        projection = X.dot(Y);

        System.out.println("The inner product of");
        System.out.print(X);
        System.out.println("and");
        System.out.print(Y);
        System.out.println("is ");
        System.out.printf("%.4f\n\n",projection); //answer should be 3*1 + 3.1415*1 = 6.1415
		/*SparseNumericVector x = new SparseNumericVector();
		x.add(new SparseNumericElement(15, 2));
		x.add(new SparseNumericElement(18, 3));
		x.add(new SparseNumericElement(20, 4));
		x.add(new SparseNumericElement(16, 5));
		System.out.println(x.toString());
		
		SparseNumericVector y = new SparseNumericVector();
		y.add(new SparseNumericElement(11, 6));
		y.add(new SparseNumericElement(15, 7));
		y.add(new SparseNumericElement(20, 8));
		System.out.println(y.toString());
		
		System.out.println(x.dot(y));*/
	}

}
