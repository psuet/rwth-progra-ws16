public class ListFiles extends Command{

	public ListFiles(VCS vcs){
		super(vcs);
	}

	public void execute(){
		if (Util.listFiles(vcs.getRootDir()).length > 0){
			
			for(String file : Util.listFiles(vcs.getRootDir())){
				System.out.println(file);
			}
		}
	}
}