package espm.invest.portfolio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockTransactionRepository extends CrudRepository<StockTransactionModel, String> {

    @Override
    StockTransactionModel save(StockTransactionModel s);

    @Override
    Optional<StockTransactionModel> findById(String s);

    @Query("SELECT tc from StockTransactionModel tc where tc.idPortfolio = :idPortfolio order by tc.date")
    List<StockTransactionModel> listByPortfolio(@Param("idPortfolio") String idPortfolio);

}