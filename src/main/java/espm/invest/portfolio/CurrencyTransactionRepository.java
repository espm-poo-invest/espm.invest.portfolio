//package espm.invest.portfolio;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface CurrencyTransactionRepository extends CrudRepository<CurrencyTransactionModel, String> {
//    @Override
//    CurrencyTransactionModel save(CurrencyTransactionModel s);
//
//    @Override
//    Optional<CurrencyTransactionModel> findById(String s);
//
//    @Query("SELECT tc from CurrencyTransactionModel tc where tc.idPortfolio = :idPortfolio order by tc.date")
//    List<CurrencyTransactionModel> listByPortfolio(@Param("idPortfolio") String idPortfolio);
//
//}
