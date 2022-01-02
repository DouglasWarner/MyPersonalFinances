package com.douglas.mypersonalfinances;

import android.icu.util.Calendar;

import com.douglas.mypersonalfinances.data.model.Day;
import com.douglas.mypersonalfinances.data.model.Expense;
import com.douglas.mypersonalfinances.data.model.Income;
import com.douglas.mypersonalfinances.data.model.Month;
import com.douglas.mypersonalfinances.data.model.Year;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static Calendar getCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.YEAR, 2022);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c;
    }

    private static Date getDate() {
        return getCalendar().getTime();
    }

    public static ArrayList<Year> getYear() {
        ArrayList<Year> years = new ArrayList<>();

        years.add(new Year(getDate(), getMonth()));

        return years;
    }

    public static ArrayList<Month> getMonth() {
        ArrayList<Month> months = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            months.add(new Month(getDate(), getDays(), 100f, 100f));
        }

        return months;
    }

    public static ArrayList<Day> getDays() {
        ArrayList<Day> days = new ArrayList<>();
        Calendar c = getCalendar();
        for (int i = 1; i <= 31; i++) {
            Day d = new Day(c.getTime(), getExpenses(c.getTime()), getIncome(c.getTime()));
            days.add(d);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;
    }

    public static ArrayList<Expense> getExpenses(Date d) {
        ArrayList<Expense> expenses = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            expenses.add(new Expense(i, d, new Random().nextFloat(), "Gasto "+i, "Sueldo "+i));
        }

        return expenses;
    }

    public static ArrayList<Income> getIncome(Date d) {
        ArrayList<Income> incomes = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            incomes.add(new Income(i, d, new Random().nextFloat(), "Ingreso "+i, "Sueldo "+i));
        }

        return incomes;
    }
}
