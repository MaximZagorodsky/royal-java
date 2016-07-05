import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxsoftware.webapp.Entity.Product;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Proxima on 29.06.2016.
 */
public class ReadTest {

    public static void main(String[] args) throws IOException {
        BigInteger id = BigInteger.valueOf(0L);

        for (int i = 0; i < 10; i++) {
            id = id.add(BigInteger.valueOf(4L));


            System.out.println(id);
        }


    }
}
