public class Command {
	VCS vcs;

	public Command(VCS vcs){
		this.vcs = vcs;
	}

    public void execute() {
        // override me!
    }

    public static Command parse(String cmdName, VCS vcs) {
        switch (cmdName){
        	case "exit":
        	 	Exit exit = new Exit(vcs);
	        	return exit;
	        case "listfiles":
	        	ListFiles list = new ListFiles(vcs);
	        	return list;
	        case "commit":
	        	Commit commit = new Commit(vcs);
	        	return commit;
	        default:
	      		System.err.println("Command not recognized");
	      		return null;
        }
    }
}
