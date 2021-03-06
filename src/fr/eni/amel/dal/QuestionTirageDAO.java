package fr.eni.amel.dal;

import fr.eni.amel.bo.QuestionTirage;
import fr.eni.tp.web.common.dal.dao.GenericDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface QuestionTirageDAO extends GenericDAO{

	public Object selectByIdEpreuve(Object id) throws DaoException;
	
	public QuestionTirage selectById(int idQuestion, int idEpreuve)  throws DaoException;
	
	public void createReponse(Integer idEpreuve, Integer idQuestion,Integer idProposition) throws DaoException;
	
	public void deleteReponse(Integer idEpreuve, Integer idQuestion,Integer idProposition) throws DaoException;

	public void insert(int IdQuestion, int IdEpreuve, int ordre) throws DaoException;
	
	public void update(int IdQuestion, int IdEpreuve, Boolean marquee) throws DaoException;

}
