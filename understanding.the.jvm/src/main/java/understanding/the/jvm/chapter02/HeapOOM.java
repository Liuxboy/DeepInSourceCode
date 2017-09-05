package understanding.the.jvm.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: understanding.jvm <br>
 * Author: liuchundong <br>
 * Date: 2017/8/17 <br>
 * Time: 17:08 <br>
 * Desc:
 * JVM Options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 如果设置了该参数化，可以在发现OOM时，dump出内存快照文件，-XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> oomObjectList = new ArrayList<>();
        while (true) {
            oomObjectList.add(new OOMObject());
        }
    }
    /*
    Connected to the target VM, address: '127.0.0.1:57428', transport: 'socket'
            [GC (Allocation Failure) [PSYoungGen: 8146K->1001K(9216K)] 8146K->5486K(19456K), 0.0229937 secs] [Times: user=0.05 sys=0.01, real=0.03 secs]
            [GC (Allocation Failure) --[PSYoungGen: 9193K->9193K(9216K)] 13678K->19428K(19456K), 0.0271822 secs] [Times: user=0.05 sys=0.00, real=0.03 secs]
            [Full GC (Ergonomics) [PSYoungGen: 9193K->0K(9216K)] [ParOldGen: 10234K->10224K(10240K)] 19428K->10224K(19456K), [Metaspace: 2919K->2919K(1056768K)], 0.4144664 secs] [Times: user=0.39 sys=0.00, real=0.41 secs]
            [Full GC (Ergonomics) [PSYoungGen: 7473K->7912K(9216K)] [ParOldGen: 10224K->8113K(10240K)] 17697K->16026K(19456K), [Metaspace: 2919K->2919K(1056768K)], 0.4994117 secs] [Times: user=0.66 sys=0.00, real=0.50 secs]
            [Full GC (Allocation Failure) [PSYoungGen: 7912K->7912K(9216K)] [ParOldGen: 8113K->8102K(10240K)] 16026K->16015K(19456K), [Metaspace: 2919K->2919K(1056768K)], 0.3101545 secs] [Times: user=0.45 sys=0.00, real=0.31 secs]
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    at java.util.Arrays.copyOf(Arrays.java:3210)
    at java.util.Arrays.copyOf(Arrays.java:3181)
    at java.util.ArrayList.grow(ArrayList.java:261)
    at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
    at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
    at java.util.ArrayList.add(ArrayList.java:458)
    at understanding.jvm.chapter02.HeapOOM.main(HeapOOM.java:21)
    Disconnected from the target VM, address: '127.0.0.1:57428', transport: 'socket'
    Heap
    PSYoungGen      total 9216K, used 8154K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
    eden space 8192K, 99% used [0x00000000ff600000,0x00000000ffdf6aa0,0x00000000ffe00000)
    from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
    to   space 1024K, 49% used [0x00000000fff00000,0x00000000fff7fda0,0x0000000100000000)
    ParOldGen       total 10240K, used 8102K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
    object space 10240K, 79% used [0x00000000fec00000,0x00000000ff3e9b70,0x00000000ff600000)
    Metaspace       used 2951K, capacity 4486K, committed 4864K, reserved 1056768K
    class space    used 314K, capacity 386K, committed 512K, reserved 1048576K
    */
}
