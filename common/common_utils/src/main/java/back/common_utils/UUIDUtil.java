package back.common_utils;

import java.util.UUID;

public class UUIDUtil {
    public static String getId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-","");
        return id;
    }
}
