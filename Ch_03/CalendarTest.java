import java.time.*;

public class CalendarTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1); // set to start of month
        DayOfWeek weekday = date.getDayOfWeek();
        System.out.println("Day of the week is: " + weekday);
        int value = weekday.getValue(); // 1 = Monday, ..., 7 = Sunday

        System.out.println("Value is: " + value);

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++) {
            System.out.print("    ");
        }

        // goes through the loop until we finish going through all the days of the
        // month
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());

            // This is for putting a * next to the current day of the month
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }

            date = date.plusDays(1);

            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }

        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }
}