package espm.invest.portfolio;

import espm.invest.portfolio.common.datatype.Portfolio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio")
public class PortfolioModel {

    @Id
    @Column(name = "id_portfolio")
    private String idPortfolio;

    @Column(name = "id_user")
    private String idUser;

    private double balance;

    public PortfolioModel() {

    }

    public PortfolioModel(Portfolio c) {
        this.idPortfolio = c.getId();
        // this.idUser = c.getUser.getId();
        this.balance = c.getBalance();
    }

    public Portfolio to() {
        // Usuario usuario = new Usuario();
        // usuario.setId(idUsuario);

        Portfolio p = new Portfolio();
        p.setId(this.idPortfolio);
        p.setBalance(this.balance);
        // c.setUsuario(usuario);
        return p;
    }

}
