package fr.eni.amel.dal;

import java.util.List;

import fr.eni.amel.bo.Proposition;
import fr.eni.tp.web.common.dal.dao.GenericDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;


public interface PropositionDao extends GenericDAO<Proposition, Integer> {

	List<Proposition> listePropositionsParQuestion(Integer idQuestion) throws DaoException;
	public List<Proposition> selectReponseByEpreuveQuestion(Integer idEpreuve, Integer idQuestion) throws DaoException;
	List<Proposition> selectPropositionsBonnesParQuestion(Integer idQuestion) throws DaoException;
}
