import com.luoan.PropertiesLogic;
import com.luoan.Test;
import com.luoan.view.Interface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Interface anInterface = new Interface();
        new PropertiesLogic(anInterface);
        anInterface.setVisible(true);
    }
}
