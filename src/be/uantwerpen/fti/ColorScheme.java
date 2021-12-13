package be.uantwerpen.fti;


public class ColorScheme {
    private static ColorScheme single_instance = null;


    private Scheme Mode;


    private ColorScheme() {
        this.Mode = Scheme.Light;
    }


    public static ColorScheme getInstance() {
        if (single_instance == null)
            single_instance = new ColorScheme() {
            };
        return single_instance;
    }

    public Scheme getMode() {
        return Mode;
    }

    public void setMode(Scheme mode) {
        Mode = mode;
    }
}
