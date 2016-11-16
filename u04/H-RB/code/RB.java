public class RB{

	public String[] programm;
	public int amount;
	public int pos;
	public int[] reg;

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
						System.out.println("Moves");
						return -1;
					}
					++count;
					break;
				case "for":
					if(checkT(parts,2)){
						System.out.println(str);
						System.out.println("for");
						return -1;

					}
					break;
				case "endfor":
					if(checkT(parts,1)){
						System.out.println("endfor");
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
		programm = new String[input.length];
		programm = java.util.Arrays.copyOf(input, input.length);
		return count;
	}

	public boolean checkT(String[] parts, int count){
		if (parts.length - 1 > count) return true;
		for(int i = 1; i<parts.length; i++){
			if(!(parts[i].matches("\\d*"))) return true;
		}
		return false;
	}

	public String schritt(){
		while(true){
			if(programm.length == 0) return "end";
			if(pos>(programm.length-1)) return "end";
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
					System.out.println("");
					System.out.println("PROG:"+programm[pos]);
 					System.out.println("POS:" + pos);

					int forpos = pos;
					int forreg = Integer.parseInt(parts[1]);
					int forval = Integer.parseInt(parts[2]);
					System.out.println("FORREG :"+ forreg);
					System.out.println("REG :"+ reg[forreg]);
					System.out.println("TO :"+forval);
					System.out.println("----------");
					if(reg[forreg] < forval){
						reg[forreg]++;
						pos++;
					}else{
						System.out.println("POS:" + pos);
						System.out.println("PROG:" + programm[pos]);
						while(!(programm[pos].split(" ")[0].equals("endfor")&&(Integer.parseInt(programm[pos].split(" ")[1]) == forpos))){
							++pos; 
						};
						pos++;
						reg[forreg] = 0;
					}
					break;
 			}
		}
	} 
}