import java.util.ArrayList;
import java.util.List;

public class TestGC {
    public static void main(String[] args) {
        List<byte[]> bl = new ArrayList<byte[]>();
        while(true){
            byte[] b = new byte[1024];
            bl.add(b);
        }
    }
}
