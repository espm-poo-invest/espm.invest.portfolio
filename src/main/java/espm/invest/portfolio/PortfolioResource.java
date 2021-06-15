package espm.invest.portfolio;

import espm.invest.portfolio.common.controller.PortfolioController;
import espm.invest.portfolio.common.datatype.Portfolio;
import espm.invest.portfolio.common.datatype.StockTransaction;
import espm.invest.portfolio.common.datatype.TransactionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PortfolioResource implements PortfolioController{
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private StockTransactionService stockTransactionService;

    @Override
    public Portfolio portfolio(String id) {
        return portfolioService.findBy(id);
    }

    @Override
    public List<Portfolio> portfolio() {
        return null;
    }

    @Override
    public Portfolio create(Portfolio portfolio) {
        Portfolio c = new Portfolio();
        c.setBalance(0.0);
        return portfolioService.create(c);
    }

    @Override
    public StockTransaction stockBuy(String idPortfolio, TransactionBean bean) {
        return stockTransactionService.buy(idPortfolio, bean);
    }

    @Override
    public StockTransaction stockSell(String idPortfolio, TransactionBean bean) {
        return stockTransactionService.sell(idPortfolio, bean);
    }
}
