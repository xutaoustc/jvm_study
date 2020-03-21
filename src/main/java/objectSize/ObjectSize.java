package objectSize;

import com.carrotsearch.hppc.LongObjectHashMap;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.util.HashMap;
import java.util.Map;

public class ObjectSize {
    public static void main(String[] args) {
        com.carrotsearch.hppc.LongObjectMap hppcMap = new LongObjectHashMap();
        Map<String,String> hashMap = new HashMap<>();
        Map<Long,String> longKeyHashMap = new HashMap<>();

        for(long i=0;i<100000;i++){
            hashMap.put(i+"",i+ "," +i);
            longKeyHashMap.put(i, i+ "," +i);
            hppcMap.put(i,i+ "," +i);
        }

        System.out.println(ObjectSizeCalculator.getObjectSize(hashMap));
        System.out.println(ObjectSizeCalculator.getObjectSize(longKeyHashMap));
        System.out.println(ObjectSizeCalculator.getObjectSize(hppcMap));

    }
}
