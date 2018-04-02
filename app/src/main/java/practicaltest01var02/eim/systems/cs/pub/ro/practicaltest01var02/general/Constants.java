package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.general;

/**
 * Created by Florentina on 02-Apr-18.
 */

public interface Constants {
    final public static String NR1 = "nr1";
    final public static String NR2 = "nr2";

    final public static String OP = "op";

    final public static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;


    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;

    final public static String PROCESSING_THREAD_TAG = "[Processing Thread]";

    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";


    final public static String FIRST_NUMBER = "firstNumber";
    final public static String SECOND_NUMBER = "secondNumber";

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01var02.summean",
            "ro.pub.cs.systems.eim.practicaltest01var02.difmean"
    };
}
