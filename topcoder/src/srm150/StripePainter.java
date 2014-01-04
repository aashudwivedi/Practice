package srm150;


public class StripePainter {	
	public int minStrokes(String pattern) {		
		return findCount(pattern, '%');
	}
	
	private int findCount(String pattern, char defaultColor) {		
		if(pattern.length() == 0) {			
			return 0;			
		} else if(pattern.length() == 1){			
			return pattern.charAt(0) == defaultColor ? 0 : 1;
			
		} else {			
			int i = 1;
			for(; i < pattern.length(); i++) {
				if(pattern.charAt(0) == pattern.charAt(pattern.length() - i)) {
					break;
				}
			}
			
			int breakIndex = pattern.length() - i;			
			
			int startIndex = 1;
			if(breakIndex == 1) startIndex = 0;
			
			boolean paintReq = true;
			if(defaultColor == pattern.charAt(0)) {
				paintReq = false;
			}
			
			int strokes = paintReq ? 1 : 0;
			
			if(breakIndex == 0) {
				// no similar color found
				return strokes + findCount(pattern.substring(1, pattern.length()), defaultColor);
			} else {
				// we can eiter take this split or not, which ever yields the minimum
				// when split is considered
				int ret1 = strokes  + 
						findCount(pattern.substring(startIndex, breakIndex), pattern.charAt(0)) +
						findCount(pattern.substring(breakIndex + 1, pattern.length()), defaultColor);
				// without taking the split
				int ret2 = strokes + findCount(pattern.substring(1, pattern.length()), defaultColor);
				return Math.min(ret1, ret2);
			}
		}
	}
	
	public static void main(String args[]) {
		StripePainter painter = new StripePainter();
		System.out.println(painter.minStrokes("BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD"));
	}
}
