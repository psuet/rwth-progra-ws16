/**
 * Class for rb.
 * 
 * @author     Paul Sütterlin - 366676
 * @version    1.0
 */
public class RB{

	int amount;
	private String[] programm;
	private int pos;
	private int[] reg;

	/**
	 * Loads program onto the RB
	 *
	 * @param      input  Programm
	 *
	 * @return     -1 for error; -2 for too long programms; normal: int length of the programm
	 */
	public int programmHochladen(String[] input){
		int count = 0;
		reg = new int[2];
		pos=0;
		for(String str:input){
			String[] parts = str.split(" ");
			switch (parts[0]){
				case "move":
				case "turnLeft":
				case "turnRight":
				case "pickUp":
					if(parts.length > 1){
						return -1;
					}
					++count;
					break;
				case "for":
					if(checkArgs(parts,2)){
						return -1;
					}
					break;
				case "endfor":
					if(checkArgs(parts,1)){
						return -1;
					}
					break;
				default:
					return -1;
			}
		}
		if(count > amount){
			return -2;
		}
		//Copying the input array into the program
		programm = new String[input.length];
		programm = java.util.Arrays.copyOf(input, input.length);
		return count;
	}

	/**
	 * checks arguments of "for" and "endfor" for invalid inputs
	 *
	 * @param      parts  arguments as String array
	 * @param      count  how many arguments are allowed behind the statement
	 *
	 * @return     true if illegal statement was discovered
	 */
	private boolean checkArgs(String[] parts, int count){
		if (parts.length - 1 > count) return true;
		for(int i = 1; i<parts.length; i++){
			if(!(parts[i].matches("\\d*"))) return true;
		}
		return false;
	}

	/**
	 * Performes a step of the program
	 *
	 * @return     command to execute
	 */
	public String schritt(){
		while(!(programm.length == 0 || pos>(programm.length-1))){
 			String[] parts = programm[pos].split(" ");
 			switch(parts[0]){
 				case "move":
				case "turnLeft":
				case "turnRight":
				case "pickUp":
					++pos;
					return parts[0];
				case "endfor":
					pos = Integer.parseInt(parts[1]);
					break;
				case "for":
					int forpos = pos;
					int forreg = Integer.parseInt(parts[1]);
					if(reg[forreg] < Integer.parseInt(parts[2])){
						reg[forreg]++;
						++pos;
					}else{
						while(!(programm[pos].split(" ")[0].equals("endfor")&&(Integer.parseInt(programm[pos].split(" ")[1]) == forpos))){
							++pos; 
						};
						++pos;
						reg[forreg] = 0;
					}
					break;
 			}
		}
		return "end";
	} 
}