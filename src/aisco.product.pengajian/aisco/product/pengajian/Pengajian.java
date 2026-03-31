package aisco.product.pengajian;

import aisco.program.core.Program;
import aisco.program.ProgramFactory;
import aisco.financialreport.core.FinancialReport;
import aisco.financialreport.FinancialReportFactory;
import java.util.ArrayList;
import java.util.List;


public class Pengajian {
    private static final int INDEX_KAJIAN_SUBUH = 0;
    private static final int INDEX_PENGAJIAN_AKBAR = 1;

    private static FinancialReport income1;
    private static FinancialReport expense1;

    public static List<Program> addProgram()
    {
        System.out.println("\n Programs: ");
        List<Program> programs = new ArrayList<>();

        // Buat base activity programs
        Program kajianSubuh = ProgramFactory.createProgram("aisco.program.activity.ProgramImpl", 1, "Kajian Subuh", "Kajian rutin ba'da subuh untuk jamaah", "50 jamaah", "Ustadz Ahmad", "https://pengajian.id/kajiansubuh-logo");
        Program pengajianAkbar = ProgramFactory.createProgram("aisco.program.activity.ProgramImpl", 2, "Pengajian Akbar", "Pengajian akbar bulanan bersama ustadz tamu", "200 jamaah", "DKM Al-Ikhlas", "https://pengajian.id/akbar-logo");

        // Dekorasi dengan periodic
        kajianSubuh = ProgramFactory.createProgram("aisco.program.periodic.ProgramImpl", kajianSubuh, "daily");
        pengajianAkbar = ProgramFactory.createProgram("aisco.program.periodic.ProgramImpl", pengajianAkbar, "monthly");

        programs.add(INDEX_KAJIAN_SUBUH, kajianSubuh);
        programs.add(INDEX_PENGAJIAN_AKBAR, pengajianAkbar);
        return programs;
    }

    public static List<FinancialReport> addIncome(List<Program> programs)
    {
        List<FinancialReport> incomes = new ArrayList<>();
        income1 = FinancialReportFactory.createFinancialReport("aisco.financialreport.income.FinancialReportImpl", FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl","1", "01-03-2026", 500000, "Infaq Jamaah Subuh", programs.get(INDEX_KAJIAN_SUBUH), "11000"), "Cash");
        incomes.add(income1);
        incomes.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.income.FinancialReportImpl", FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl","2", "15-03-2026", 2000000, "Donasi Pengajian Akbar", programs.get(INDEX_PENGAJIAN_AKBAR), "11000"), "Transfer"));
        incomes.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.income.FinancialReportImpl", FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl","3", "20-03-2026", 1000000, "Sumbangan Hamba Allah", programs.get(INDEX_KAJIAN_SUBUH), "110"), "Cash"));
        return incomes;
    }

    public static List<FinancialReport> addExpense(List<Program> programs)
    {
        List<FinancialReport> expenses = new ArrayList<>();
        expense1 = FinancialReportFactory.createFinancialReport("aisco.financialreport.expense.FinancialReportImpl",FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl","10", "05-03-2026", 200000, "Konsumsi Kajian Subuh", programs.get(INDEX_KAJIAN_SUBUH), "41000"));
        expenses.add(expense1);
        expenses.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.expense.FinancialReportImpl",FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl","20", "15-03-2026", 750000, "Sewa Sound System Pengajian Akbar", programs.get(INDEX_PENGAJIAN_AKBAR), "41000")));
        expenses.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.expense.FinancialReportImpl",FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl","30", "15-03-2026", 300000, "Konsumsi Pengajian Akbar", programs.get(INDEX_PENGAJIAN_AKBAR), "41000")));
        return expenses;
    }

    public static void main(String[] args) {
        System.out.println("Product Pengajian");
        List<Program> programs = addProgram();
        System.out.println(programs);

        List<FinancialReport> incomes = addIncome(programs);
        income1.printHeader();
        System.out.println(incomes);
        int totalincome = ((aisco.financialreport.income.FinancialReportImpl)income1).total(incomes);

        List<FinancialReport> expenses = addExpense(programs);
        expense1.printHeader();
        System.out.println(expenses);
        int totalexpense = ((aisco.financialreport.expense.FinancialReportImpl) expense1).total(expenses);

        int balance = totalincome - totalexpense;
        System.out.println("Balance: " + balance);
    }
}
