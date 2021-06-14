package espm.invest.portfolio;
import espm.invest.portfolio.common.controller.PortfolioController;
import espm.invest.portfolio.common.datatype.Portfolio;
import espm.invest.portfolio.common.datatype.StockTransaction;
import espm.invest.portfolio.common.datatype.TransactionBean;
import espm.invest.stock.common.controller.StockController;
import espm.invest.stock.common.datatype.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EnableFeignClients(basePackages = {"espm.invest.portfolio.common.controller","espm.invest.stock.common.controller"})
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
    public Portfolio portfolio() {
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
