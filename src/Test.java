
public class Test {

	public static void main(String[] args) {
		Integer[][] intarry = new Integer[2][2];
		intarry[0][0]=10;
		intarry[0][1]=20;
		intarry[1][0]=15;
		intarry[1][1]=16;
		
//		for(int i =0;i<intarry.length;i++) {
//			
//			
//			for(int j=0;j<intarry[i].length;j++) {
//				System.out.println(intarry[i][j]);
//				
//			}
//		}
		
		for(Object[] ob:intarry) {
			for(int i=0;i<ob.length;i++) {
				System.out.println(ob[i]);
			}
		}

	}

}
