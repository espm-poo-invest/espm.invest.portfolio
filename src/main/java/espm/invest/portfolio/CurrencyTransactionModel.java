package espm.invest.portfolio;

import espm.invest.portfolio.common.datatype.CurrencyTransaction;
import espm.invest.portfolio.common.datatype.Portfolio;
import espm.invest.portfolio.common.datatype.StockTransaction;
import espm.invest.portfolio.common.datatype.TransactionType;

import javax.persistence.*;
import java.util.Currency;
import java.util.Date;

@Entity
@Table(name= "currencytransaction")
public class CurrencyTransactionModel {
    @Id
    @Column(name = "id_currencytransaction")
    private String idCurrencyTransaction;

    @Column(name = "id_portfolio")
    private String idPortfolio;

    @Column(name = "id_currency")
    private String idCurrency;

    @Column(name = "st_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "type")
    private Integer nrType;

    public CurrencyTransactionModel() {

    }

    public CurrencyTransactionModel(CurrencyTransaction ts) {
        this.idCurrencyTransaction = ts.getId();
        this.date = ts.getDate();
        this.idPortfolio = ts.getPortfolio().getId();
        this.idCurrency = ts.getCurrency().getId();
        this.nrType = ts.getType().equals(TransactionType.VENDA) ? 1 : 2;
        this.amount = ts.getAmount();
    }

    public CurrencyTransaction to() {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(idPortfolio);

        Currency currency = new Currency();
        currency.setId(idCurrency);

        CurrencyTransaction ts = new CurrencyTransaction();
        ts.setId(idCurrencyTransaction);
        ts.setDate(date);
        ts.setAmount(amount);
        ts.setType(1 == nrType ? TransactionType.VENDA : TransactionType.COMPRA);
        ts.setPortfolio(portfolio);
        ts.setCurrency(currency);
        return ts;
    }
}
