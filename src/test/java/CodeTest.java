
import java.util.Calendar;
import java.util.TimeZone;

public class CodeTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(currentYear +" "+ currentMonth +" "+ currentDay);

    }
}
