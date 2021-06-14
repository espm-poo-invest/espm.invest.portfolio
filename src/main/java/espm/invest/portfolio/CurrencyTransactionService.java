package espm.invest.portfolio;

import espm.invest.currency.common.controller.CurrencyController;
import espm.invest.portfolio.common.datatype.Portfolio;
import espm.invest.portfolio.common.datatype.CurrencyTransaction;
import espm.invest.portfolio.common.datatype.TransactionBean;
import espm.invest.portfolio.common.datatype.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CurrencyTransactionService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private CurrencyController currencyController;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private CurrencyTransactionRepository currencyTransactionRepository;

    public List<CurrencyTransaction> listByPortfolio(String idPortfolio) {
        List<CurrencyTransaction> l = currencyTransactionRepository
                .listByPortfolio(idPortfolio).stream()
                .map(CurrencyTransactionModel::to)
                .collect(Collectors.toList());
        return l;
    }

    public CurrencyTransaction buy(String idPortfolio, TransactionBean bean) {
        Portfolio c = portfolioService.findBy(idPortfolio);
        if (c == null) {
            throw new RuntimeException("Portfolio does not exist: " + idPortfolio);
        }

        Date now = new Date();

        Currency currency = currencyController.currency(bean.getName());
        if (currency == null) {
            throw new RuntimeException("Currency does not exist: " + bean.getName());
        }

//        Currency currency = currencyController.currency(currency.getName(), sdf.format(now));
//        if (cotacao == null) {
//            throw new RuntimeException("Cotacao nao existe: " + sdf.format(agora));
//        }

        CurrencyTransaction ts = new CurrencyTransaction();
        ts.setId(UUID.randomUUID().toString());
        ts.setPortfolio(c);
        ts.setCurrency(currency);
        ts.setType(TransactionType.COMPRA);
        ts.setAmount(bean.getAmount());
        ts.setDate(now);

        return currencyTransactionRepository.save(new CurrencyTransactionModel(ts)).to();
    }

    public CurrencyTransaction sell(String idPortfolio, TransactionBean bean) {
        Portfolio p = portfolioService.findBy(idPortfolio);
        if (p == null) {
            throw new RuntimeException("Portfolio does not exist: " + idPortfolio);
        }

        Date now = new Date();

        Currency currency = currencyController.currency(bean.getName());
        if (currency == null) {
            throw new RuntimeException("Currency does not exist: " + bean.getName());
        }

        //não sei se esses fazem sentido já que não tem cotação, é tudo Currency
        Currency currencyMoment = currencyController.currency(currency.getName(), sdf.format(now));
        if (currencyMoment == null) {
            throw new RuntimeException("Cotacao nao existe: " + sdf.format(now));
        }

        if (bean.getMax() <= 0
                && bean.getMax() - currencyMoment.getPrice() > 0) {
            throw new RuntimeException("Cotacao limite, atual: " + currencyMoment.getPrice());
        }

        CurrencyTransaction ts = new CurrencyTransaction();
        ts.setId(UUID.randomUUID().toString());
        ts.setPortfolio(p);
        ts.setCurrency(currency);
        ts.setType(TransactionType.VENDA);
        ts.setAmount(bean.getAmount());
        ts.setDate(now);

        return currencyTransactionRepository.save(new CurrencyTransactionModel(ts)).to();
    }
}
