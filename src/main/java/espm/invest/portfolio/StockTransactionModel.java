package espm.invest.portfolio;

import espm.invest.portfolio.common.datatype.Portfolio;
import espm.invest.portfolio.common.datatype.StockTransaction;
import espm.invest.portfolio.common.datatype.TransactionType;
import espm.invest.stock.common.datatype.Stock;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "stocktransaction")
public class StockTransactionModel {

    @Id
    @Column(name = "id_stocktransaction")
    private String idStockTransaction;

    @Column(name = "id_portfolio")
    private String idPortfolio;

    @Column(name = "id_stock")
    private String idStock;

    @Column(name = "st_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "type")
    private Integer nrType;

    public StockTransactionModel() {

    }

    public StockTransactionModel(StockTransaction ts) {
        this.idStockTransaction = ts.getId();
        this.date = ts.getDate();
        this.idPortfolio = ts.getPortfolio().getId();
        this.idStock = ts.getStock().getId();
        this.nrType = ts.getType().equals(TransactionType.VENDA) ? 1 : 2;
        this.amount = ts.getAmount();
    }

    public StockTransaction to() {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(idPortfolio);

        Stock stock = new Stock();
        stock.setId(idStock);

        StockTransaction ts = new StockTransaction();
        ts.setId(idStockTransaction);
        ts.setDate(date);
        ts.setAmount(amount);
        ts.setType(1 == nrType ? TransactionType.VENDA : TransactionType.COMPRA);
        ts.setPortfolio(portfolio);
        ts.setStock(stock);
        return ts;
    }
}
