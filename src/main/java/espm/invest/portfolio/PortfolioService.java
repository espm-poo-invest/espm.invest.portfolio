package espm.invest.portfolio;

import espm.invest.portfolio.common.datatype.Portfolio;
import espm.invest.stock.common.controller.StockController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

import java.util.UUID;
@EnableFeignClients(basePackages = {"espm.invest.portfolio.common.controller","espm.invest.stock.common.controller"})
@Component
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StockController stockController;

    @Autowired
    private StockTransactionService stockTransactionService;

    public Portfolio create(Portfolio p) {
        p.setId(UUID.randomUUID().toString());
        return portfolioRepository.save(new PortfolioModel(p)).to();
    }

    public Portfolio findBy(String id) {
        Portfolio p = portfolioRepository
                .findById(id)
                .map(PortfolioModel::to)
                .orElse(null);
        if (p != null) {
            // Aqui e uma amarracao mapping 1 .. n
            p.setStockTransaction(stockTransactionService.listByPortfolio(p.getId()));
            p.getStockTransaction().forEach(stockTransaction -> {
                stockTransaction.setStock(stockController.stockById(stockTransaction.getStock().getId()));
            });
            // c.setTransacoesAtivo(...);
        }
        return p;
    }

}
