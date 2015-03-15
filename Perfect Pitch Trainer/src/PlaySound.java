public class PlaySound {
	
	private static final MakePitch s = new MakePitch();
	private static final String A_NOTE = "A_NOTE.wav";
	private static final String AS_NOTE = "AS_NOTE.wav";
	private static final String B_NOTE = "B_NOTE.wav";
	private static final String C_NOTE = "C_NOTE.wav";
	private static final String CS_NOTE = "CS_NOTE.wav";
	private static final String D_NOTE = "D_NOTE.wav";
	private static final String DS_NOTE = "DS_NOTE.wav";
	private static final String E_NOTE = "E_NOTE.wav";
	private static final String F_NOTE = "F_NOTE.wav";
	private static final String FS_NOTE = "FS_NOTE.wav";
	private static final String G_NOTE = "G_NOTE.wav";
	private static final String GS_NOTE = "GS_NOTE.wav";
	
	public static final String [] files = {
		A_NOTE, AS_NOTE, B_NOTE, C_NOTE, CS_NOTE, D_NOTE, DS_NOTE, E_NOTE, F_NOTE, FS_NOTE, G_NOTE, GS_NOTE
	};
	
	public static void playPitch(int pitchNumber) {
		s.playSound(System.getProperty("user.dir") + '/' + files[pitchNumber]);
	}
	
	public static void playSequence() {
		s.playSound("PALATE_CLEANSER.wav");
	}
}
