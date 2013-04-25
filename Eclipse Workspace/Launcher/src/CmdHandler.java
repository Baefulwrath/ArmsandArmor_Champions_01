public class CmdHandler {
	public static void activate(String cmd){
		switch(cmd){
			case "save":Main.saveStartupFile();
			break;
			case "load":Main.loadStartupFile();
			break;
			case "testAdmin":Main.testAdmin();
			break;
			case "title":Main.title = Main.switchString(Main.title, true);
			break;
			case "useGL20":Main.useGL20 = Main.switchBoolean(Main.useGL20, true);
			break;
			case "width":Main.width = Main.switchInt(Main.width, false);
			break;
			case "height":Main.height = Main.switchInt(Main.height, false);
			break;
			case "resizable":Main.resizable = Main.switchBoolean(Main.resizable, false);
			break;
			case "fullscreen":Main.fullscreen = Main.switchBoolean(Main.fullscreen, false);
			break;
			case "launch":Main.launch();
			break;
			case "exit":Main.exit();
			break;
		}
	}
}
