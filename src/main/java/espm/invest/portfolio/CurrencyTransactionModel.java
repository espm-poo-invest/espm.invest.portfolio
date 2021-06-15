//package espm.invest.portfolio;
//
//
//import espm.invest.portfolio.common.datatype.CurrencyTransaction;
//import espm.invest.portfolio.common.datatype.Portfolio;
//import espm.invest.portfolio.common.datatype.StockTransaction;
//import espm.invest.portfolio.common.datatype.TransactionType;
//
//import javax.persistence.*;
//import java.util.Currency;
//import java.util.Date;
//
//@Entity
//@Table(name= "currencytransaction")
//public class CurrencyTransactionModel {
//    @Id
//    @Column(name = "id_currencytransaction")
//    private String idCurrencyTransaction;
//
//    @Column(name = "id_portfolio")
//    private String idPortfolio;
//
//    @Column(name = "id_currency")
//    private String idCurrency;
//
//    @Column(name = "st_date")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date date;
//
//    @Column(name = "amount")
//    private double amount;
//
//    @Column(name = "type")
//    private Integer nrType;
//
//    public CurrencyTransactionModel() {
//
//    }

//    public CurrencyTransactionModel(CurrencyTransaction tc) {
//        this.idCurrencyTransaction = tc.getId();
//        this.date = tc.getDate();
//        this.idPortfolio = tc.getPortfolio().getId();
//        this.idCurrency = tc.getCurrency().getId();
//        this.nrType = tc.getType().equals(TransactionType.VENDA) ? 1 : 2;
//        this.amount = tc.getAmount();
//    }
//
//    public CurrencyTransaction to() {
//        Portfolio portfolio = new Portfolio();
//        portfolio.setId(idPortfolio);
//
//        Currency currency = new Currency();
//        currency.setId(idCurrency);
//
//        CurrencyTransaction tc = new CurrencyTransaction();
//        tc.setId(idCurrencyTransaction);
//        tc.setDate(date);
//        tc.setAmount(amount);
//        tc.setType(1 == nrType ? TransactionType.VENDA : TransactionType.COMPRA);
//        tc.setPortfolio(portfolio);
//        tc.setCurrency(currency);
//        return tc;
//    }
//}
