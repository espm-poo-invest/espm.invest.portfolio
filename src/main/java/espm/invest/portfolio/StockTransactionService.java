package espm.invest.portfolio;

import espm.invest.portfolio.common.datatype.Portfolio;
import espm.invest.portfolio.common.datatype.StockTransaction;
import espm.invest.portfolio.common.datatype.TransactionBean;
import espm.invest.portfolio.common.datatype.TransactionType;
import espm.invest.stock.common.controller.StockController;
import espm.invest.stock.common.datatype.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StockTransactionService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private StockController stockController;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    public List<StockTransaction> listByPortfolio(String idPortfolio) {
        List<StockTransaction> l = stockTransactionRepository
                .listByPortfolio(idPortfolio).stream()
                .map(StockTransactionModel::to)
                .collect(Collectors.toList());
        return l;
    }

    public StockTransaction buy(String idPortfolio, TransactionBean bean) {
        Portfolio c = portfolioService.findBy(idPortfolio);
        if (c == null) {
            throw new RuntimeException("Portfolio does not exist: " + idPortfolio);
        }

        Date now = new Date();

        Stock stock = stockController.stock(bean.getName());
        if (stock == null) {
            throw new RuntimeException("Stock does not exist: " + bean.getName());
        }

//        Stock stock = stockController.stock(stock.getName(), sdf.format(now));
//        if (cotacao == null) {
//            throw new RuntimeException("Cotacao nao existe: " + sdf.format(agora));
//        }

        StockTransaction ts = new StockTransaction();
        ts.setId(UUID.randomUUID().toString());
        ts.setPortfolio(c);
        ts.setStock(stock);
        ts.setType(TransactionType.COMPRA);
        ts.setAmount(bean.getAmount());
        ts.setDate(now);

        return stockTransactionRepository.save(new StockTransactionModel(ts)).to();
    }

    public StockTransaction sell(String idPortfolio, TransactionBean bean) {
        Portfolio p = portfolioService.findBy(idPortfolio);
        if (p == null) {
            throw new RuntimeException("Portfolio does not exist: " + idPortfolio);
        }

        Date now = new Date();

        Stock stock = stockController.stock(bean.getName());
        if (stock == null) {
            throw new RuntimeException("Stock does not exist: " + bean.getName());
        }

        //não sei se esses fazem sentido já que não tem cotação, é tudo Stock
        Stock stockMoment = stockController.stock(stock.getName(), sdf.format(now));
        if (stockMoment == null) {
            throw new RuntimeException("Cotacao nao existe: " + sdf.format(now));
        }

        if (bean.getMax() <= 0
                && bean.getMax() - stockMoment.getPrice() > 0) {
            throw new RuntimeException("Cotacao limite, atual: " + stockMoment.getPrice());
        }

        StockTransaction ts = new StockTransaction();
        ts.setId(UUID.randomUUID().toString());
        ts.setPortfolio(p);
        ts.setStock(stock);
        ts.setType(TransactionType.VENDA);
        ts.setAmount(bean.getAmount());
        ts.setDate(now);

        return stockTransactionRepository.save(new StockTransactionModel(ts)).to();
    }

}
