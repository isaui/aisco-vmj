package aisco.product.goodschool;

import aisco.program.core.Program;
import aisco.program.ProgramFactory;
import aisco.financialreport.core.FinancialReport;
import aisco.financialreport.FinancialReportFactory;
import aisco.donation.core.Donation;
import aisco.donation.DonationFactory;
import java.util.ArrayList;
import java.util.List;

public class GoodSchool {
        private static final int INDEX_SCHOOL = 0;
        private static final int INDEX_LIBRARY = 1;
        private static final int INDEX_ELECTRICITY = 2;
        private static final int INDEX_SCHOLARSHIP = 3;

        private static FinancialReport income1;
        private static FinancialReport expense1;

        public static List<Program> addProgram() {
                System.out.println("\n Programs: ");
                List<Program> programs = new ArrayList<>();
                Program schoolconstruction = ProgramFactory.createProgram("aisco.program.activity.ProgramImpl", 1,
                                "School Construction", "Construct the building of elementary school", "100 students",
                                "Government",
                                "https://www.myschool.splelive.id/logo");
                Program freelibrary = ProgramFactory.createProgram("aisco.program.activity.ProgramImpl", 2,
                                "Free Library",
                                "Library for underprivileged children ", "children", "BeriBuku Community",
                                "https://www.myschool.splelive.id/liblogo");
                Program paymentelectricity = ProgramFactory.createProgram("aisco.program.operational.ProgramImpl", 3,
                                "Electricity Payment", "Training for new teachers", "10 Teachers");
                Program withscholarship = ProgramFactory.createProgram("aisco.program.scholarship.ProgramImpl",
                                paymentelectricity, "Scholarship Venedict", "Partial Scholarship", 24);
                programs.add(INDEX_SCHOOL, schoolconstruction);
                programs.add(INDEX_LIBRARY, freelibrary);
                programs.add(INDEX_ELECTRICITY, paymentelectricity);
                programs.add(INDEX_SCHOLARSHIP, withscholarship);
                return programs;
        }

        public static List<FinancialReport> addIncome(List<Program> programs) {
                List<FinancialReport> incomes = new ArrayList<>();
                income1 = FinancialReportFactory
                                .createFinancialReport("aisco.financialreport.income.FinancialReportImpl",
                                                FinancialReportFactory.createFinancialReport(
                                                                "aisco.financialreport.core.FinancialReportImpl",
                                                                "1", "23-10-2019", 100000, "Donation Ana",
                                                                programs.get(INDEX_SCHOOL), "11000"),
                                                "Transfer");
                incomes.add(income1);
                incomes.add(
                                FinancialReportFactory.createFinancialReport(
                                                "aisco.financialreport.income.FinancialReportImpl",
                                                FinancialReportFactory.createFinancialReport(
                                                                "aisco.financialreport.core.FinancialReportImpl",
                                                                "2", "24-10-2019", 3000000, "Donation Joni",
                                                                programs.get(INDEX_LIBRARY), "11000"),
                                                "Cash"));
                incomes.add(
                                FinancialReportFactory.createFinancialReport(
                                                "aisco.financialreport.income.FinancialReportImpl",
                                                FinancialReportFactory.createFinancialReport(
                                                                "aisco.financialreport.core.FinancialReportImpl",
                                                                "3", "11-11-2019", 5000000, "CSR Firma SJT",
                                                                programs.get(INDEX_SCHOOL), "110"),
                                                "Transfer"));
                incomes.add(
                                FinancialReportFactory.createFinancialReport(
                                                "aisco.financialreport.income.FinancialReportImpl",
                                                FinancialReportFactory.createFinancialReport(
                                                                "aisco.financialreport.core.FinancialReportImpl",
                                                                "3", "11-11-2019", 50000, "Donation Aida",
                                                                programs.get(INDEX_ELECTRICITY), "110"),
                                                "Cash"));
                return incomes;
        }

        public static List<FinancialReport> addExpense(List<Program> programs) {
                List<FinancialReport> expenses = new ArrayList<>();
                expense1 = FinancialReportFactory.createFinancialReport(
                                "aisco.financialreport.expense.FinancialReportImpl",
                                FinancialReportFactory.createFinancialReport(
                                                "aisco.financialreport.core.FinancialReportImpl", "10",
                                                "23-10-2019", 1000000, "Buy Cement", programs.get(INDEX_SCHOOL),
                                                "41000"));
                expenses.add(expense1);
                expenses.add(FinancialReportFactory.createFinancialReport(
                                "aisco.financialreport.expense.FinancialReportImpl",
                                FinancialReportFactory.createFinancialReport(
                                                "aisco.financialreport.core.FinancialReportImpl", "20",
                                                "24-10-2019", 1500000, "Buy Bookcase", programs.get(INDEX_LIBRARY),
                                                "410")));
                expenses.add(FinancialReportFactory.createFinancialReport(
                                "aisco.financialreport.expense.FinancialReportImpl",
                                FinancialReportFactory.createFinancialReport(
                                                "aisco.financialreport.core.FinancialReportImpl", "30",
                                                "12-10-2019", 50000, "Book ABC", programs.get(INDEX_LIBRARY),
                                                "41000")));
                return expenses;
        }

        public static void addDonation() {
                Donation donate = DonationFactory.createDonation("aisco.donation.pgateway.DonationImpl");
                donate.addDonation();
                donate.getDonation();
        }

        public static List<Program> addScholarship(List<Program> programs) {
                List<Program> scholarships = new ArrayList<>();
                Program withscholarship = ProgramFactory.createProgram("aisco.program.scholarship.ProgramImpl",
                                programs.get(INDEX_SCHOLARSHIP), "Scholarship Dhuafa", "Full Scholarship", 48);
                scholarships.add(withscholarship);
                return scholarships;
        }

        public static void main(String[] args) {
                System.out.println("Product Good School");
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

                System.out.println("\nAdd Scholarship Program: ");
                Program withscholarship = ProgramFactory.createProgram("aisco.program.scholarship.ProgramImpl",
                                programs.get(0), "Scholarship Venedict", "Partial Scholarship", 24);
                System.out.println(withscholarship);
        }
}
