public class Commit extends ListFiles{
	
	public Commit(VCS vcs){
		super(vcs);
	}	

	public void execute(){
		String newBackupDir = Util.appendFileOrDirname(vcs.getBackupDir(), Util.getTimestamp());
		Util.mkdir(newBackupDir);
		for(String file : Util.listFiles(vcs.getBackupDir())){
			Util.moveFile(Util.appendFileOrDirname(vcs.getBackupDir(), file), Util.appendFileOrDirname(newBackupDir, file)) ;
		}
		for(String file : Util.listFiles(vcs.getRootDir())){
			Util.copyFile(Util.appendFileOrDirname(vcs.getRootDir(), file), Util.appendFileOrDirname(vcs.getBackupDir(), file)) ;
		}
		System.out.println("Commited the following files:");
		super.execute();


	}
}