package OORefactor;

import java.util.*;

public class Utils {
    public static boolean getRand() {
	return (new Random()).nextBoolean();
    }
    
    public static int getRand(int low, int high) {
	return low+(new Random()).nextInt(high-low+1);
    }
}
