package aisco.product.rumahbelajarbasic;

import aisco.program.ProgramFactory;
import aisco.program.core.Program;
import aisco.financialreport.FinancialReportFactory;
import aisco.financialreport.core.FinancialReport;
import java.util.ArrayList;
import java.util.List;

public class RumahBelajarBasic {
    private static final int INDEX_LITERASI = 0;
    private static final int INDEX_MENTORING = 1;

    private static FinancialReport income1;
    private static FinancialReport expense1;

    public static List<Program> addProgram() {
        System.out.println("\n Programs: ");
        List<Program> programs = new ArrayList<>();

        Program literasi = ProgramFactory.createProgram(
            "aisco.program.activity.ProgramImpl",
            1,
            "Kelas Literasi Dasar",
            "Kelas membaca dan menulis untuk anak binaan",
            "120 siswa",
            "Komunitas Rumah Belajar",
            "https://rumahbelajar.id/literasi"
        );

        Program mentoring = ProgramFactory.createProgram(
            "aisco.program.activity.ProgramImpl",
            2,
            "Mentoring Matematika",
            "Pendampingan matematika untuk persiapan ujian",
            "80 siswa",
            "Relawan Kampus",
            "https://rumahbelajar.id/mentoring"
        );

        literasi = ProgramFactory.createProgram("aisco.program.category.ProgramImpl", literasi, "Pendidikan Dasar");
        mentoring = ProgramFactory.createProgram("aisco.program.category.ProgramImpl", mentoring, "Pendampingan Akademik");

        programs.add(INDEX_LITERASI, literasi);
        programs.add(INDEX_MENTORING, mentoring);
        return programs;
    }

    public static List<FinancialReport> addIncome(List<Program> programs) {
        List<FinancialReport> incomes = new ArrayList<>();
        income1 = FinancialReportFactory.createFinancialReport(
            "aisco.financialreport.income.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport(
                "aisco.financialreport.core.FinancialReportImpl",
                "1", "02-04-2026", 7000000,
                "Dana Hibah Pendidikan", programs.get(INDEX_LITERASI), "11000"
            ),
            "Transfer"
        );
        incomes.add(income1);

        incomes.add(FinancialReportFactory.createFinancialReport(
            "aisco.financialreport.income.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport(
                "aisco.financialreport.core.FinancialReportImpl",
                "2", "05-04-2026", 1500000,
                "Donasi Orang Tua Siswa", programs.get(INDEX_MENTORING), "11000"
            ),
            "Cash"
        ));
        return incomes;
    }

    public static List<FinancialReport> addExpense(List<Program> programs) {
        List<FinancialReport> expenses = new ArrayList<>();
        expense1 = FinancialReportFactory.createFinancialReport(
            "aisco.financialreport.expense.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport(
                "aisco.financialreport.core.FinancialReportImpl",
                "10", "04-04-2026", 2500000,
                "Pengadaan Modul Belajar", programs.get(INDEX_LITERASI), "41000"
            )
        );
        expenses.add(expense1);

        expenses.add(FinancialReportFactory.createFinancialReport(
            "aisco.financialreport.expense.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport(
                "aisco.financialreport.core.FinancialReportImpl",
                "20", "06-04-2026", 1000000,
                "Transport Relawan", programs.get(INDEX_MENTORING), "41000"
            )
        ));
        return expenses;
    }

    public static void main(String[] args) {
        System.out.println("Product Rumah Belajar Basic");
        List<Program> programs = addProgram();
        System.out.println(programs);

        List<FinancialReport> incomes = addIncome(programs);
        income1.printHeader();
        System.out.println(incomes);
        int totalincome = ((aisco.financialreport.income.FinancialReportImpl) income1).total(incomes);

        List<FinancialReport> expenses = addExpense(programs);
        expense1.printHeader();
        System.out.println(expenses);
        int totalexpense = ((aisco.financialreport.expense.FinancialReportImpl) expense1).total(expenses);

        int balance = totalincome - totalexpense;
        System.out.println("Balance: " + balance);
    }
}
