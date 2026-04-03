package aisco.product.mbg;

import aisco.program.core.Program;
import aisco.program.ProgramFactory;
import aisco.financialreport.core.FinancialReport;
import aisco.financialreport.FinancialReportFactory;
import aisco.donation.core.Donation;
import aisco.donation.DonationFactory;
import java.util.ArrayList;
import java.util.List;

public class MakanBergiziGratis {
    private static final int INDEX_SD = 0;
    private static final int INDEX_SMP = 1;

    private static FinancialReport income1;
    private static FinancialReport expense1;

    public static List<Program> addProgram() {
        System.out.println("\n Programs: ");
        List<Program> programs = new ArrayList<>();

        Program makanSD = ProgramFactory.createProgram("aisco.program.activity.ProgramImpl",
            1, "Makan Bergizi SD", "Penyediaan makan bergizi untuk siswa SD",
            "500 siswa", "Dinas Pendidikan", "https://mbg.id/logo-sd");
        Program makanSMP = ProgramFactory.createProgram("aisco.program.activity.ProgramImpl",
            2, "Makan Bergizi SMP", "Penyediaan makan bergizi untuk siswa SMP",
            "300 siswa", "Dinas Kesehatan", "https://mbg.id/logo-smp");

        makanSD = ProgramFactory.createProgram("aisco.program.category.ProgramImpl", makanSD, "Program Gizi Anak");
        makanSMP = ProgramFactory.createProgram("aisco.program.category.ProgramImpl", makanSMP, "Program Gizi Remaja");

        programs.add(INDEX_SD, makanSD);
        programs.add(INDEX_SMP, makanSMP);
        return programs;
    }

    public static List<FinancialReport> addIncome(List<Program> programs) {
        List<FinancialReport> incomes = new ArrayList<>();
        income1 = FinancialReportFactory.createFinancialReport("aisco.financialreport.income.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl",
                "1", "01-03-2026", 10000000, "Dana APBN Makan Bergizi", programs.get(INDEX_SD), "11000"), "Transfer");
        incomes.add(income1);
        incomes.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.income.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl",
                "2", "05-03-2026", 5000000, "Donasi CSR PT Sehat Sejahtera", programs.get(INDEX_SMP), "11000"), "Transfer"));
        incomes.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.income.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl",
                "3", "10-03-2026", 2000000, "Donasi Masyarakat", programs.get(INDEX_SD), "110"), "Cash"));
        return incomes;
    }

    public static List<FinancialReport> addExpense(List<Program> programs) {
        List<FinancialReport> expenses = new ArrayList<>();
        expense1 = FinancialReportFactory.createFinancialReport("aisco.financialreport.expense.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl",
                "10", "02-03-2026", 3000000, "Beli Bahan Makanan SD", programs.get(INDEX_SD), "41000"));
        expenses.add(expense1);
        expenses.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.expense.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl",
                "20", "02-03-2026", 2000000, "Beli Bahan Makanan SMP", programs.get(INDEX_SMP), "41000")));
        expenses.add(FinancialReportFactory.createFinancialReport("aisco.financialreport.expense.FinancialReportImpl",
            FinancialReportFactory.createFinancialReport("aisco.financialreport.core.FinancialReportImpl",
                "30", "03-03-2026", 1500000, "Ongkos Distribusi", programs.get(INDEX_SD), "41000")));
        return expenses;
    }

    public static void addDonation() {
        Donation donate = DonationFactory.createDonation("aisco.donation.pgateway.DonationImpl");
        donate.addDonation();
        donate.getDonation();
    }

    public static void main(String[] args) {
        System.out.println("Product Makan Bergizi Gratis");
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

        addDonation();
        int balance = totalincome - totalexpense;
        System.out.println("Balance: " + balance);
    }
}
