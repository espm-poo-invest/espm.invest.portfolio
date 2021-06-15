package espm.invest.portfolio;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PortfolioRepository extends CrudRepository<PortfolioModel, String> {
    @Override
    PortfolioModel save(PortfolioModel s);

    @Override
    Iterable<PortfolioModel> findAll();

    @Override
    Optional<PortfolioModel> findById(String s);

//    @Query("SELECT c from CotacaoModel c WHERE c.idMoeda = :idMoeda AND c.dtData <= :data ORDER BY c.dtData DESC")
//    List<CotacaoModel> listByMoedaData(
//            @Param("idMoeda") String idMoeda,
//            @Param("data") Date data);
//
//    @Query("SELECT c FROM StockModel c " +
//            "WHERE " +
//            "(c.idMoeda is null or c.idMoeda = :idMoeda) AND " +
//            "(c.dtData is null or c.dtData >= :dtInicio) AND " +
//            "(c.dtData is null or c.dtData <= :dtFim)"
//    )
//    List<CotacaoModel> listBy(
//            @Param("idMoeda") String idMoeda,
//            @Param("dtInicio") Date dtInicio,
//            @Param("dtFim") Date dtFim
//    );
}
